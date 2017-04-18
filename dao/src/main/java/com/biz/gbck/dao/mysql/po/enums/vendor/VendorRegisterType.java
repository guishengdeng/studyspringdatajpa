package com.biz.gbck.dao.mysql.po.enums.vendor;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum VendorRegisterType implements EnumerableValue {

    FLAG_SHIP_STORE(0),//旗舰店: 商家以自有品牌或由权利人排他性品牌授权，入驻1919商城开设的店铺。
    MONOPOLY_STORE(1),//专卖店: 商家持商标权人品牌授权，入驻1919商城开设的店铺。
    SPECIALTY_STORE(2);//专营店: 经营同一品类下两个及以上他人或自有品牌商品的店铺。

    public static class Converter extends BaseEnumValueConverter<VendorRegisterType> {

    }

    private Integer value;

    private VendorRegisterType(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
