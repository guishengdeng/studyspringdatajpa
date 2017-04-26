package com.biz.gbck.vo.application;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * app内置管理页面信息
 * Created by lzz on 2017/4/26.
 */
public class AppVo implements Serializable{

    private static final long serialVersionUID = -1447542223691552413L;

    private  String id;

    /*
     * 400电话
     */
    @Column(length=50, nullable=false)
    private String tel;

    /*
     *热搜索词
     */
    @Column(length=50)
    private String hotKeyWord;

    /*
     *首页遮罩图片URL
     */
    @Column(length=100)
    private String pictureUrl;

    /*
     *首页遮罩跳转URL
     */
    @Column(length=100)
    private String url;

    /*
    *红包标题
     */
    @Column(length=80)
    private String title;

    /*
    *红包提示的内容
     */
    @Column(length=100)
    private String  content;

    /*
    *红包分享出去的图标
     */
    @Column(length=50)
    private String icon;

    /*
     *红包分享页url
     */
    @Column(length=50)
    private  String  shareUrl;

    /*
     *推荐有礼Url
     */
    @Column(length=50)
    private String recommedUrl;

    /*
    *app下载页面
    */
    @Column(length=50)
    private String appDownloadUrl;

    /*
     *订单满足 多少（元） 发券
     */
    @Column
    private Integer money;

    /*
    *首页搜索栏显示标签
     */
    @Column(length=50)
    private String tabOne;

    @Column(length=50)
    private String tabTwo;

    /*
     *20倍会员下单购买数量受限
     */
    @Column(length=50)
    private double minNum;

    @Column(length=50)
    private double maxNum;

    /*
     *受限类型
     */
    @Column(nullable=false)
    private String type;

    /*
     *提示
     */
    @Column(length=50)
    private boolean point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
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
