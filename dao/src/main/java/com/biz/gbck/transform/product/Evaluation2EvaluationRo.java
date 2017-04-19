package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.Evaluation;
import com.biz.gbck.dao.redis.ro.product.EvaluationRo;
import com.google.common.base.Function;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangzichun
 * @date 2017/2/10
 */
public class Evaluation2EvaluationRo implements Function<Evaluation, EvaluationRo> {
    @Override
    public EvaluationRo apply(Evaluation evaluation) {
        EvaluationRo evaluationRo = new EvaluationRo();
        evaluationRo.setId(evaluation.getId());
        evaluationRo.setMemberId(evaluation.getMemberId());
        evaluationRo.setOrderCode(evaluation.getOrderCode());
        evaluationRo.setProductId(evaluation.getProduct().getId());
        if (evaluation.getVendor() != null) {
            evaluationRo.setVendorId(evaluation.getVendor().getId());
        }
        if (evaluation.getVendor() != null) {
            evaluationRo.setVendorName(evaluation.getVendor().getVendorName());
        }
        evaluationRo.setProductName(evaluation.getProduct().getName());
        evaluationRo.setDescriptionScore(evaluation.getDescriptionScore());
        evaluationRo.setAtitudeScore(evaluation.getAttitudeScore());
        evaluationRo.setLogisticsScore(evaluation.getLogisticsScore());
        evaluationRo.setContent(evaluation.getContent());
        evaluationRo.setEvaluationTime(evaluation.getCreateTimestamp());
        evaluationRo.setProductCode(evaluation.getProduct().getProductCode());
        if (CollectionUtils.isNotEmpty(evaluation.getImages())) {
            evaluationRo.setImages(StringUtils.join(evaluation.getImages(), ","));
        }
        return evaluationRo;
    }
}
