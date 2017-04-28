package com.biz.gbck.dao.mysql.po.qrcode.enums;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;
import com.biz.core.enums.converter.BaseEnumNameAndValueAndDescriptionConverter;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;

public enum BusinessStatusEnum implements EnumerableNameAndValueAndDescription{
    STATUS0(0,"G7","收货");

    private Integer value;//值
    private String name;//编码
    private String description;//描述
    
    public static class Converter extends BaseEnumNameAndValueAndDescriptionConverter<SaleStatusEnum> {

    }
    
    BusinessStatusEnum(Integer value,String name,String description){
        this.value=value;
        this.name=name;
        this.description=description;
    }
    @Override
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }
    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }
}
