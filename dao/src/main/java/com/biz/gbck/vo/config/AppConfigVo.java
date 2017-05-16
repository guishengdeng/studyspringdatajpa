package com.biz.gbck.vo.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动上报 on dylan
 */
public class AppConfigVo {
    public boolean registSkipUserInfo=false; //是否跳转后续步骤
    public String searchPlaceHolder; //搜索栏热语句
    public String tel400; //400电话
    public String hotKeywords; //热点词以空格分隔
    public String pictureUrl; //遮罩图片url
    public String clickUrl; //遮罩点击调整url
    public Integer finishOrderSendVoucherLimit; //满多少送优惠卷

    public Map<String, String> getMap() {
        Map map = new HashMap<String, String>();
        map.put("registSkipUserInfo", Boolean.toString(registSkipUserInfo));
        map.put("searchPlaceHolder", searchPlaceHolder);
        map.put("tel400", tel400);
        map.put("hotKeywords", hotKeywords);
        map.put("notice", this.getNoticeMap());
        map.put("finishOrderSendVoucherLimit", finishOrderSendVoucherLimit);
        return map;
    }

    public Map<String, String> getNoticeMap() {
        Map<String, String> noticeMap = new HashMap<>();
        noticeMap.put("pictureUrl", pictureUrl);
        noticeMap.put("clickUrl", clickUrl);
        return noticeMap;
    }


    public boolean isRegistSkipUserInfo() {
        return registSkipUserInfo;
    }

    public void setRegistSkipUserInfo(boolean registSkipUserInfo) {
        this.registSkipUserInfo = registSkipUserInfo;
    }

    public String getSearchPlaceHolder() {
        return searchPlaceHolder;
    }

    public void setSearchPlaceHolder(String searchPlaceHolder) {
        this.searchPlaceHolder = searchPlaceHolder;
    }

    public String getTel400() {
        return tel400;
    }

    public void setTel400(String tel400) {
        this.tel400 = tel400;
    }

    public String getHotKeywords() {
        return hotKeywords;
    }

    public void setHotKeywords(String hotKeywords) {
        this.hotKeywords = hotKeywords;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public Integer getFinishOrderSendVoucherLimit() {
        return finishOrderSendVoucherLimit;
    }

    public void setFinishOrderSendVoucherLimit(Integer finishOrderSendVoucherLimit) {
        this.finishOrderSendVoucherLimit = finishOrderSendVoucherLimit;
    }
}

