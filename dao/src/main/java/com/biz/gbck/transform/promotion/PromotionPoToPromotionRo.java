package com.biz.gbck.transform.promotion;

import com.biz.gbck.dao.mysql.po.info.PromotionPo;
import com.biz.gbck.dao.redis.ro.info.PromotionRo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial") public class PromotionPoToPromotionRo
    implements Function<PromotionPo, PromotionRo>, Serializable {

    @Override public PromotionRo apply(PromotionPo po) {
        PromotionRo ro = null;
        if (po != null) {
            ro = new PromotionRo();
            ro.setId(po.getId());
            ro.setIdx(po.getIdx());
            ro.setTitle(po.getTitle());
            ro.setLogo(po.getLogo());
            ro.setUrl(po.getUrl());
        }
        return ro;
    }

}
