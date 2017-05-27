package com.biz.soa.service.voucher.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.codelogger.utils.StringUtils;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.voucher.VoucherExpireType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.mysql.repository.voucher.VoucherTypeRepository;
import com.biz.gbck.dao.redis.repository.voucher.VoucherTypeRedisDao;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.voucher.VoucherTypeVo;
import com.biz.service.AbstractBaseService;
import com.biz.soa.service.voucher.VoucherTypeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.codelogger.utils.ValueUtils.getValue;

@Service
public class VoucherTypeServiceImpl extends AbstractBaseService implements VoucherTypeService {

	private final static Logger logger = LoggerFactory.getLogger(VoucherTypeService.class);

	@Autowired
	private VoucherTypeRepository voucherTypeRepository;

	@Autowired
	private VoucherTypeRedisDao voucherTypeRedisDao;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional
	public void save(VoucherTypeVo voucherTypeVo) {
		VoucherTypePo voucherTypePo = new VoucherTypePo();
		voucherTypePo.setId(idService.nextId());
		voucherTypePo.setName(voucherTypeVo.getName());

		Double faceValue = Double.parseDouble(voucherTypeVo.getFaceValue()) * 100;
		voucherTypePo.setFaceValue(faceValue.intValue());

		Double paymentLimit = Double.parseDouble(voucherTypeVo.getPaymentLimit()) * 100;
		voucherTypePo.setPaymentLimit(paymentLimit.intValue());
		voucherTypePo.setVoucherExpireType(voucherTypeVo.getVoucherExpireType());
		if (NumberUtils.isNumber(voucherTypeVo.getPeriodDays())) {
			voucherTypePo.setPeriodDays(Integer.valueOf(voucherTypeVo.getPeriodDays()));
		}
		voucherTypePo.setMinCountInPool(Integer.valueOf(voucherTypeVo.getMinCountInPool()));
		voucherTypePo.setMailTo(voucherTypeVo.getMailTo());
		voucherTypePo.setIssuerName(voucherTypeVo.getIssuerName());
		voucherTypePo.setDescription(voucherTypeVo.getDescription());
		voucherTypePo.setIssueCount(voucherTypeVo.getIssueCount());
		voucherTypePo.setVoucherLimitType(voucherTypeVo.getVoucherLimitType());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = new Date();
		Date expireTime = new Date();
		try {
			startTime = sdf.parse(voucherTypeVo.getStartTime());
			expireTime = sdf.parse(voucherTypeVo.getExpireTime());
		} catch (Exception e) {
			logger.warn("转换日期字符串[{}]出错", voucherTypeVo.getStartTime());
		}
		voucherTypePo.setStartTime(new java.sql.Timestamp(startTime.getTime()));
		voucherTypePo.setExpireTime(new java.sql.Timestamp(expireTime.getTime()));

		if (getValue(voucherTypeVo.getProductType()) > 0) {
			voucherTypePo.setCategory(categoryRepository.findOne(Long.valueOf(voucherTypeVo.getProductType())));
		}

		if (StringUtils.isNotBlank(voucherTypeVo.getProductIds())) {
			String[] productIds = voucherTypeVo.getProductIds().split("\\D");
			Set<Product> products = new HashSet<>();
			for (String pid : productIds) {
				products.add(productRepository.findOne(Long.valueOf(pid)));
			}
			voucherTypePo.setProducts(products);
		}

		voucherTypePo.setTypeStatus(voucherTypeVo.getTypeStatus());
		voucherTypePo.setFetchType(voucherTypeVo.getFetchType());

		String paymentType = voucherTypeVo.getPaymentType();
		if (paymentType != null && !"".equals(paymentType)) {
			voucherTypePo.setPaymentType(paymentType+","+PaymentType.ALIPAY + "," + PaymentType.WECHAT);
		} else {
			voucherTypePo.setPaymentType(PaymentType.ALIPAY + "," + PaymentType.WECHAT);
		}

		//保存优惠券类型实体
		voucherTypeRepository.save(voucherTypePo);
		//redis保存
		voucherTypeRedisDao.save(voucherTypePo.voucherTypePo2Ro());
		List<VoucherRo> vouchers = Lists.newArrayList();
		for (int i = 0; i < voucherTypePo.getIssueCount(); i++) {
			VoucherRo voucherRo = new VoucherRo();
			voucherRo.setVoucherTypeId(voucherTypePo.getId());
			voucherRo.setId(idService.nextId());
			vouchers.add(voucherRo);
		}
		voucherTypeRedisDao.syncVoucherList(voucherTypePo.getId(), vouchers);
	}

	@Override
	@Transactional
	public List<VoucherTypePo> allVoucherTypes() {
		return voucherTypeRepository.allByStartTimeDesc();
	}

	@Override
	@Transactional
	public VoucherTypePo getVoucherTypeById(Long id) {
		return voucherTypeRepository.getDataById(id);
	}

	@Override
	@Transactional
	public VoucherTypeRo getVoucherTypeRoById(Long id) {
		return voucherTypeRedisDao.getVoucherTypeRoById(id);
	}

	@Override
	@Transactional
	public void addVoucherTypeIssueCount(Long id, int addIssueCount) {
		VoucherTypePo voucherTypePo = voucherTypeRepository.getDataById(id);
		voucherTypePo.setIssueCount(voucherTypePo.getIssueCount() + addIssueCount);
		voucherTypeRepository.save(voucherTypePo);
		voucherTypeRedisDao.save(voucherTypePo.voucherTypePo2Ro());

		List<VoucherRo> vouchers = Lists.newArrayList();
		for (int i = 0; i < addIssueCount; i++) {
			VoucherRo voucherRo = new VoucherRo();
			voucherRo.setVoucherTypeId(voucherTypePo.getId());
			voucherRo.setId(idService.nextId());
			vouchers.add(voucherRo);
		}
		voucherTypeRedisDao.syncVoucherList(voucherTypePo.getId(), vouchers);
	}

	@Override
	@Transactional
	public void deleteVoucherType(Long id) {
		voucherTypeRepository.deleteDataById(id, new java.sql.Timestamp(System.currentTimeMillis()));
	}

	@Override
	@Transactional
	public List<VoucherTypeRo> allVoucherTypesInApp() {
		return voucherTypeRedisDao.allVoucherTypes();
	}

	@Override
	public Map<Long, List<Long>> dividVoucherTypeIdsByCategory(Collection<Long> ids) {
		Map<Long, List<Long>> categoryVoucherTypeIds = Maps.newHashMap();
		for (Long id : ids) {
			VoucherTypeRo voucherTypeRo = getVoucherTypeRoById(id);
			Long categoryId = voucherTypeRo.getCategoryId();
			List<Long> voucherTypeIds;
			if (categoryVoucherTypeIds.containsKey(categoryId)) {
				voucherTypeIds = categoryVoucherTypeIds.get(categoryId);
			} else {
				voucherTypeIds = Lists.newArrayList();
			}
			voucherTypeIds.add(id);
			categoryVoucherTypeIds.put(categoryId, voucherTypeIds);
		}
		return categoryVoucherTypeIds;
	}

	@Override
	@Transactional
	public void update(VoucherTypeVo voucherTypeVo) {
		if (voucherTypeVo.getId() == null) {
			logger.warn("修改优惠券[{}]ID为空", voucherTypeVo);
		}
		VoucherTypePo voucherTypePo = this.getVoucherTypeById(voucherTypeVo.getId());
		voucherTypePo.setName(voucherTypeVo.getName());
		if (voucherTypePo.getVoucherExpireType() == null) {
			voucherTypePo.setVoucherExpireType(VoucherExpireType.EXPIRE_BY_PERIOD);
		}

		voucherTypeRepository.save(voucherTypePo);
		voucherTypeRedisDao.save(voucherTypePo.voucherTypePo2Ro());
	}

}
