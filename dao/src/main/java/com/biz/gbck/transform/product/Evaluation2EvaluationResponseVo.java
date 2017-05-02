package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.Evaluation;
import com.biz.gbck.vo.evaluation.backend.EvaluationResponseVo;
import com.google.common.base.Function;

/**
 * @author yangzichun
 * @date 2017/2/9
 */
public class Evaluation2EvaluationResponseVo implements Function<Evaluation, EvaluationResponseVo> {
    @Override
    public EvaluationResponseVo apply(Evaluation evaluation) {
        EvaluationResponseVo evaluationResponseVo = new EvaluationResponseVo();
        evaluationResponseVo.setId(evaluation.getId());
        evaluationResponseVo.setProductCode(evaluation.getProduct() == null ? null : evaluation.getProduct().getProductCode());
        evaluationResponseVo.setVendorName(evaluation.getVendor() == null ? null : evaluation.getVendor().getVendorName());
        evaluationResponseVo.setCommonStatus(evaluation.getCommonStatus() == null ? null : evaluation.getCommonStatus().getValue());
        evaluationResponseVo.setOrderCode(evaluation.getOrderCode());
        evaluationResponseVo.setEvaluationIdString(String.valueOf(evaluation.getId()));
        evaluationResponseVo.setEvaluationTime(evaluation.getCreateTimestamp());
        return evaluationResponseVo;
    }
}
