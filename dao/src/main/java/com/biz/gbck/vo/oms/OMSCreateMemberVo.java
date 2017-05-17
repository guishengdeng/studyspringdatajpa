package com.biz.gbck.vo.oms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

/**
 * Created by cmk on 2016/7/26.
 */
public class OMSCreateMemberVo {

    @JsonIgnore
    private Long userId;

    /**
     * 账号(手机号)
     */
    @NotNull(message="账号不能空")
    private String name;

    /**
     * 手机
     */
    private String mobile;

    /**
     * email
     */
    private String email;
    /**
     * 密码(md5)
     */

    @NotNull(message="密码不能空")
    private String password;

    /**
     * 原始密码，密码规则：6-16位，不含空格
     */

    @NotNull(message="原始密码不能空")
    @JsonProperty("originalpwd")
    private String originalPwd;

    /**
     * 渠道代码
     */
    //@NotNull(message="渠道代码不能空")
    private String channelCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalPwd() {
        return originalPwd;
    }

    public void setOriginalPwd(String originalPwd) {
        this.originalPwd = originalPwd;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
