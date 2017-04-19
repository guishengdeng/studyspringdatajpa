package com.biz.gbck.vo.product.frontend.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品请求Vo接口
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public interface IProductReqVo extends Serializable {

    String getWarehouseDepotCode();

    String getDepotCode();

    Long getGeoId();

    BigDecimal getLatitude();

    BigDecimal getLongitude();

    boolean isValid();

    String getInvalidMessage();
}
