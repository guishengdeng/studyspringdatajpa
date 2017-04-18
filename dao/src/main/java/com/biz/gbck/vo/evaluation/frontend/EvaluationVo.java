package com.biz.gbck.vo.evaluation.frontend;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 店铺获得评价返回的vo
 *
 * @author yangzichun
 * @date 2017/2/8
 */
public class EvaluationVo implements Serializable {

    private static final long serialVersionUID = 7175283776373106825L;

    // 评价id
    private Long id;
    // 用户昵称
    private String nickName;
    // 关联订单号
    private String orderCode;
    // 评价内容
    private String content;
    // 评价图片
    private List<String> images = newArrayList();
    // 商品id
    private Long productId;
    // 商品名称
    private String productName;
    // 评论时间
    private Timestamp evaluationTime;
    // 店铺id
    private Long vendorId;

    // 店铺名称
    private String vendorName;

    // 会员id
    private Long memberId;

    // 描述相符评分
    private Integer descriptionScore;

    // 物流评分
    private Integer logisticsScore;

    // 服务态度评分
    private Integer atitudeScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        if (StringUtils.isNotBlank(nickName)) {
            StringBuilder sb = new StringBuilder(nickName.substring(0, 1));
            sb.append("***").append(nickName.substring(nickName.length() - 1));
            nickName = sb.toString();
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Integer getAtitudeScore() {
        return atitudeScore;
    }

    public void setAtitudeScore(Integer atitudeScore) {
        this.atitudeScore = atitudeScore;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    @Override
    public String toString() {
        return "EvaluationVo{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", content='" + content + '\'' +
                ", images=" + images +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", evaluationTime=" + evaluationTime +
                ", vendorId=" + vendorId +
                ", vendorName='" + vendorName + '\'' +
                ", memberId=" + memberId +
                ", descriptionScore=" + descriptionScore +
                ", logisticsScore=" + logisticsScore +
                ", atitudeScore=" + atitudeScore +
                '}';
    }
}
