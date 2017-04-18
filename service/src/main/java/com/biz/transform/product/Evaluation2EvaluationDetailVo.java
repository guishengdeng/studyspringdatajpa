package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Evaluation;
import com.biz.gbck.vo.evaluation.backend.EvaluationDetailVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author yangzichun
 * @date 2017/2/17
 */
public class Evaluation2EvaluationDetailVo implements Function<Evaluation, EvaluationDetailVo> {
    @Nullable
    @Override
    public EvaluationDetailVo apply(@Nullable Evaluation evaluation) {
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
