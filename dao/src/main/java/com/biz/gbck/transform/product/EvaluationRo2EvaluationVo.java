package com.biz.gbck.transform.product;

import com.biz.gbck.dao.redis.ro.evaluation.EvaluationRo;
import com.biz.gbck.vo.evaluation.frontend.EvaluationVo;
import com.google.common.base.Function;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ArrayUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author yangzichun
 * @date 2017/2/10
 */
public class EvaluationRo2EvaluationVo implements Function<EvaluationRo, EvaluationVo> {
    @Override
    public EvaluationVo apply(EvaluationRo evaluationRo) {
        EvaluationVo evaluationVo = new EvaluationVo();
        BeanUtils.copyProperties(evaluationRo, evaluationVo);
        evaluationVo.setEvaluationTime(evaluationRo.getEvaluationTime());
        evaluationVo.setNickName(evaluationRo.getAccountName());
        //如果评论图片不为空
        if (StringUtils.isNotBlank(evaluationRo.getImages())) {
            //将ro中以","分隔的图片地址取出传递给vo
            String[] imageArray = evaluationRo.getImages().split(",");
            List<String> images = ArrayUtils.toList(imageArray);
            evaluationVo.setImages(images);
        }
        return evaluationVo;
    }
}
