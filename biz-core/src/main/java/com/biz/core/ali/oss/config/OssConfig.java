package com.biz.core.ali.oss.config;

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

	private String bucketName;

    private String userId;

    public OssConfig() {
    }

    public OssConfig(String remoteEndpoint, String localEndpoint, String userId, String accessKeyId, String accessSecret, String bucketName) {
        this.remoteEndpoint = remoteEndpoint;
        this.localEndpoint = localEndpoint;
        this.userId = userId;
        this.accessKeyId = accessKeyId;
        this.accessSecret = accessSecret;
        this.bucketName = bucketName;
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

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
