package com.biz.gbck.dao.mysql.po.qrcode.enums;

import com.biz.core.enums.EnumerableValue;

public enum BusinessStatusEnum implements EnumerableValue{
    STATUS0(0,"G7","收货");

    private int value;//值
    private String code;//编码
    private String desc;//描述
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    BusinessStatusEnum(int value,String code,String desc){
        this.value=value;
        this.code=code;
        this.desc=desc;
    }
}
