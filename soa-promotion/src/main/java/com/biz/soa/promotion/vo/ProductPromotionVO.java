package com.biz.soa.promotion.vo;

import com.biz.gbck.enums.product.promotion.PromotionOrientEnum;
import com.biz.gbck.enums.product.promotion.PromotionRoundEnum;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * 商品促销VO
 *
 * Created by david-liu on 2017/05/19 15:27.
 */
public class ProductPromotionVO implements Serializable {
    private static final long serialVersionUID = 4988202378530399647L;

    /**
     * 公司ID(谁发布的促销信息)
     */
    private Long companyId;

    /**
     * 促销的名称
     */
    private String name;

    /**
     * 促销的开始时间
     */
    private Timestamp startDate;

    /**
     * 促销结束的时间
     */
    private Timestamp endDate;

    /**
     * 促销的循环方式
     * <p>在促销生效的时间段内, 是每天都生效, 还是每个月的几号生效, 还是每周的周几生效</p>
     *
     * @see PromotionRoundEnum
     */
    private PromotionRoundEnum promotionRound;

    /**
     * 循环生效的时刻, 多个时刻以逗号分割开
     * <p>
     * 如果循环是每天都生效, 该字段为空
     * 如果循环是每月几号生效, 该字段为[1,31]之间的整数
     * 如果循环是每周周几生效, 改字段为[1, 7]之间的证书
     * </p>
     */
    private String roundCycle;

    /**
     * 在促销日, 促销开始生效的时间
     * <p>
     * 如果为空, 则取00:00:00.000开始
     * </p>
     */
    private Time effectStartTime;

    /**
     * 在促销日, 促销结束的时间
     * <p>
     * 如果为空, 则取23:59:59.999
     * </p>
     */
    private Time effectEndTime;

    /**
     * 促销面向类型(面向客户/面向客户组)
     */
    private PromotionOrientEnum orientType;

    /**
     * 面向的下级采购单位
     */
    private List<Long> orientedCompany;

    /**
     * 排除的下级采购单位
     */
    private List<Long> excludeCompany;

    /**
     * 面向的价格组
     */
    private List<Long> orientedPriceGroup;

    /**
     * 排除的价格组
     */
    private List<Long> excludePriceGroup;

    /**
     * 是否支持优惠券
     */
    private Boolean allowVoucher;

    /**
     * 是否互斥
     */
    private Boolean isExclusive;

    /**
     * 促销的执行单位
     */
    private List<Long> executeDepartment;

    /**
     * 面向的商品
     */
    private List<Long> orientedProducts;

    /**
     * 排除的商品
     */
    private List<Long> excludeProducts;

    /**
     * 面向的分类
     */
    private List<Long> orientedCategories;

    /**
     * 排除的分类
     */
    private List<Long> excludeCategories;

    /**
     * 促销描述
     */
    private String description;

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

    public String getRoundCycle() {
        return roundCycle;
    }

    public void setRoundCycle(String roundCycle) {
        this.roundCycle = roundCycle;
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

    public List<Long> getExecuteDepartment() {
        return executeDepartment;
    }

    public void setExecuteDepartment(List<Long> executeDepartment) {
        this.executeDepartment = executeDepartment;
    }

    public List<Long> getOrientedProducts() {
        return orientedProducts;
    }

    public void setOrientedProducts(List<Long> orientedProducts) {
        this.orientedProducts = orientedProducts;
    }

    public List<Long> getExcludeProducts() {
        return excludeProducts;
    }

    public void setExcludeProducts(List<Long> excludeProducts) {
        this.excludeProducts = excludeProducts;
    }

    public List<Long> getOrientedCategories() {
        return orientedCategories;
    }

    public void setOrientedCategories(List<Long> orientedCategories) {
        this.orientedCategories = orientedCategories;
    }

    public List<Long> getExcludeCategories() {
        return excludeCategories;
    }

    public void setExcludeCategories(List<Long> excludeCategories) {
        this.excludeCategories = excludeCategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
