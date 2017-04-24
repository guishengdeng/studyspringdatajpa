package com.biz.gbck.dao.redis.ro.product;

import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yangzichun
 * @date 2017/2/9
 */
@Ro(key = "product:EvaluationRo")
@RoSortedSet(key = "list", score = "evaluationTime")
public class EvaluationRo extends BaseRedisObject<Long> implements Serializable {
    private static final long serialVersionUID = 7965176419294948828L;
    //商品id
    private Long productId;
    //商品名称
    private String productName;
    //店铺id
    @FieldSortedSet(key = "vendorId", score = "evaluationTime")
    private Long vendorId;
    //店铺名称
    private String vendorName;
    //会员id
    private Long memberId;
    //会员账号
    private String accountName;
    //评论时间
    private Timestamp evaluationTime;
    //描述相符评分
    private Integer descriptionScore;
    //物流评分
    private Integer logisticsScore;
    //服务态度评分
    private Integer atitudeScore;
    //评论内容
    private String content;
    //评论图片
    private String images;
    //关联订单号
    private String orderCode;
    //商品编号
    @FieldSortedSet(key = "productCode", score = "evaluationTime")
    private String productCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Integer getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(Integer descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public Integer getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(Integer logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public Integer getAtitudeScore() {
        return atitudeScore;
    }

    public void setAtitudeScore(Integer atitudeScore) {
        this.atitudeScore = atitudeScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "EvaluationRo [productId=" + productId + ", productName=" + productName + ", vendorId=" + vendorId
                + ", vendorName=" + vendorName + ", memberId=" + memberId + ", accountName=" + accountName
                + ", evaluationTime=" + evaluationTime + ", descriptionScore=" + descriptionScore + ", logisticsScore="
                + logisticsScore + ", atitudeScore=" + atitudeScore + ", content=" + content + ", images=" + images
                + ", orderCode=" + orderCode + "]";
    }

}
