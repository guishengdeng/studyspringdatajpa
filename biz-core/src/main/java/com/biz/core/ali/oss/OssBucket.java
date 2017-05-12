package com.biz.core.ali.oss;


/**
 * oss 图片上传地址
 */
public enum OssBucket {

    PRODUCT("product","biz.oss.bucketName"),
    AUDIT("audit","biz.oss.bucketName");
    private final String type,bucketNameKey;

    OssBucket(String type, String bucketNameKey) {
        this.type = type;
        this.bucketNameKey = bucketNameKey;
    }

    public String getBucketNameKey() {
        return bucketNameKey;
    }

    public String getType() {
      return type;
    }
    
    
}
