package com.biz.gbck.dao.mysql.po.payment;


import com.biz.gbck.common.NameAndValueAndDescriptionAbleEnum;
import com.biz.gbck.dao.mysql.po.NameAndValueAndDescriptionAbleEnumConverter;

public enum ZsgfApplyStatus implements NameAndValueAndDescriptionAbleEnum {

    WAIT_FOR_IMG_UPLOAD("等待上传图片", 1),

    AUDIT_FAILED("审核失败", 2),

    WAIT_FOR_AUDIT("待审核", 20),

    APPLY_SUCCESS("申请成功", 30);

    private Integer value;

    private String name;

    ZsgfApplyStatus(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return name;
    }

    public static class ZsgfApplyStatusConverter extends NameAndValueAndDescriptionAbleEnumConverter<ZsgfApplyStatus> {
    }
}
