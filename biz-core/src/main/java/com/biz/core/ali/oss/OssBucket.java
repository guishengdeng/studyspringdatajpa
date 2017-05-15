package com.biz.core.ali.oss;


/**
 * oss bucket
 */
public enum OssBucket {

    PRODUCT("product","biz.oss.productBucketName"),
    AUDIT("audit","biz.oss.auditBucketName");


    private final String type,name;//type:图片类型 name:bucket空间名称

    OssBucket(String type, String name) {
        this.type = type;
        this.name = name;

    }

    public String getType() {
      return type;
    }

    public String getName() {
        return name;
    }
}
