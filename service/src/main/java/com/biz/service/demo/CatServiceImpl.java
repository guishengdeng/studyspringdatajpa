package com.biz.service.demo;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.dao.mysql.repository.demo.CatRepository;
import com.biz.gbck.dao.mysql.specification.demo.CatSearchSpecification;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.service.AbstractRepositorySupportService;
import com.biz.service.demo.interfaces.CatService;
import com.biz.support.jpa.repository.CommonJpaRepository;
import com.biz.vo.demo.CatReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static org.codelogger.utils.ExceptionUtils.iae;

/**
 * Created by defei on 4/17/17.
 */
@Service
public class CatServiceImpl extends AbstractRepositorySupportService<CatPO> implements CatService {

	@Autowired
	private CatRepository catRepository;

	@Override
	protected CommonJpaRepository<CatPO, Long> getRepository() {

		return catRepository;
	}

	/**
	 * 保存猫数据
	 */
	@Override
	public CatPO save(CatReqVO vo) {
		iae.throwIfNull(vo, "参数不能为空");
		CatPO existCat = getByName(vo.getName());
		iae.throwIfTrue(existCat != null && !Objects.equals(vo.getId(), existCat.getId()), "名字已存在");

		CatPO catPO = vo.getId() == null ? new CatPO() : get(vo.getId());
		catPO.setId(vo.getId());
		catPO.setName(vo.getName());
		catPO.setHomepage(vo.getHomepage());
		catPO.setDescription(vo.getDescription());
		catPO.setSaleStatus(vo.getSaleStatus());
		catPO.setStatus(vo.getStatus());
		catPO.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
		return super.save(catPO);
	}

	/**
	 * 通过名字查找猫
	 */
	@Override
	public CatPO getByName(String name) {

		return catRepository.findByName(name);
	}

	/**
	 * 例出对应状态下的所有猫
	 */
	@Override
	public List<CatPO> listByStatus(CommonStatusEnum status) {

		return catRepository.findByStatus(status);
	}

	/**
	 * 例出对应销售状态下的所有猫
	 */
	@Override
	public List<CatPO> listBySaleStatus(SaleStatusEnum saleStatus) {

		return catRepository.findBySaleStatus(saleStatus);
	}

	/**
	 * 搜索猫
	 */
	public Page<CatPO> searchCat(CatSearchVO reqVo){

		return catRepository.findAll(new CatSearchSpecification(reqVo), new PageRequest(reqVo.getPage()-1, reqVo.getPageSize(), Sort.Direction.ASC, "name"));
	}

	/**
	 * 更新销售状态
	 */
	@Override
	public void updateSaleStatus(Long id, SaleStatusEnum saleStatus) {

		catRepository.updateSaleStatus(id, saleStatus);
	}

	/**
	 * 把猫的状态设置为禁用
	 */
	@Override
	public void remove(Long id) {

		catRepository.updateStatus(id, CommonStatusEnum.DISABLE);
	}
}
