package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * 阿里云推荐引擎请求Vo
 *
 * @author david-liu
 * @date 2016年09月27日
 * @reviewer
 * @see
 */
public class AliRecReqVo implements Serializable {

    private static final long serialVersionUID = -8099416230201218176L;

    private String accessKeyID;

    private String accessKeySecret;

    private String bizCode;

    private String scnCode;

    public AliRecReqVo(String accessKeyID, String accessKeySecret, String bizCode, String scnCode) {
        this.accessKeyID = accessKeyID;
        this.accessKeySecret = accessKeySecret;
        this.bizCode = bizCode;
        this.scnCode = scnCode;
    }

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getScnCode() {
        return scnCode;
    }

    public void setScnCode(String scnCode) {
        this.scnCode = scnCode;
    }
}
