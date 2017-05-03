package com.biz.gbck.vo.user;

import com.biz.gbck.vo.IRequestVo;
import com.biz.support.web.assist.ClientType;
import com.biz.support.web.assist.ClientTypeAware;
import com.biz.support.web.assist.GlobalParams;
import com.biz.support.web.assist.IPAddressAware;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Rest请求公共参数
 *
 * @date 2017年05月02日
 * @reviewer
 * @see
 */
public class BaseRequestVo implements IPAddressAware, IRequestVo, ClientTypeAware {

    private static final long serialVersionUID = 6444955805404831941L;

    @JsonIgnore
    private GlobalParams globalParams;

    @JsonIgnore
    private String token;

    @JsonIgnore
    private String authToken;

    @JsonIgnore
    private String ip;

    @JsonIgnore
    private String md5;

    @JsonIgnore
    private ClientType clientType;

    @JsonIgnore
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public GlobalParams getGlobalParams() {
        return globalParams;
    }

    public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public ClientType getClientType() {
        return clientType;
    }

    @Override
    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
