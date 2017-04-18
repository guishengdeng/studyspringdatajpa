package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.EvaluationAddVo;
import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 评论po
 *
 * @author yangzichun
 * @date 2017/2/8
 */
@Entity
@Table(name = "evaluation")
public class Evaluation extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4452195718871842524L;
    //商品
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, optional = false, fetch =
            FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    //店铺（获取店铺名）
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    //关联订单号u
    @Column(length = 50)
    private String orderCode;

    //描述相符评分
    @Column(nullable = false)
    private Integer descriptionScore;

    //物流速度评分，用于统计店铺的总得分
    @Column(nullable = false)
    private Integer logisticsScore;

    //服务态度评分，用于统计店铺的总得分
    @Column(nullable = false)
    private Integer attitudeScore;

    //评价内容
    @Column(columnDefinition = "TEXT")
    private String content;

    //评价图片
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> images;

    //会员id
    private Long memberId;

    //是否在前台显示评论,默认DISABLE（value = 2）不显示
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum commonStatus = CommonStatusEnum.DISABLE;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public CommonStatusEnum getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(CommonStatusEnum commonStatus) {
        this.commonStatus = commonStatus;
    }

    /**
     * 拷贝评价 vo 的数据到评价 po
     */
    public void fromEvaluationAddVo(EvaluationAddVo evaluationAddVo) {
        this.setMemberId(evaluationAddVo.getMemberId());
        this.setImages(evaluationAddVo.getImages());
        this.setLogisticsScore(evaluationAddVo.getLogisticsScore());
        this.setAttitudeScore(evaluationAddVo.getAttitudeScore());
        this.setOrderCode(evaluationAddVo.getOrderCode());
        this.setDescriptionScore(evaluationAddVo.getDescriptionScore());
        this.setContent(evaluationAddVo.getContent());
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "product=" + product +
                ", vendor=" + vendor +
                ", orderCode='" + orderCode + '\'' +
                ", descriptionScore=" + descriptionScore +
                ", logisticsScore=" + logisticsScore +
                ", attitudeScore=" + attitudeScore +
                ", content='" + content + '\'' +
                ", images=" + images +
                ", memberId=" + memberId +
                ", commonStatus=" + commonStatus +
                '}';
    }
}
