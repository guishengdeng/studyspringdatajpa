package com.biz.soa.config;

/**
 * Created by david-liu on 2017/04/18 14:07.
 */
public class RecConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String recRequestUrl;

    private String bizCode;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getRecRequestUrl() {
        return recRequestUrl;
    }

    public void setRecRequestUrl(String recRequestUrl) {
        this.recRequestUrl = recRequestUrl;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public enum scn_code {
        home,
        detail,
        main_rec,
        productRec
    }
}
