package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.Evaluation;
import com.biz.gbck.vo.evaluation.backend.EvaluationDetailVo;
import com.google.common.base.Function;

/**
 * @author yangzichun
 * @date 2017/2/17
 */
public class Evaluation2EvaluationDetailVo implements Function<Evaluation, EvaluationDetailVo> {
    @Override
    public EvaluationDetailVo apply(Evaluation evaluation) {
        EvaluationDetailVo evaluationDetailVo = new EvaluationDetailVo();
        evaluationDetailVo.setId(evaluation.getId());
        evaluationDetailVo.setOrderCode(evaluation.getOrderCode());
        evaluationDetailVo.setCommonStatus(evaluation.getCommonStatus() == null ? null : evaluation.getCommonStatus().toString());
        evaluationDetailVo.setAttitudeScore(evaluation.getAttitudeScore());
        evaluationDetailVo.setContent(evaluation.getContent());
        evaluationDetailVo.setDescriptionScore(evaluation.getDescriptionScore());
        evaluationDetailVo.setImages(evaluation.getImages());
        evaluationDetailVo.setLogisticsScore(evaluation.getLogisticsScore());
        evaluationDetailVo.setProductName(evaluation.getProduct() == null ? null : evaluation.getProduct().getName());
        evaluationDetailVo.setCreateTime(evaluation.getCreateTimestamp());
        return evaluationDetailVo;
    }
}
