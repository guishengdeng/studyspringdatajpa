package com.biz.gbck.dao.mysql.po.product.promotion;

import com.biz.gbck.enums.product.PromotionOrientEnum;
import com.biz.gbck.enums.product.PromotionRoundEnum;
import com.biz.support.jpa.converter.ListLongConverter;
import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

/**
 * 商品促销
 *
 * Created by david-liu on 2017/04/21 14:08.
 */
@MappedSuperclass
public class ProductPromotion extends BaseEntity {
    private static final long serialVersionUID = 8000046744483870135L;

    /**
     * 公司ID
     */
    @Column
    private Long companyId;

    /**
     * 促销名称
     */
    @Column(length = 50)
    private String name;

    /**
     * 促销开始时间
     */
    @Column(nullable = false)
    private Timestamp startDate;

    /**
     * 促销结束时间
     */
    @Column(nullable = false)
    private Timestamp endDate;

    /**
     * 促销循环方式
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PromotionRoundEnum promotionRound;

    /**
     * 活动开始时间(可选, 如果设置了代表当天活动生效的开始时间)
     */
    @Column
    private Time effectStartTime;

    /**
     * 活动结束时间(可选, 如果设置了代表当天活动生效的结束时间)
     */
    @Column
    private Time effectEndTime;

    /**
     * 促销面向类型(面向客户/面向客户组)
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private PromotionOrientEnum orientType;

    /**
     * 面向的下级采购单位
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    private List<Long> orientedCompany;

    /**
     * 排除的下级采购单位
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    private List<Long> excludeCompany;

    /**
     * 面向的价格组
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    private List<Long> orientedPriceGroup;

    /**
     * 排除的价格组
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    private List<Long> excludePriceGroup;

    /**
     * 是否支持优惠券
     */
    @Column
    private Boolean allowVoucher = Boolean.FALSE;

    /**
     * 是否互斥
     */
    @Column
    private Boolean isExclusive = Boolean.FALSE;

    /**
     * 执行单位(促销价和销售价的差价由谁买单)
     */
    @Column
    private String executeDepartment;

    /**
     * 所有能参与促销的商品编码集合
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private String orientedProducts;

    /**
     * 不能参与促销的商品编码集合
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private String excludeProducts;

    /**
     * 能参与促销的分类
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    private String orientedCategories;

    /**
     * 不能参与促销的分类
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    private String excludeCategories;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public PromotionRoundEnum getPromotionRound() {
        return promotionRound;
    }

    public void setPromotionRound(PromotionRoundEnum promotionRound) {
        this.promotionRound = promotionRound;
    }

    public Time getEffectStartTime() {
        return effectStartTime;
    }

    public void setEffectStartTime(Time effectStartTime) {
        this.effectStartTime = effectStartTime;
    }

    public Time getEffectEndTime() {
        return effectEndTime;
    }

    public void setEffectEndTime(Time effectEndTime) {
        this.effectEndTime = effectEndTime;
    }

    public PromotionOrientEnum getOrientType() {
        return orientType;
    }

    public void setOrientType(PromotionOrientEnum orientType) {
        this.orientType = orientType;
    }

    public List<Long> getOrientedCompany() {
        return orientedCompany;
    }

    public void setOrientedCompany(List<Long> orientedCompany) {
        this.orientedCompany = orientedCompany;
    }

    public List<Long> getExcludeCompany() {
        return excludeCompany;
    }

    public void setExcludeCompany(List<Long> excludeCompany) {
        this.excludeCompany = excludeCompany;
    }

    public List<Long> getOrientedPriceGroup() {
        return orientedPriceGroup;
    }

    public void setOrientedPriceGroup(List<Long> orientedPriceGroup) {
        this.orientedPriceGroup = orientedPriceGroup;
    }

    public List<Long> getExcludePriceGroup() {
        return excludePriceGroup;
    }

    public void setExcludePriceGroup(List<Long> excludePriceGroup) {
        this.excludePriceGroup = excludePriceGroup;
    }

    public Boolean getAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(Boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public Boolean getExclusive() {
        return isExclusive;
    }

    public void setExclusive(Boolean exclusive) {
        isExclusive = exclusive;
    }

    public String getExecuteDepartment() {
        return executeDepartment;
    }

    public void setExecuteDepartment(String executeDepartment) {
        this.executeDepartment = executeDepartment;
    }

    public String getOrientedProducts() {
        return orientedProducts;
    }

    public void setOrientedProducts(String orientedProducts) {
        this.orientedProducts = orientedProducts;
    }

    public String getExcludeProducts() {
        return excludeProducts;
    }

    public void setExcludeProducts(String excludeProducts) {
        this.excludeProducts = excludeProducts;
    }

    public String getOrientedCategories() {
        return orientedCategories;
    }

    public void setOrientedCategories(String orientedCategories) {
        this.orientedCategories = orientedCategories;
    }

    public String getExcludeCategories() {
        return excludeCategories;
    }

    public void setExcludeCategories(String excludeCategories) {
        this.excludeCategories = excludeCategories;
    }
}
