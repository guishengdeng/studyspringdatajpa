package com.biz.gbck.vo.evaluation.frontend;

import java.io.Serializable;
import java.util.List;

/**
 * 前台添加评价vo
 *
 * @author yangzichun
 * @date 2017/2/18
 */
public class EvaluationAddVo implements Serializable {
    //评价id
    private Long id;
    //店铺id
    private Long vendorId;
    //商品id
    private Long productId;
    //会员id
    private Long memberId;
    //关联订单号
    private String orderCode;
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
    //评价商品中台编号
    private String productCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
}
