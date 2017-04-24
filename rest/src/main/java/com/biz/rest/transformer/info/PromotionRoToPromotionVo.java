package com.biz.rest.transformer.info;

import com.biz.gbck.dao.redis.ro.info.PromotionRo;
import com.biz.rest.vo.PromotionVo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial") public class PromotionRoToPromotionVo
    implements Function<PromotionRo, PromotionVo>, Serializable {

    @Override public PromotionVo apply(PromotionRo ro) {
        PromotionVo vo = null;
        if (ro != null) {
            vo = new PromotionVo();
            vo.id = ro.getId();
            vo.title = ro.getTitle();
            vo.url = ro.getUrl();
            vo.logo = ro.getLogo();
        }
        return vo;
    }

}
