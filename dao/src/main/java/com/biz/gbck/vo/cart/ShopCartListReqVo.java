package com.biz.gbck.vo.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 购物车列表reqVo
 *
 * @author lei
 * @date 2017/04/22
 */
public class ShopCartListReqVo extends BaseShopCartReqVo {

    private static final long serialVersionUID = 2010073680708198043L;

    @JsonIgnore
    private boolean showDetail = true;

    public boolean isShowDetail() {
        return showDetail;
    }

    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
    }

    public ShopCartListReqVo() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
