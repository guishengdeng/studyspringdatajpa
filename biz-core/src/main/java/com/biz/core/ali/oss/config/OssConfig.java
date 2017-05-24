package com.biz.core.ali.oss.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * oss 属性配置类
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月16日
 * @reviewer 
 */
public class OssConfig {

	private String remoteEndpoint;

	private String localEndpoint;

	private String accessKeyId;

	private String accessSecret;

    private String productBucketName;//商品及头像

    private String auditBucketName; //资质

    private String userId;

    public OssConfig() {
    }

    public OssConfig(String remoteEndpoint, String localEndpoint, String accessKeyId, String accessSecret, String productBucketName, String auditBucketName, String userId) {
        this.remoteEndpoint = remoteEndpoint;
        this.localEndpoint = localEndpoint;
        this.accessKeyId = accessKeyId;
        this.accessSecret = accessSecret;
        this.productBucketName = productBucketName;
        this.auditBucketName = auditBucketName;
        this.userId = userId;
    }

    /**
     * {@linkplain OssConfig#userId}
     */
    public String getUserId() {

        return userId;
    }

    /**
     * {@linkplain OssConfig#userId}
     */
    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getRemoteEndpoint() {
        return remoteEndpoint;
    }

    public void setRemoteEndpoint(String remoteEndpoint) {
        this.remoteEndpoint = remoteEndpoint;
    }

    public String getLocalEndpoint() {
        return localEndpoint;
    }

    public void setLocalEndpoint(String localEndpoint) {
        this.localEndpoint = localEndpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }


    public String getProductBucketName() {
        return productBucketName;
    }

    public void setProductBucketName(String productBucketName) {
        this.productBucketName = productBucketName;
    }

    public String getAuditBucketName() {
        return auditBucketName;
    }

    public void setAuditBucketName(String auditBucketName) {
        this.auditBucketName = auditBucketName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
