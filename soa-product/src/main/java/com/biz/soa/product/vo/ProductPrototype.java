package com.biz.soa.product.vo;

import com.biz.core.util.StringTool;
import com.biz.gbck.dao.redis.ro.product.master.ProductRO;
import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.dao.redis.ro.product.promotion.SimpleSpecialOfferPromotionRO;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductFieldVo;
import com.biz.gbck.vo.product.gbck.response.ProductItemVO;
import com.biz.gbck.vo.search.ProductIdxVO;
import com.biz.gbck.vo.stock.ProductStockVO;
import com.biz.soa.product.service.interfaces.ProductPriceGenerator;
import com.biz.soa.product.service.interfaces.impl.DepotNextDoorPriceGenerator;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Optional;
import org.codelogger.utils.ValueUtils;

/**
 * 商品原型数据VO
 *
 * Created by david-liu on 2017/05/02 11:59.
 */
public class ProductPrototype implements Serializable {
    private static final long serialVersionUID = -5408321995338252190L;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 商品RO
     */
    private ProductRO productRO;

    /**
     * 价格RO
     */
    private PriceRO priceRO;

    /**
     * 库存VO
     */
    private ProductStockVO stockVO;

    /**
     * 商品价格生成策略
     */
    private ProductPriceGenerator priceGenerator;

    public ProductPrototype(Long priceGroupId, ProductRO productRO, PriceRO priceRO, ProductStockVO stockVO, SimpleSpecialOfferPromotionRO simpleSpecialOfferPromotionRO) {
        this.priceGroupId = priceGroupId;
        this.productRO = productRO;
        this.priceRO = priceRO;
        this.stockVO = stockVO;
        priceGenerator = new DepotNextDoorPriceGenerator();
        priceGenerator.setPriceRO(priceRO);
        priceGenerator.setSimpleSpecialOfferPromotionRO(simpleSpecialOfferPromotionRO);
    }

    public ProductRO getProductRO() {
        return productRO;
    }

    public void setProductRO(ProductRO productRO) {
        this.productRO = productRO;
    }

    public PriceRO getPriceRO() {
        return priceRO;
    }

    public void setPriceRO(PriceRO priceRO) {
        this.priceRO = priceRO;
    }

    public ProductStockVO getStockVO() {
        return stockVO;
    }

    public void setStockVO(ProductStockVO stockVO) {
        this.stockVO = stockVO;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public ProductItemVO toProductItemVO() {
        ProductItemVO itemVO = new ProductItemVO();
        itemVO.setProductName(this.productRO.getName());
        itemVO.setProductCode(this.productRO.getProductCode());
        itemVO.setCategory(new ProductFieldVo<>(this.productRO.getCategoryId(), this.productRO.getCategoryName()));
        itemVO.setBrand(new ProductFieldVo<>(this.productRO.getBrandId(), this.productRO.getBrandName()));
        // TODO 添加商品促销字段
        itemVO.setLogo(this.productRO.getLogo());
        itemVO.setSalePrice(this.priceGenerator.getSalePrice());
        itemVO.setSuggestSalePrice(this.priceGenerator.getSuggestPrice());
        itemVO.setTags(StringTool.split(this.productRO.getSaleTags(), ","));
        return itemVO;
    }

    public ProductAppDetailRespVO toAppDetailRespVO() {
        ProductAppDetailRespVO respVO = new ProductAppDetailRespVO();
        if (ValueUtils.getValue(this.stockVO.getStock()) <= 0) {
            return null;
        }
        respVO.setProductCode(this.productRO.getProductCode());
        respVO.setProductName(this.productRO.getName());
        respVO.setBrief(this.productRO.getBreif());
        respVO.setStock(this.stockVO.getStock());
        respVO.setSalePrice(this.priceGenerator.getSalePrice());
        respVO.setSuggestPrice(this.priceGenerator.getSuggestPrice());
        respVO.setMarketPrice(this.priceGenerator.getPurchasePrice());
        respVO.setMinQuantity(this.productRO.getMinQuantity());
        respVO.setProperties(null);
        respVO.setLogo(this.productRO.getLogo());
        respVO.setImages(StringTool.split(this.productRO.getImages(), ","));
        respVO.setIntroImages(StringTool.split(this.productRO.getIntroImages(), ","));
        return respVO;
    }

    public ProductIdxVO toProductIdxVO() {
        ProductIdxVO idxVO = new ProductIdxVO();
        idxVO.setId(String.format("%s%s", this.productRO.getProductCode(), this.priceGroupId));
        idxVO.setProductId(this.productRO.getProductId());
        idxVO.setPriceGroup(this.priceGroupId);
        idxVO.setStock(this.stockVO.getStock());
        idxVO.setSalePrice(this.priceGenerator.getSalePrice());
        idxVO.setDynamicAveragePrice(this.priceGenerator.getDynamicAveragePrice());
        idxVO.setName(this.productRO.getName());
        idxVO.setSubTitle(this.productRO.getSubTitle());
        idxVO.setProductCode(this.productRO.getProductCode());
        idxVO.setI18nCode(this.productRO.getI18nCode());
        idxVO.setBrand(String.valueOf(this.productRO.getBrandId()));
        idxVO.setCategory(String.valueOf(this.productRO.getCategoryId()));
        StringBuilder propertyStringBuilder = new StringBuilder();
        Optional.ofNullable(this.productRO.getProperties()).orElse(Lists.newArrayList())
                .forEach(propertyItemVo -> propertyStringBuilder.append(String.format("%s_%s", propertyItemVo.getPropertyId(), propertyItemVo.getPropertyValue())).append(','));
        idxVO.setProperties(propertyStringBuilder.toString());
        idxVO.setSaleTags(this.productRO.getSaleTagIds());
        idxVO.setApartTags(this.productRO.getApartTagIds());
        idxVO.setControlByQRCode(this.productRO.getControlByQRCode());
        idxVO.setCircularFlow(this.productRO.getCircularFlow());
        idxVO.setRapidProduct(this.productRO.getRapidProduct());
        idxVO.setSaleStatus(this.productRO.getSaleStatus());
        idxVO.setSalesVolume(this.productRO.getSalesVolume());
        return idxVO;
    }
}
