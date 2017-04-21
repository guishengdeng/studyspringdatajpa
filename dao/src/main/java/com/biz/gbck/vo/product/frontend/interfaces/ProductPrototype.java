package com.biz.gbck.vo.product.frontend.interfaces;

import com.biz.gbck.dao.redis.ro.product.master.ProductRo;
import com.biz.gbck.enums.product.DeliverType;
import com.biz.gbck.enums.product.ProductShowStatus;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.search.ProductIdxVo;
import java.io.Serializable;
import java.util.List;

/**
 * 商品原型数据接口
 *
 * @author david-liu
 * @date 2017年01月17日
 * @reviewer
 */
public interface ProductPrototype extends Serializable {
    Long getProductId();

    Long getVendorId();

    String getDepotCode();

    String getProductName();

    Integer getProductType();

    String getLogo();

    Integer getMarketPrice();

    Integer getFinalPrice(Integer userLevel);

    Integer getCountryStock();

    ProductRo getProductRo();

    TypeBProductStockVo getTypeBStockVo();

    Integer getSaleStatus();

    List<String> getApartTags();

    Boolean validate(Integer userLevel, Boolean validateStock);

    void validateWithException(Integer userLevel, Boolean validateStock) throws DepotNextDoorException;

    Integer getWarehouseDepotMarketPrice();

    Integer getWarehouseDepotFinalPrice(String depotCode, Integer userLevel);

    String getProductCode();

    String getVendorProductCode();

    String getSubTitle();

    Long vendorId();

    ProductIdxVo toProductIdx();

    Long getBrandId();

    Long getCategoryId();

    List<String> getProductImages();

    Integer getWeight();

    String getPredictArrival();

    DeliverType getDeliverType();

    String getCategoryName();

    String getBrandName();

    List<String> getIntroImages();

    Boolean getOpenKuaiheMode();

    ProductShowStatus getShowStatus();
}
