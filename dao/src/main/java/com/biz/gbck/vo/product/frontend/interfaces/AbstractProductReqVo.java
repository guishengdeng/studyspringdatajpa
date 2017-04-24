package com.biz.gbck.vo.product.frontend.interfaces;

import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;

/**
 * 商品请求Vo抽象类
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public abstract class AbstractProductReqVo implements IProductReqVo {
    private static final long serialVersionUID = -8146969789090490279L;

    public boolean isValid() {
        boolean isWarehouseDepotCodeValid = StringUtils.isNotBlank(this.getWarehouseDepotCode());
        boolean isLocationValid = ValueUtils.getValue(this.getGeoId()) > 0 &&
                this.getLatitude() != null && this.getLongitude() != null;
        return isWarehouseDepotCodeValid && isLocationValid;
    }

    @Override
    public String getInvalidMessage() {
        boolean isWarehouseDepotCodeValid = StringUtils.isNotBlank(this.getWarehouseDepotCode());
        boolean isLocationValid = ValueUtils.getValue(this.getGeoId()) > 0 &&
                this.getLatitude() != null && this.getLongitude() != null;
        if (!isWarehouseDepotCodeValid) {
            return "省仓门店编码不能为空";
        } else if (!isLocationValid) {
            if (ValueUtils.getValue(this.getGeoId()) <= 0) {
                return "传入参数geoId不能为空";
            } else {
                return "必须传入定位的经纬度信息";
            }
        } else {
            return "必须传入推荐类型";
        }
    }
}
