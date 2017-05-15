package com.biz.core.ali.oss;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 返回上传空间bucket集合
 * Created by dylan on 17-5-15.
 */
public class BucketResVo {
    private String type;//类型名称
    private String name;//bucket名称
    public BucketResVo() {
    }

    public BucketResVo(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
