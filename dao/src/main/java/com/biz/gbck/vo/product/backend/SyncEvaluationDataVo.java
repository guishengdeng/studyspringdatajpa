package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 导入商品评价的Vo
 *
 * @author zhangcheng
 * @date 2017/3/23
 * @reviewer
 * @see
 */
public class SyncEvaluationDataVo implements Serializable {

    private static final long serialVersionUID = -721065492229860294L;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 店铺名称
     */
    private String vendorName;

    /**
     * 中台商品编码
     */
    private String productCode;

    /**
     * 老官网关联订单号
     */
    private String oldBBCOrderCode;

    /**
     * 描述相符评分
     */
    private String descriptionScore;

    /**
     * 物流速度评分
     */
    private String logisticsScore;

    /**
     * 服务态度评分
     */
    private String attitudeScore;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价图片
     */
    private List<String> images;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 该评价是否显示的状态(如果该评价为显示：1，如果该评价不显示：2)
     */
    private String showStatus;

    /**
     * 该评论的评论时间
     */
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getOldBBCOrderCode() {
        return oldBBCOrderCode;
    }

    public void setOldBBCOrderCode(String oldBBCOrderCode) {
        this.oldBBCOrderCode = oldBBCOrderCode;
    }

    public String getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(String descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public String getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(String logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public String getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(String attitudeScore) {
        this.attitudeScore = attitudeScore;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
