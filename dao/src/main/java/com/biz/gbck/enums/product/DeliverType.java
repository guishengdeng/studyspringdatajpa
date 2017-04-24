package com.biz.gbck.enums.product;

/**
 * 配送方式
 *
 * @author david-liu
 * @date 2017年02月20日
 * @reviewer
 */
public enum DeliverType {
    // 商家自配送
    SHOP_OWNER_DELIVER("商家自配送"),
    // 门店配送
    DEPOT_DELIVER("门店配送"),
    // 省仓门店配送
    WAREHOUSE_DELIVER("省仓门店配送");

    private String description;

    DeliverType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
