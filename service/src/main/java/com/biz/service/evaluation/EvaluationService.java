package com.biz.service.evaluation;

import com.biz.core.page.PageResult;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.evaluation.frontend.EvaluationCountVo;
import com.biz.gbck.vo.evaluation.frontend.EvaluationVo;
import com.biz.gbck.vo.evaluation.frontend.VendorIdPageRequestVo;
import com.biz.gbck.vo.product.backend.SearchPageVo;

/**
 * 评论service
 *
 * @author yangzichun
 * @date 2017/2/15
 */
public interface EvaluationService {
    /**
     * 从redis中查询评价 vo 分页结果
     *
     * @param searchPageVo 评价分页请求vo
     * @return 评价 vo 分页结果
     * @throws IllegalParameterException 参数不合法异常
     */
    PageResult findEvaluations(SearchPageVo searchPageVo) throws DepotNextDoorException;

    /**
     * 根据店铺 id 从redis中查询评价 vo 分页结果
     *
     * @param vendorEvaluationQueryRequestVo 店铺查询评价分页请求vo
     * @return 评价 vo 分页结果
     * @throws IllegalParameterException 参数不合法异常
     */
    PageResult<EvaluationVo> findByVendorIdPage(VendorIdPageRequestVo vendorEvaluationQueryRequestVo) throws DepotNextDoorException;

    PageResult<EvaluationVo> findEvaluationByProductCode(VendorIdPageRequestVo vendorEvaluationQueryRequestVo) throws IllegalParameterException;

    EvaluationCountVo findAllEvaluationByVendorId(VendorIdPageRequestVo vo) throws IllegalParameterException;

    EvaluationCountVo findEvaluationCountByProductCode(VendorIdPageRequestVo reqVo) throws IllegalParameterException;
}
