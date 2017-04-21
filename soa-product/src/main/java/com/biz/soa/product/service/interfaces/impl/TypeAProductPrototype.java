package com.biz.soa.product.service.interfaces.impl;

import com.biz.core.util.StringTool;
import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.gbck.dao.redis.ro.product.master.ProductRo;
import com.biz.gbck.enums.depot.PeriodsOfDeliveriesTime;
import com.biz.gbck.enums.product.DeliverType;
import com.biz.gbck.enums.product.ProductShowStatus;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.PropertyItemVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.gbck.vo.search.ProductIdxVo;
import com.biz.soa.product.service.interfaces.ProductPriceGenerator;
import com.biz.soa.product.service.interfaces.ProductValidator;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;

/**
 * 商品数据原型(商品信息包含价格信息和库存)
 *
 * @author david-liu
 * @date 2017年01月12日
 * @reviewer
 */
public class TypeAProductPrototype implements ProductPrototype {

    private static final long serialVersionUID = -5408321995338252190L;

    /**
     * A类商品价格生成器
     */
    private ProductPriceGenerator productPriceGenerator;

    /**
     * 商品 Ro
     */
    private ProductRo productRo;

    /**
     * 全国库存(A 类商品使用)
     */
    private Integer countryStock;

    /**
     * 价格 Ro
     */
    private PriceRo priceRo;

    /**
     * 商品验证器
     */
    private ProductValidator validator;

    /**
     * 是否开启快喝模式
     */
    private Boolean isOpenKuaiheMode;

    /**
     * 显示状态
     */
    private ProductShowStatus showStatus;

    /**
     * 构建商品数据原型
     *
     * @param productRo 商品 Ro(A 类商品, B 类商品必传)
     * @param countryStock 商品 Ro(A 类商品必传)
     * @param priceRo 价格 Ro(A 类商品和 B 类商品必传)
     * @param validator 商品验证规则
     */
    public TypeAProductPrototype(ProductRo productRo, Integer countryStock, PriceRo priceRo, ProductValidator validator, ProductShowStatus showStatus) {
        this.productRo = productRo;
        this.countryStock = countryStock;
        this.priceRo = priceRo;
        this.validator = validator;
        this.productPriceGenerator = TypeAProductPriceGenerator.doClone(priceRo);
        this.isOpenKuaiheMode = Boolean.FALSE;
        this.showStatus = showStatus;
    }

    public Long getProductId() {
        return this.productRo.getProductId();
    }

    public Long getVendorId() {
        return this.productRo.getVendorId();
    }

    public String getDepotCode() {
        return null;
    }

    public String getProductName() {
        return this.productRo.getName();
    }

    public Integer getProductType() {
        return this.productRo.getProductType();
    }

    public String getLogo() {
        return this.productRo.getLogo();
    }

    public Integer getMarketPrice() {
        return this.productPriceGenerator.doGetMarketPrice();
    }

    public Integer getFinalPrice(Integer userLevel) {
        return this.productPriceGenerator.doGetFinalPrice(userLevel);
    }

    public Integer getCountryStock() {
        return this.countryStock;
    }

    public TypeBProductStockVo getTypeBStockVo() {
        return null;
    }

    public Integer getSaleStatus() {
        return this.productRo.getSaleStatus();
    }

    public List<String> getApartTags() {
        return Lists.newArrayList();
    }

    @Override
    public Boolean validate(Integer userLevel, Boolean validateStock) {
        return this.validator.doValidate(this, userLevel, validateStock);
    }

    @Override
    public void validateWithException(Integer userLevel, Boolean validateStock) throws DepotNextDoorException {
        this.validator.doValidateWithException(this, userLevel, validateStock);
    }

    @Override
    public ProductRo getProductRo() {
        return this.productRo;
    }

    @Override
    public String getProductCode() {
        return this.productRo.getProductCode();
    }

    @Override
    public String getVendorProductCode() {
        return this.productRo.getVendorProductCode();
    }

    @Override
    public String getSubTitle() {
        return this.productRo.getSubTitle();
    }

    @Override
    public Long vendorId() {
        return this.productRo.getVendorId();
    }

    @Override
    public Integer getWarehouseDepotMarketPrice() {
        return null;
    }

    @Override
    public Integer getWarehouseDepotFinalPrice(String depotCode, Integer userLevel) {
        return null;
    }

    @Override
    public Long getBrandId() {
        return this.productRo.getBrandId();
    }

    @Override
    public Long getCategoryId() {
        return this.productRo.getCategoryId();
    }

    @Override
    public List<String> getProductImages() {
        if (StringUtils.isNotBlank(this.productRo.getImages())) {
            return StringTool.split(this.productRo.getImages(), ",");
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public Integer getWeight() {
        return this.productRo.getWeight();
    }

    @Override
    public String getPredictArrival() {
        return PeriodsOfDeliveriesTime.today.getTitle();
    }

    @Override
    public DeliverType getDeliverType() {
        return DeliverType.SHOP_OWNER_DELIVER;
    }

    @Override
    public String getCategoryName() {
        return Optional.fromNullable(this.productRo.getCategoryName()).or("");
    }

    @Override
    public String getBrandName() {
        return Optional.fromNullable(this.productRo.getBrandName()).or("");
    }

    @Override
    public Boolean getOpenKuaiheMode() {
        return isOpenKuaiheMode;
    }

    @Override
    public List<String> getIntroImages() {
        String introImages = Optional.fromNullable(productRo.getIntroImages()).or("");
        return Optional.fromNullable(StringTool.split(introImages, ",")).or(Lists.<String>newArrayList());
    }

    @Override
    public ProductIdxVo toProductIdx() {
        ProductIdxVo vo = new ProductIdxVo();
        vo.setId(String.format("%s%s", this.getVendorId(), this.getProductCode()));
        vo.setProductType(this.getProductType());
        vo.setProductId(String.valueOf(this.getProductId()));
        vo.setVendorId(this.getVendorId());
        vo.setName(this.getProductName());
        vo.setSubTitle(this.getSubTitle());
        vo.setProductCode(this.getProductCode());
        vo.setI18nCode(this.productRo.getI18nCode());
        vo.setBrief(this.productRo.getBreif());
        vo.setScore(this.productRo.getScore());
        vo.setTotalScore(this.productRo.getTotalScore());
        vo.setScoreCount(this.productRo.getScoreCount());
        vo.setSalesVolume(this.productRo.getSalesVolume());
        vo.setViewVolume(this.productRo.getViewVolume());
        vo.setCategory(this.productRo.getCategoryId());
        vo.setBrand(this.productRo.getBrandId());
        vo.setBrandName(this.productRo.getBrandName());
        vo.setSaleTagIds(this.productRo.getSaleTagIds());
        vo.setApartTagIds(this.productRo.getApartTagIds());
        vo.setFinalPrice(this.getFinalPrice(1));
        vo.setCostPrice(this.priceRo.getCostPrice());
        vo.setLimitPrice(this.priceRo.getMinPrice());
        vo.setMarketPrice(this.getMarketPrice());
        List<PropertyItemVo> propertyItemVos = this.productRo.getProperties();
        StringBuilder propertyStringBuilder = new StringBuilder();
        StringBuilder propertyTextStringBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(propertyItemVos)) {
            for (PropertyItemVo itemVo : propertyItemVos) {
                propertyStringBuilder.append(String.format("%s_%s", itemVo.getPropertyId(), itemVo.getPropertyValue())).append(',');
                propertyTextStringBuilder.append(String.format("%s_%s", itemVo.getPropertyName(), itemVo.getPropertyValue())).append(",");
            }
        }
        vo.setProperties(propertyStringBuilder.toString());
        vo.setPropertyTexts(propertyTextStringBuilder.toString());
        vo.setPrice1(this.getFinalPrice(1));
        vo.setPrice2(this.getFinalPrice(2));
        vo.setPrice3(this.getFinalPrice(3));
        vo.setPrice4(this.getFinalPrice(4));
        vo.setPrice5(this.getFinalPrice(5));
        vo.setPrice6(this.getFinalPrice(6));
        vo.setPrice7(this.getFinalPrice(7));
        vo.setPrice8(this.getFinalPrice(8));
        vo.setPrice9(this.getFinalPrice(9));
        vo.setPrice10(this.getFinalPrice(10));
        vo.setPrice11(this.getFinalPrice(11));
        vo.setPrice12(this.getFinalPrice(12));
        vo.setPrice13(this.getFinalPrice(13));
        vo.setPrice14(this.getFinalPrice(14));
        vo.setPrice15(this.getFinalPrice(15));
        vo.setPrice16(this.getFinalPrice(16));
        vo.setPrice17(this.getFinalPrice(17));
        vo.setPrice18(this.getFinalPrice(18));
        vo.setPrice19(this.getFinalPrice(19));
        vo.setPrice20(this.getFinalPrice(20));
        vo.setOnSaleTime(this.productRo.getOnSaleTime());
        vo.setStock(this.getCountryStock());
        if (StringUtils.isNotBlank(this.productRo.getGeoIds())) {
            vo.setGeoIds(this.productRo.getGeoIds().replace(",", "_"));
        }
        vo.setProductType(this.getProductType());
        vo.setSaleStatus(this.getSaleStatus());
        return vo;
    }

    @Override
    public ProductShowStatus getShowStatus() {
        if (this.showStatus != null) {
            return showStatus;
        }

        if (this.productRo.getSaleStatus() == SaleStatusEnum.OFF_SALE.getValue()) {
            return ProductShowStatus.OFF_SALE;
        }

        if (ValueUtils.getValue(this.getCountryStock()) <= 0) {
            return ProductShowStatus.INSUFFICIENT_STOCK;
        }

        return ProductShowStatus.NORMAL;
    }
}
