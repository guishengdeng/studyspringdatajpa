package com.biz.gbck.dao.mysql.po.app;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * App内容管理
 *
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */
@Entity
@Table(name = "app_content")
public class App extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 9056063437136572968L;

    /*
     * 400电话
     */
    @Column(length = 150, nullable = false)
    private String tel;

    /*
     *热搜索词
     */
    @Column(length = 50)
    private String hotKeyWord;

    /*
     *首页遮罩图片URL
     */
    @Column(length = 200)
    private String pictureUrl;

    /*
     *首页遮罩跳转URL
     */
    @Column(length = 200)
    private String url;

    /*
    *红包标题
     */
    @Column(length = 100)
    private String title;

    /*
    *红包提示的内容
     */
    @Column(length = 100)
    private String content;

    /*
    *红包分享出去的图标
     */
    @Column(length = 100)
    private String icon;

    /*
     *红包分享页url
     */
    @Column(length = 200)
    private String shareUrl;

    /*
     *推荐有礼Url
     */
    @Column(length = 200)
    private String recommedUrl;

    /*
    *app下载页面
    */
    @Column(length = 200)
    private String appDownloadUrl;

    /*
     *订单满足 多少（元） 发券
     */
    @Column
    private Integer amount;

    /*
    *首页搜索栏显示标签
     */
    @Column(length = 150)
    private String tabOne;

    @Column(length = 150)
    private String tabTwo;

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

}
