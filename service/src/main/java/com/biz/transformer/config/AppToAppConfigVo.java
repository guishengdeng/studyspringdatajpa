package com.biz.transformer.config;


import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.vo.config.AppConfigVo;
import com.google.common.base.Function;

import java.io.Serializable;

public class AppToAppConfigVo implements Function<App, AppConfigVo>, Serializable {

    @Override public AppConfigVo apply(App po) {
        AppConfigVo vo=null;
        if(po != null){
            vo=new AppConfigVo();
            vo.setSearchPlaceHolder(po.getTabOne());
            vo.setTel400(po.getTel());
            vo.setHotKeywords(po.getHotKeyWord());
            vo.setPictureUrl(po.getPictureUrl());
            vo.setClickUrl(po.getUrl());
            vo.setFinishOrderSendVoucherLimit(po.getAmount());
        }
        return vo;
    }
}
