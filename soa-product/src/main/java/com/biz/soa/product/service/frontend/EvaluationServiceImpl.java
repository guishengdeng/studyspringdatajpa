package com.biz.soa.product.service.frontend;

import com.biz.core.page.PageResult;
import com.biz.core.util.JsonUtil;
import com.biz.gbck.dao.mysql.po.product.bbc.Evaluation;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.dao.mysql.po.product.bbc.Vendor;
import com.biz.gbck.dao.mysql.repository.bbc.evaluation.EvaluationRepository;
import com.biz.gbck.dao.mysql.repository.bbc.product.ProductRepository;
import com.biz.gbck.dao.mysql.repository.bbc.vendor.VendorRepository;
import com.biz.gbck.dao.redis.repository.product.bbc.EvaluationRedisDao;
import com.biz.gbck.dao.redis.repository.product.bbc.ProductRedisDao;
import com.biz.gbck.dao.redis.repository.vendor.VendorRedisDao;
import com.biz.gbck.dao.redis.ro.evaluation.EvaluationRo;
import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.dao.redis.ro.vendor.VendorRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.evaluation.frontend.EvaluationCountVo;
import com.biz.gbck.vo.evaluation.frontend.EvaluationVo;
import com.biz.gbck.vo.evaluation.frontend.VendorIdPageRequestVo;
import com.biz.gbck.vo.product.backend.EvaluationAddVo;
import com.biz.gbck.vo.product.backend.SearchPageVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.evaluation.EvaluationService;
import com.biz.gbck.transform.product.EvaluationRo2EvaluationVo;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author yangzichun
 * @date 2017/2/15
 */
@Service
public class EvaluationServiceImpl extends AbstractBaseService implements EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EvaluationRedisDao evaluationRedisDao;
    @Autowired
    private VendorRedisDao vendorRedisDao;
    @Autowired
    private ProductRedisDao productRedisDao;

    /**
     * 从redis中查询评价 vo 分页结果
     *
     * @param searchPageVo 评价分页请求vo
     * @return 评价 vo 分页结果
     * @throws IllegalParameterException 参数不合法异常
     */
    @Override
    public PageResult<EvaluationVo> findEvaluations(SearchPageVo searchPageVo) throws IllegalParameterException {
        // searchPageVo对象或pageIndex、pageSize为空
        if (searchPageVo == null || searchPageVo.getPageIndex() < 0 || searchPageVo.getPageSize() <= 0) {
            throw new IllegalParameterException("请求参数不合法");
        }
        // 根据页数从redis中查询评价ro，第一页为1
        List<EvaluationRo> evaluationRos = evaluationRedisDao.findByScoreDesc(searchPageVo.getPageIndex(),
                searchPageVo.getPageSize());
        // 如果未查询到评价集合,返回new PageResult
        return buildPageResult(searchPageVo, evaluationRos);
    }

    /**
     * 生成返回vo
     */
    private PageResult<EvaluationVo> buildPageResult(SearchPageVo searchPageVo, List<EvaluationRo> evaluationRos) {
        PageResult pageResult;
        if (CollectionUtils.isNotEmpty(evaluationRos)) {
            List<EvaluationVo> evaluationVos = Lists.transform(evaluationRos, new EvaluationRo2EvaluationVo());
            pageResult = new PageResult<>(searchPageVo.getPageIndex() + 1, evaluationVos.size(), evaluationRos.size(),
                    evaluationVos);
            pageResult.setHasNext(evaluationRos.size() / searchPageVo.getPageSize() == 1);
        } else {
            pageResult = new PageResult<>(searchPageVo.getPageIndex() + 1, searchPageVo.getPageSize(), 0,
                    newArrayList());
        }
        return pageResult;
    }

    /**
     * 根据商品Id查询评价
     *
     * @param reqVo 商品查询评价分页请求vo
     * @throws IllegalParameterException
     */
    public PageResult<EvaluationVo> findEvaluationByProductCode(VendorIdPageRequestVo reqVo)
            throws IllegalParameterException {
        if (reqVo == null || reqVo.getProductCode() == null || reqVo.getPageIndex() < 0 || reqVo.getPageSize() <= 0) {
            throw new IllegalParameterException("请求参数不合法");
        }
        List<EvaluationRo> evaluationRos = evaluationRedisDao.findByEvaluationProductCode(reqVo);
        if (reqVo.isImageEvaluation()) {
            List<EvaluationRo> arr = newArrayList();
            for (int i = 0; i < evaluationRos.size(); i++) {
                if (StringUtils.isNotEmpty(evaluationRos.get(i).getImages())) {
                    arr.add(evaluationRos.get(i));
                }
            }
            return buildPageResult(reqVo, arr);
        }
        return buildPageResult(reqVo, evaluationRos);
    }

    /**
     * 根据店铺 id 从redis中查询评价 vo 分页结果
     *
     * @param reqVo 店铺查询评价分页请求vo
     * @return 评价 vo 分页结果
     * @throws IllegalParameterException 参数不合法异常
     */
    @Override
    public PageResult<EvaluationVo> findByVendorIdPage(VendorIdPageRequestVo reqVo) throws IllegalParameterException {
        if (reqVo == null || reqVo.getVendorId() == null || reqVo.getPageIndex() < 0 || reqVo.getPageSize() <= 0) {
            throw new IllegalParameterException("请求参数不合法");
        }
        // 设置分页参数
        List<EvaluationRo> evaluationRos = evaluationRedisDao.findByVendorId(reqVo);
        if (reqVo.isImageEvaluation()) {
            List<EvaluationRo> arr = newArrayList();
            for (int i = 0; i < evaluationRos.size(); i++) {
                if (StringUtils.isNotEmpty(evaluationRos.get(i).getImages())) {
                    arr.add(evaluationRos.get(i));
                }
            }
            return buildPageResult(reqVo, arr);
        }
        return buildPageResult(reqVo, evaluationRos);
    }

    /**
     * 保存或更新一条评价
     *
     * @param evaluationAddVo 评价vo
     */
    @Override
    @Transactional
    public void saveOrUpdateEvaluation(EvaluationAddVo evaluationAddVo) throws IllegalParameterException {
        if (logger.isDebugEnabled()) {
            logger.debug("保存评价信息,参数[{}]", JsonUtil.obj2Json(evaluationAddVo));
        }
        // 判断参数是否为空
        if (evaluationAddVo == null || evaluationAddVo.getProductId() == null
                || evaluationAddVo.getOrderCode() == null) {
            logger.error("传递参数不合法");
            throw new IllegalParameterException("无效评价");
        }
        Evaluation evaluation = null;
        // 如果这是一条新建的评论,分配id
        if (evaluationAddVo.getId() == null) {
            evaluation = new Evaluation();
            evaluation.setId(idService.nextId());
            evaluation.setImages(evaluationAddVo.getImages());
        } else {
            // 如果已经存在，从vo中获取id
            evaluation.setId(evaluationAddVo.getId());
        }

        Product product = productRepository.findOne(evaluationAddVo.getProductId());
        Vendor vendor = vendorRepository.findOne(evaluationAddVo.getVendorId());
        // 检查商品信息，如果为空抛出异常
        //        BusinessAsserts.notNull(product, DepotNextDoorExceptions.Product.NOT_EXIST);
        // 如果店铺信息为空，判断是否为B类商品，如果是，抛出异常
        if (vendor == null) {
            //            BusinessAsserts.isFalse(product.getProductType().equals(VendorTypeEnum.TYPE_B), DepotNextDoorExceptions.Product.EXTEND_NOT_VALID);
        }
        // 设置评价的id、店铺、商品信息
        evaluation.setProduct(product);
        evaluation.setVendor(vendor);
        // 从评价 vo 拷贝除了id、店铺、商品信息以外的信息
        evaluation.fromEvaluationAddVo(evaluationAddVo);
        try {
            Evaluation re = evaluationRepository.save(evaluation);
        } catch (Exception e) {
            logger.error("保存评价出错", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 保存或者更新较多评论
     *
     * @param evaluationAddVos 添加评论vo
     * @throws DepotNextDoorException Soa异常
     */
    @Override
    public void saveOrUpdateEvaluations(List<EvaluationAddVo> evaluationAddVos) throws DepotNextDoorException {
        if (CollectionUtils.isEmpty(evaluationAddVos)) {
            logger.error("传递参数不合法");
            throw new IllegalParameterException("无效评价");
        }
        List<Evaluation> evaluations = Lists.newArrayList();
        for (EvaluationAddVo evaluationAddVo : evaluationAddVos) {
            Evaluation evaluation = new Evaluation();
            if (evaluationAddVo.getId() == null) {
                evaluation.setId(idService.nextId());
            } else {
                // 如果已经存在，从vo中获取id
                evaluation.setId(evaluationAddVo.getId());
            }
            Product product = productRepository.findOne(evaluationAddVo.getProductId());
            Vendor vendor = vendorRepository.findOne(evaluationAddVo.getVendorId());
            // 检查商品信息，如果为空抛出异常
            //            BusinessAsserts.notNull(product, DepotNextDoorExceptions.Product.NOT_EXIST);
            // 如果店铺信息为空，判断是否为B类商品，如果是，抛出异常
            if (vendor == null) {
                //                BusinessAsserts.isFalse(product.getProductType().equals(VendorTypeEnum.TYPE_B),
                //                DepotNextDoorExceptions.Product.EXTEND_NOT_VALID);
            }
            // 设置评价的id、店铺、商品信息
            evaluation.setProduct(product);
            evaluation.setVendor(vendor);
            // 从评价 vo 拷贝除了id、店铺、商品信息以外的信息
            evaluation.fromEvaluationAddVo(evaluationAddVo);
            evaluations.add(evaluation);
        }
        try {
            logger.info("开始保存评价，共{}个", evaluations.size());
            evaluationRepository.save(evaluations);
        } catch (Exception e) {
            logger.error("保存评价出错", e.getMessage(), e);
        }
    }

    /**
     * 获取店铺总评价
     *
     * @throws IllegalParameterException
     */
    public EvaluationCountVo findAllEvaluationByVendorId(VendorIdPageRequestVo reqVo) throws IllegalParameterException {
        if (reqVo == null || reqVo.getProductId() == null) {
            throw new IllegalParameterException("请求参数不合法");
        }
        VendorRo vendorRo = vendorRedisDao.findById(reqVo.getVendorId());
        EvaluationCountVo evaluationCountVo = new EvaluationCountVo();
        if (vendorRo != null) {
            evaluationCountVo.setAttitudeNum(vendorRo.getAttitudeNum());
            evaluationCountVo.setAttitudeScore(vendorRo.getAttitudeScore());
            evaluationCountVo.setDescriptionNum(vendorRo.getDescriptionNum());
            evaluationCountVo.setDescriptionScore(vendorRo.getDescriptionScore());
            evaluationCountVo.setLogisticsNum(vendorRo.getLogisticsNum());
            evaluationCountVo.setLogisticsScore(vendorRo.getLogisticsScore());
        }
        return evaluationCountVo;

    }

    /**
     * 根据商品Id获取总评价
     */
    public EvaluationCountVo findEvaluationCountByProductCode(VendorIdPageRequestVo reqVo)
            throws IllegalParameterException {
        if (reqVo.getProductCode() == null || reqVo == null || reqVo.getVendorId() == null) {
            throw new IllegalParameterException("请求参数不合法");
        }
        ProductRo productRo = productRedisDao.findOne(reqVo.getProductCode());
        VendorRo vendorRo = vendorRedisDao.findById(reqVo.getVendorId());
        EvaluationCountVo evaluationCountVo = new EvaluationCountVo();
        if (vendorRo != null) {
            evaluationCountVo.setAttitudeScore(vendorRo.getAttitudeScore());
            evaluationCountVo.setLogisticsScore(vendorRo.getLogisticsScore());
            evaluationCountVo.setDescriptionScore(vendorRo.getLogisticsScore());
            evaluationCountVo.setProductscore(productRo.getScore());
        }
        return evaluationCountVo;

    }

    /**
     * 备注：该方法由于Vo的中参数没有设置完成，并且该方法用于导入订单时使用，其他地方请慎用
     */
    @Override
    public List<EvaluationVo> findAllEvaluations() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        logger.debug("所有的评价的数量为：{}", evaluations.size());
        List<EvaluationVo> evaluationVos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(evaluations)) {
            for (Evaluation evaluation : evaluations) {
                EvaluationVo evaluationVo = new EvaluationVo();
                evaluationVo.setId(evaluation.getId());
                evaluationVo.setOrderCode(evaluation.getOrderCode());
                evaluationVo.setMemberId(evaluation.getMemberId());
                evaluationVos.add(evaluationVo);
            }
            return evaluationVos;
        }
        return Lists.newArrayList();
    }

}
