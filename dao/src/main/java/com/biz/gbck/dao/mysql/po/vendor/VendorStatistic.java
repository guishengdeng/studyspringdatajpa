package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.dao.mysql.po.product.Vendor;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * 店铺统计数据 Po
 *
 * @author lei
 * @date 2017年02月22日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ven_vendor_statistic")
public class VendorStatistic extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8959410362741036319L;

    /**
     * 店铺数据
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    //服务态度评分
    private Integer attitudeScore;

    //物流速度评分
    private Integer logisticsScore;

    //描述相符评分
    private Integer descriptionScore;

    //服务态度评论人数
    private Integer attitudeNum;

    //物流速度评分人数
    private Integer logisticsNum;

    //描述相符评分
    private Integer descriptionNum;

    public Integer getAttitudeNum() {
        return attitudeNum;
    }

    public void setAttitudeNum(Integer attitudeNum) {
        this.attitudeNum = attitudeNum;
    }

    public Integer getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(Integer attitudeScore) {
        this.attitudeScore = attitudeScore;
    }

    public Integer getDescriptionNum() {
        return descriptionNum;
    }

    public void setDescriptionNum(Integer descriptionNum) {
        this.descriptionNum = descriptionNum;
    }

    public Integer getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(Integer descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public Integer getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(Integer logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public Integer getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(Integer logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
