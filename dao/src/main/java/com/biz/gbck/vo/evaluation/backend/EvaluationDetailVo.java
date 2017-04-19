package com.biz.gbck.vo.evaluation.backend;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 后台管理评价详情vo
 *
 * @author yangzichun
 * @date 2017/2/8
 */
public class EvaluationDetailVo implements Serializable {

    private static final long serialVersionUID = 2014985735239668019L;

    //评价id
    private Long id;
    //商品名称
    private String productName;
    //关联订单号
    private String orderCode;
    //用户账号
    private String accountName;
    //服务态度评分
    private Integer attitudeScore;
    //物流速度评分
    private Integer logisticsScore;
    //描述相符评分
    private Integer descriptionScore;
    //评价内容
    private String content;
    //评价图片
    private List<String> images;
    //是否在前台显示评论,true为显示
    private String commonStatus;

    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(Integer attitudeScore) {
        this.attitudeScore = attitudeScore;
    }

    public Integer getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(Integer logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public Integer getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(Integer descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(String commonStatus) {
        this.commonStatus = commonStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
