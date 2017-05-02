package com.biz.gbck.vo.app;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * app内置管理页面信息
 * Created by lzz on 2017/4/26.
 */
public class AppVo implements Serializable {

    private static final long serialVersionUID = -1447542223691552413L;

    private Long id;

    /*
     * 400电话
     */
    private String tel;

    /*
     *热搜索词
     */
    private String hotKeyWord;

    /*
     *首页遮罩图片URL
     */
    private String pictureUrl;

    /*
     *首页遮罩跳转URL
     */
    private String url;

    /*
    *红包标题
     */
    private String title;

    /*
    *红包提示的内容
     */
    private String content;

    /*
    *红包分享出去的图标
     */
    private String icon;

    /*
     *红包分享页url
     */
    private String shareUrl;

    /*
     *推荐有礼Url
     */
    private String recommedUrl;

    /*
    *app下载页面
    */
    private String appDownloadUrl;

    /*
     *订单满足 多少（元） 发券
     */

    private Integer amount;

    /*
    *首页搜索栏显示标签
     */
    private String tabOne;

    private String tabTwo;

    /*
     *20倍会员下单购买数量受限
     */
    private double minNum;

    private double maxNum;

    /*
     *受限类型
     */
    private String type;

    /*
     *提示
     */
    private boolean point;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHotKeyWord() {
        return hotKeyWord;
    }

    public void setHotKeyWord(String hotKeyWord) {
        this.hotKeyWord = hotKeyWord;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getRecommedUrl() {
        return recommedUrl;
    }

    public void setRecommedUrl(String recommedUrl) {
        this.recommedUrl = recommedUrl;
    }

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer money) {
        this.amount = money;
    }

    public String getTabOne() {
        return tabOne;
    }

    public void setTabOne(String tabOne) {
        this.tabOne = tabOne;
    }

    public String getTabTwo() {
        return tabTwo;
    }

    public void setTabTwo(String tabTwo) {
        this.tabTwo = tabTwo;
    }

    public double getMinNum() {
        return minNum;
    }

    public void setMinNum(double minNum) {
        this.minNum = minNum;
    }

    public double getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(double maxNum) {
        this.maxNum = maxNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPoint() {
        return point;
    }

    public void setPoint(boolean point) {
        this.point = point;
    }


}
