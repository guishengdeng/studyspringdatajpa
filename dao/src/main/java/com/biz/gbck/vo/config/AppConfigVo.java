package com.biz.gbck.vo.config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AppConfigVo {

    public boolean registSkipUserInfo;
    public String tel400;
    public String hotKeywords;

    /**
     * 订单满足 多少（分） 发券
     */
    public Integer finishOrderSendVoucherLimit;
    /**
     * 遮罩图片url
     */
    public String pictureUrl;

    /**
     * 遮罩点击调整url
     */
    public String clickUrl;
    /**
     * 图标
     * shareMap.put("icon", "gbcklogo144x144.png");
     */
    public String icon;
    /**
     * 红包标题
     * shareMap.put("title", "分享推荐得红包");
     */
    public String title;
    /**
     * 提示内容
     * shareMap.put("content", "隔壁仓库送您一个红包");
     */
    public String content;
    /**
     * 推荐有礼url
     */
    public String recommendUrl;

    /**
     * 红包分享url
     */
    public String shareUrl;

    public String appDownloadUrl;

    /**
     * 20倍会员下单限制
     */
    public Boolean orderLimitForVIP20 = false;

    /**
     * 最低起售数量倍数
     */
    public BigDecimal minQuantityMultiplicand = new BigDecimal("1");

    /**
     * 最大购买数量倍数
     */
    public BigDecimal maxQuantityMultiplicand = new BigDecimal("1");

    /**
     *首页搜索栏标签
     */
    public String searchPlaceHolder;
    /**
     * 首页搜索栏标签对应的tag
     */
    public String tags;

    public Map<String, String> getMap() {
        Map map = new HashMap<String, String>();
        map.put("tel400", tel400);  //400
        map.put("registSkipUserInfo", Boolean.toString(registSkipUserInfo)); //是否跳过注册后步骤
        map.put("hotKeywords", hotKeywords);  //热词
        map.put("finishOrderSendVoucherLimit", finishOrderSendVoucherLimit);
        map.put("share", this.getShareMap());
        map.put("notice", this.getNoticeMap());
        map.put("searchPlaceHolder", searchPlaceHolder);
        return map;
    }

    /**
     * 分享配置信息
     *
     * @return
     */
    public Map<String, String> getShareMap() {
        Map<String, String> shareMap = new HashMap<>();
        shareMap.put("icon", icon);
        shareMap.put("title", title);
        shareMap.put("content", content);
        shareMap.put("recommendUrl", recommendUrl);
        shareMap.put("shareUrl", shareUrl);
        return shareMap;
    }

    /**
     * 遮罩
     * jsonObj
     *
     * @return
     */
    public Map<String, String> getNoticeMap() {
        Map<String, String> noticeMap = new HashMap<>();
        noticeMap.put("pictureUrl", pictureUrl);
        noticeMap.put("clickUrl", clickUrl);
        return noticeMap;
    }


    public boolean isRegistSkipUserInfo() {
        return registSkipUserInfo;
    }

    public Integer getFinishOrderSendVoucherLimit() {
        return finishOrderSendVoucherLimit;
    }

    public void setFinishOrderSendVoucherLimit(Integer finishOrderSendVoucherLimit) {
        this.finishOrderSendVoucherLimit = finishOrderSendVoucherLimit;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getRecommendUrl() {
        return recommendUrl;
    }

    public void setRecommendUrl(String recommendUrl) {
        this.recommendUrl = recommendUrl;
    }

    public boolean getRegistSkipUserInfo() {
        return registSkipUserInfo;
    }

    public void setRegistSkipUserInfo(boolean registSkipUserInfo) {
        this.registSkipUserInfo = registSkipUserInfo;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    /**
     * {@linkplain AppConfigVo#orderLimitForVIP20}
     */
    public Boolean getOrderLimitForVIP20() {
        return orderLimitForVIP20;
    }

    /**
     * {@linkplain AppConfigVo#orderLimitForVIP20}
     */
    public void setOrderLimitForVIP20(Boolean orderLimitForVIP20) {
        this.orderLimitForVIP20 = orderLimitForVIP20;
    }

    /**
     * {@linkplain AppConfigVo#minQuantityMultiplicand}
     */
    public BigDecimal getMinQuantityMultiplicand() {
        return minQuantityMultiplicand;
    }

    /**
     * {@linkplain AppConfigVo#minQuantityMultiplicand}
     */
    public void setMinQuantityMultiplicand(BigDecimal minQuantityMultiplicand) {
        this.minQuantityMultiplicand = minQuantityMultiplicand;
    }

    /**
     * {@linkplain AppConfigVo#maxQuantityMultiplicand}
     */
    public BigDecimal getMaxQuantityMultiplicand() {
        return maxQuantityMultiplicand;
    }

    /**
     * {@linkplain AppConfigVo#maxQuantityMultiplicand}
     */
    public void setMaxQuantityMultiplicand(BigDecimal maxQuantityMultiplicand) {
        this.maxQuantityMultiplicand = maxQuantityMultiplicand;
    }

    public String getSearchPlaceHolder() {
        return searchPlaceHolder;
    }

    public void setSearchPlaceHolder(String searchPlaceHolder) {
        this.searchPlaceHolder = searchPlaceHolder;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

