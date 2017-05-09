package com.biz.gbck.dao.redis.ro.config;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

import java.io.Serializable;
import java.math.BigDecimal;

@Ro(key = "global:app_config")
@RoSortedSet(key = "list", score = "createTimestamp")
public class AppConfigRo  extends BaseRedisObject<String> implements Serializable {

    /**
     * (后台不维护)
     */
    private boolean registSkipUserInfo = false;

    /**
     * 400 电话
     * = "400 999 1919"
     */
    private String tel400= "400 999 1919" ;
    
    /**
     * 热门搜索词
     * = "茅台 五粮液 剑南春 泸州老窖 科罗娜 红花郎 1664 小企鹅"
     */

    private String hotKeywords = "茅台 五粮液 剑南春 泸州老窖 科罗娜 红花郎 1664 小企鹅";
    
    /**
     * 订单满足 多少（分） 发券
     */
    private Integer finishOrderSendVoucherLimit =1000;

    /**
     * 遮罩图片url
     */
    private String pictureUrl;

    /**
     *遮罩点击调整url
     */
    private String clickUrl;
    /**
     * 分享出去的图标ICON
     * shareMap.put("icon", "gbcklogo144x144.png");
     */
    private String icon= "share_logo144x144.png";
    /**
     * 红包标题
     *  shareMap.put("title", "分享推荐得红包");
     */
    private String title="分享推荐得红包";
    /**
     * 提示内容
     * shareMap.put("content", "隔壁仓库送您一个红包");
     */
    private String content="隔壁仓库送您一个红包";
    /**
     * 推荐有礼url
     */
    public String recommendUrl;

    /**
     * 红包分享url
     */
    public String shareUrl = "http://static.depotnextdoor.com/b2b/preview/h5/luckmoney.html";

    /**
     * app下载页面
     */
    public String appDownloadUrl = "http://static.depotnextdoor.com/b2b/preview/h5/newapp.html";

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

    public boolean isRegistSkipUserInfo() {
        return registSkipUserInfo;
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

	public Integer getFinishOrderSendVoucherLimit() {
		return finishOrderSendVoucherLimit;
	}

	public void setFinishOrderSendVoucherLimit(Integer finishOrderSendVoucherLimit) {
		this.finishOrderSendVoucherLimit = finishOrderSendVoucherLimit;
	}

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    /**
     * {@linkplain AppConfigRo#orderLimitForVIP20}
     */
    public Boolean getOrderLimitForVIP20() {
        return orderLimitForVIP20;
    }

    /**
     * {@linkplain AppConfigRo#orderLimitForVIP20}
     */
    public void setOrderLimitForVIP20(Boolean orderLimitForVIP20) {
        this.orderLimitForVIP20 = orderLimitForVIP20;
    }

    /**
     * {@linkplain AppConfigRo#minQuantityMultiplicand}
     */
    public BigDecimal getMinQuantityMultiplicand() {
        return minQuantityMultiplicand;
    }

    /**
     * {@linkplain AppConfigRo#minQuantityMultiplicand}
     */
    public void setMinQuantityMultiplicand(BigDecimal minQuantityMultiplicand) {
        this.minQuantityMultiplicand = minQuantityMultiplicand;
    }

    /**
     * {@linkplain AppConfigRo#maxQuantityMultiplicand}
     */
    public BigDecimal getMaxQuantityMultiplicand() {
        return maxQuantityMultiplicand;
    }

    /**
     * {@linkplain AppConfigRo#maxQuantityMultiplicand}
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
