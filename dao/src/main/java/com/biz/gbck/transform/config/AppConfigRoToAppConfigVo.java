package com.biz.gbck.transform.config;

import com.biz.gbck.dao.redis.ro.config.AppConfigRo;
import com.biz.gbck.vo.config.AppConfigVo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AppConfigRoToAppConfigVo
        implements Function<AppConfigRo, AppConfigVo>, Serializable {

    @Override
    public AppConfigVo apply(AppConfigRo ro) {
        AppConfigVo vo = null;
        if (ro != null) {
            vo = new AppConfigVo();
            vo.registSkipUserInfo = ro.getRegistSkipUserInfo();
            vo.tel400 = ro.getTel400();
            vo.hotKeywords = ro.getHotKeywords();
            vo.pictureUrl = ro.getPictureUrl();
            vo.clickUrl = ro.getClickUrl();
            vo.icon = ro.getIcon();
            vo.title = ro.getTitle();
            vo.content = ro.getContent();
            vo.recommendUrl=ro.getRecommendUrl();
            vo.shareUrl=ro.getShareUrl();
            vo.appDownloadUrl = ro.getAppDownloadUrl();
            vo.finishOrderSendVoucherLimit=ro.getFinishOrderSendVoucherLimit();
            vo.setOrderLimitForVIP20(ro.getOrderLimitForVIP20());
            vo.setMinQuantityMultiplicand(ro.getMinQuantityMultiplicand());
            vo.setMaxQuantityMultiplicand(ro.getMaxQuantityMultiplicand());
            vo.setSearchPlaceHolder(ro.getSearchPlaceHolder());
            vo.setTags(ro.getTags());
        }
        return vo;
    }

}

