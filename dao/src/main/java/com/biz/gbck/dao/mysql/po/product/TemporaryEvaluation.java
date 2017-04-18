package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 该Po为评价原始数据，主要用于评价数据导入时使用，正式的评价数据不用该Po
 *
 * @author zhangcheng
 * @date 2017/3/24
 * @reviewer
 * @see
 */
@Entity
@Table(name = "temporary_evaluation")
public class TemporaryEvaluation extends BaseEntity {

    /**
     * 商品名称
     */
    @Column
    private String productName;

    /**
     * 店铺名称
     */
    @Column
    private String vendorName;

    /**
     * 中台商品编码
     */
    @Column(nullable = false)
    private String productCode;

    /**
     * 老官网关联订单号
     */
    @Column
    private String oldBBCOrderCode;

    /**
     * 描述相符评分
     */
    @Column(nullable = false)
    private Integer descriptionScore;

    /**
     * 物流速度评分
     */
    @Column(nullable = false)
    private Integer logisticsScore;

    /**
     * 服务态度评分
     */
    @Column(nullable = false)
    private Integer attitudeScore;

    /**
     * 评价内容
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 评价图片
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> images;

    /**
     * 会员中台id
     */
    @Column
    private Long memberId;

    /**
     * 该评价是否显示的状态(如果该评价为显示：1，如果该评价不显示：2)
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum showStatus;

    /**
     * 评论时间
     */
    @Column
    private Timestamp createTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
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

    public Integer getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(Integer attitudeScore) {
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public CommonStatusEnum getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(CommonStatusEnum showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
