package com.biz.gbck.transform.promotion;

import com.biz.gbck.dao.mysql.po.info.PromotionPo;
import com.biz.gbck.vo.promotion.PromotionVo;
import com.google.common.base.Function;


import java.io.Serializable;

/**
 * 活动发布
 * Created by dylan on 2016/3/22.
 */
public class PromotionVoToPromotionPo implements Function<PromotionVo, PromotionPo>, Serializable {
    @Override public PromotionPo apply(PromotionVo vo) {
        PromotionPo po=null;
        if(null != vo){
            po=new PromotionPo();
            po.setId(vo.getId());
            po.setTitle(vo.getTitle());
            po.setUrl(vo.getUrl());
            po.setAdminId(vo.getAdminId());
            po.setCreateTime(vo.getCreateTime());
            po.setIdx(vo.getIdx());
            po.setShowInApp(vo.getShowInApp());
            po.setLogo(vo.getLogo());
            po.setStatus(vo.getStatus());
        }
        return  po;
    }
}
