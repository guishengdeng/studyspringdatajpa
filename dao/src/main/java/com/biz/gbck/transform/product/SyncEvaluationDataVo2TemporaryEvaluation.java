package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.TemporaryEvaluation;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.SyncEvaluationDataVo;
import com.google.common.base.Function;
import java.sql.Timestamp;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangcheng
 * @date 2017/3/24
 * @reviewer
 * @see
 */
public class SyncEvaluationDataVo2TemporaryEvaluation implements Function<SyncEvaluationDataVo, TemporaryEvaluation> {

    @Override
    public TemporaryEvaluation apply(SyncEvaluationDataVo vo) {
        if (vo != null) {
            TemporaryEvaluation evaluation = new TemporaryEvaluation();
            if (CollectionUtils.isNotEmpty(vo.getImages())) {
                evaluation.setImages(vo.getImages());
            }
            if (StringUtils.isNotBlank(vo.getAttitudeScore())) {
                evaluation.setAttitudeScore(Integer.valueOf(vo.getAttitudeScore()));
            } else {
                evaluation.setAttitudeScore(50);
            }
            if (StringUtils.isNotBlank(vo.getDescriptionScore())) {
                evaluation.setDescriptionScore(Integer.valueOf(vo.getDescriptionScore()));
            } else {
                evaluation.setDescriptionScore(50);
            }
            if (StringUtils.isNotBlank(vo.getLogisticsScore())) {
                evaluation.setLogisticsScore(Integer.valueOf(vo.getLogisticsScore()));
            } else {
                evaluation.setLogisticsScore(50);
            }
            evaluation.setContent(vo.getContent());
            evaluation.setProductCode(vo.getProductCode());
            evaluation.setMemberId(Long.valueOf(vo.getMemberId()));
            evaluation.setOldBBCOrderCode(vo.getOldBBCOrderCode());
            evaluation.setProductName(vo.getProductName());
            evaluation.setCreateTime(Timestamp.valueOf(vo.getCreateTime()));
            if (Objects.equals(vo.getShowStatus(), "1")) {
                evaluation.setShowStatus(CommonStatusEnum.ENABLE);
            } else {
                evaluation.setShowStatus(CommonStatusEnum.DISABLE);
            }
            if (Objects.equals(vo.getVendorName().trim(), "1919自营")) {
                evaluation.setVendorName("1919自营店");
            }
            evaluation.setVendorName(vo.getVendorName());
            return evaluation;
        }
        return null;
    }
}
