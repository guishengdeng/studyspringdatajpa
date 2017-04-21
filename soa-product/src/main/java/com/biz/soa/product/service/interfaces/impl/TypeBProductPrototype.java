package com.biz.soa.product.service.interfaces.impl;

import com.biz.core.util.StringTool;
import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.gbck.dao.redis.ro.product.master.ProductRo;
import com.biz.gbck.enums.depot.PeriodsOfDeliveriesTime;
import com.biz.gbck.enums.product.DeliverType;
import com.biz.gbck.enums.product.ProductShowStatus;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.depot.DepotPromotionVo;
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
 * B类商品商品数据原型
 *
 * @author david-liu
 * @date 2017年01月17日
 * @reviewer
 * @see
 */
public class TypeBProductPrototype implements ProductPrototype {
    private static final long serialVersionUID = -40107377464669629L;

    /**
     * 商品门店价格生成器
     */
    private ProductPriceGenerator depotProductPriceGenerator;

    /**
     * 商品省仓门店价格生成器
     */
    private ProductPriceGenerator warehouseDepotProductPriceGenerator;

    /**
     * 商品Ro
     */
    private ProductRo productRo;

    /**
     * 价格 Ro
     */
    private PriceRo depotPriceRo;

    /**
     * 省仓门店价格 Ro
     */
    private PriceRo warehouseDepotPriceRo;

    /**
     * B 类商品库存
     */
    private TypeBProductStockVo stockVo;

    /**
     * 商品验证器
     */
    private ProductValidator validator;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;

    /**
     * 当前定位和就近门店的距离
     */
    private Integer distance;

    /**
     * 是否开启快喝模式
     */
    private Boolean isOpenKuaiheMode;

    /**
     * B类商品数据原型构造函数
     *
     * @param productRo 商品Ro
     * @param depotPriceRo 门店价格Ro
     * @param warehouseDepotPriceRo 省仓门店价格Ro
     * @param stockVo B类商品库存Vo
     * @param depotPromotionVo 门店简单特价
     * @param warehouseDepotPromotionVo 省仓门店简单特价
     * @param validator 验证器
     */
    public TypeBProductPrototype(String depotCode, String warehouseDepotCode,
                                 ProductRo productRo, PriceRo depotPriceRo,
                                 PriceRo warehouseDepotPriceRo, TypeBProductStockVo stockVo,
                                 DepotPromotionVo depotPromotionVo, DepotPromotionVo warehouseDepotPromotionVo,
                                 Integer distance, ProductValidator validator) {
        this.depotCode = depotCode;
        this.warehouseDepotCode = warehouseDepotCode;
        this.productRo = productRo;
        this.depotPriceRo = depotPriceRo;
        this.warehouseDepotPriceRo = warehouseDepotPriceRo;
        this.stockVo = stockVo;
        this.validator = validator;
        this.depotProductPriceGenerator = TypeBProductPriceGenerator.doClone(depotPriceRo, depotPromotionVo);
        this.warehouseDepotProductPriceGenerator = TypeBProductPriceGenerator.doClone(warehouseDepotPriceRo, warehouseDepotPromotionVo);
        this.distance = distance;
        this.isOpenKuaiheMode = StringUtils.isNotBlank(depotCode);
    }

    @Override
    public Long getProductId() {
        return this.productRo.getProductId();
    }

    @Override
    public Long getVendorId() {
        return this.productRo.getVendorId();
    }

    @Override
    public String getDepotCode() {
        return this.depotCode;
    }

    @Override
    public String getProductName() {
        return this.productRo.getName();
    }

    @Override
    public Integer getProductType() {
        return this.productRo.getProductType();
    }

    @Override
    public String getLogo() {
        return this.productRo.getLogo();
    }

    @Override
    public Integer getMarketPrice() {
        return StringUtils.isBlank(this.depotCode) ? null : this.depotProductPriceGenerator.doGetMarketPrice();
    }

    @Override
    public Integer getFinalPrice(Integer userLevel) {
        return StringUtils.isBlank(this.depotCode) ? null : this.depotProductPriceGenerator.doGetFinalPrice(userLevel);
    }

    @Override
    public Integer getCountryStock() {
        return null;
    }

    @Override
    public TypeBProductStockVo getTypeBStockVo() {
        return this.stockVo;
    }

    @Override
    public Integer getSaleStatus() {
        return this.productRo.getSaleStatus();
    }

    @Override
    public List<String> getApartTags() {
        return Lists.newArrayList();
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
    public List<String> getIntroImages() {
        String introImages = Optional.fromNullable(productRo.getIntroImages()).or("");
        return Optional.fromNullable(StringTool.split(introImages, ",")).or(Lists.<String>newArrayList());
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
    public Integer getWarehouseDepotMarketPrice() {
        return this.warehouseDepotProductPriceGenerator.doGetMarketPrice();
    }

    @Override
    public Integer getWarehouseDepotFinalPrice(String depotCode, Integer userLevel) {
        if (this.warehouseDepotProductPriceGenerator.doGetFinalPrice(userLevel) == null) {
            return null;
        } else {
            if (this.getOpenKuaiheMode()) {
                if (this.depotProductPriceGenerator.doGetFinalPrice(userLevel) == null) {
                    return null;
                }
            } else {
                return this.warehouseDepotProductPriceGenerator.doGetFinalPrice(userLevel);
            }
            return Math.max(this.depotProductPriceGenerator.doGetFinalPrice(userLevel),
                    this.warehouseDepotProductPriceGenerator.doGetFinalPrice(userLevel));
        }
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
    public Boolean getOpenKuaiheMode() {
        return isOpenKuaiheMode;
    }

    @Override
    public String getPredictArrival() {
        if (StringUtils.isNotBlank(this.depotCode) &&
                ValueUtils.getValue(this.stockVo.getDepotStock()) > 0 &&
                ValueUtils.getValue(this.distance) >= 0) {
            if (PeriodsOfDeliveriesTime.now.supported(this.distance)) {
                return PeriodsOfDeliveriesTime.now.getTitle();
            } else if (PeriodsOfDeliveriesTime.half_hour.supported(this.distance)) {
                return PeriodsOfDeliveriesTime.half_hour.getTitle();
            } else if (PeriodsOfDeliveriesTime.one_hour.supported(this.distance)) {
                return PeriodsOfDeliveriesTime.one_hour.getTitle();
            } else {
                return PeriodsOfDeliveriesTime.today.getTitle();
            }
        } else {
            return PeriodsOfDeliveriesTime.today.getTitle();
        }
    }

    @Override
    public DeliverType getDeliverType() {
        TypeBProductStockVo stockVo = this.stockVo;
        if (ValueUtils.getValue(stockVo.getDepotStock()) > 0) {
            return DeliverType.DEPOT_DELIVER;
        } else {
            return DeliverType.WAREHOUSE_DELIVER;
        }
    }

    @Override
    public ProductIdxVo toProductIdx() {
        ProductIdxVo vo = new ProductIdxVo();
        PriceRo priceRo;
        Integer stock;
        if (StringUtils.equals(this.depotCode, this.warehouseDepotCode)) {
            priceRo = warehouseDepotPriceRo;
            stock = this.stockVo.getProvinceStock();
        } else {
            if (ValueUtils.getValue(this.stockVo.getDepotStock()) > 0) {
                priceRo = this.depotPriceRo;
                stock = this.stockVo.getDepotStock();
            } else {
                priceRo = this.warehouseDepotPriceRo;
                stock = this.stockVo.getProvinceStock();
            }
        }
        vo.setId(String.format("%s%s%s", this.getVendorId(), this.getDepotCode(), this.getProductCode()));
        vo.setProductId(String.valueOf(this.getProductId()));
        vo.setVendorId(this.getVendorId());
        vo.setName(this.getProductName());
        vo.setSubTitle(this.getSubTitle());
        vo.setProductCode(this.getProductCode());
        vo.setBrandName(this.getBrandName());
        vo.setI18nCode(this.productRo.getI18nCode());
        vo.setBrief(this.productRo.getBreif());
        vo.setScore(this.productRo.getScore());
        vo.setTotalScore(this.productRo.getTotalScore());
        vo.setScoreCount(this.productRo.getScoreCount());
        vo.setSalesVolume(this.productRo.getSalesVolume());
        vo.setViewVolume(this.productRo.getViewVolume());
        vo.setCategory(this.productRo.getCategoryId());
        vo.setBrand(this.productRo.getBrandId());
        vo.setSaleTagIds(this.productRo.getSaleTagIds());
        vo.setApartTagIds(this.productRo.getApartTagIds());

        vo.setCostPrice(priceRo.getCostPrice());
        vo.setLimitPrice(priceRo.getMinPrice());
        vo.setMarketPrice(priceRo.getMarketPrice());
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
        vo.setFinalPrice(this.getFinalPrice(1));
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
        vo.setDepotCode(this.getDepotCode());
        vo.setStock(stock);
        vo.setSaleStatus(this.getSaleStatus());
        vo.setProductType(VendorTypeEnum.TYPE_B.getValue());
        return vo;
    }

    @Override
    public ProductShowStatus getShowStatus() {
        if (this.productRo.getSaleStatus() == SaleStatusEnum.OFF_SALE.getValue()) {
            return ProductShowStatus.OFF_SALE;
        }

        if (StringUtils.isBlank(this.depotCode)) {
            if (this.warehouseDepotPriceRo == null) {
                return ProductShowStatus.NOT_IN_SALE_AREA;
            }
        } else {
            if (this.depotPriceRo == null || this.warehouseDepotPriceRo == null) {
                return ProductShowStatus.NOT_IN_SALE_AREA;
            }
        }

        if (this.stockVo == null || (ValueUtils.getValue(this.stockVo.getDepotStock()) <= 0 && ValueUtils.getValue(this.stockVo.getProvinceStock()) <= 0)) {
            return ProductShowStatus.INSUFFICIENT_STOCK;
        }
        return ProductShowStatus.NORMAL;
    }
}
