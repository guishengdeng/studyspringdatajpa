package com.biz.gbck.enums.product;


import com.biz.core.enums.EnumerableValue;

/**
 * 商户商品审核状态
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public enum ProductAuditStatusEnum implements EnumerableValue {
    // 新建状态
    INIT(0, "待审核"),
    // 初始审核被拒绝
    INIT_DENY(5, "审核未通过"),
    // 已修改
    MODIFIED(10, "修改待审核"),
    // 修改审核被拒绝
    MODIFY_DENY(15, "修改审核未通过"),
    // 审核通过
    PASS(20, "审核通过");

    private int value;

    private String description;

    ProductAuditStatusEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
