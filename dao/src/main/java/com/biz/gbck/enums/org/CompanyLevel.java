package com.biz.gbck.enums.org;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 公司层级
 *
 * @author: liubin
 * @date 4/20/17 15:21
 */
public enum CompanyLevel implements EnumerableValue {

    ORG_WAREHOUSE("隔壁仓库", 1),
    ORG_PLATFORM("平台省公司用户", 2),
    ORG_PARTNER("合作者", 3),
    ORG_SHOP("商户", 4);

    private String name;

    private int value;

    CompanyLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return name;
    }

    public static class Converter
            extends BaseEnumValueConverter<CompanyLevel> {
    }
}
