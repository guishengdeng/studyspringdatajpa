package com.biz.gbck.vo.app;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "400电话不能为空")
    private String tel;

    /*
     *热搜索词
     */
    @NotNull(message = "关键字不能为空")
    @NotBlank(message = "关键字不能为空")
    private String hotKeyWord;

    /*
     *首页遮罩图片URL
     */
    @NotNull(message = "首页遮罩图片URL不能为空")
    @NotBlank(message = "首页遮罩图片URL不能为空")
    private String pictureUrl;

    /*
     *首页遮罩跳转URL
     */
    @NotNull(message = "首页遮罩跳转URL不能为空")
    @NotBlank(message = "首页遮罩跳转URL不能为空")
    private String url;

    /*
    *红包标题
     */
    @NotNull(message = "红包标题不能为空")
    @NotBlank(message = "红包标题不能为空")
    private String title;

    /*
    *红包提示的内容
     */
    @NotNull(message = "红包提示内容不能为空")
    @NotBlank(message = "红包提示内容不能为空")
    private String content;

    /*
    *红包分享出去的图标
     */
    @NotNull(message = "红包分享出去的图标不能为空")
    @NotBlank(message = "红包分享出去的图标不能为空")
    private String icon;

    /*
     *红包分享页url
     */
    @NotNull(message = "红包分享页url不能为空")
    @NotBlank(message = "红包分享页url不能为空")
    private String shareUrl;

    /*
     *推荐有礼Url
     */
    @NotNull(message = "推荐有礼Url不能为空")
    @NotBlank(message = "推荐有礼Url不能为空")
    private String recommedUrl;

    /*
    *app下载页面
    */
    @NotNull(message = "app下载页面不能为空")
    @NotBlank(message = "app下载页面不能为空")
    private String appDownloadUrl;

    /*
     *订单满足 多少（元） 发券
     */
    @NotNull(message = "订单满足 多少（元） 发券不能为空")
    private Integer amount;

    /*
    *首页搜索栏显示标签
     */
    @NotNull(message = "首页搜索栏显示标签不能为空")
    @NotBlank(message = "首页搜索栏显示标签不能为空")
    private String tabOne;
    private String tabTwo;

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

}
