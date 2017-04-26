package com.biz.gbck.dao.redis.ro.user;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;


@Ro(key = "userRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class UserRo extends BaseRedisObject<String> implements Serializable {


    /**
     * 账号
     */
    private String account;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    
    /**
     * 原始密码
     */
    @JsonIgnore
    private String originalPassword;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 绑定的店铺
     */
    private Long shopId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 注册时的设备号
     */
    private String registerDeviceId;

    /**
     * 最后一次登录时间
     */
    private Timestamp lastLoginTime;

    /**
     * 最后一次登录设备
     */
    private String lastLoginDeviceToken;

    /**
     * 注册时的ip地址
     */
    private String registerIP;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIP;

    /**
     * 是否是店长
     */
    private Boolean isAdmin = false;

    /**
     * 用户状态
     */
    private Integer status;
    
    /**
     * 最后token
     */
    private String lastToken;
    
    /**
     * 最后登陆手机品牌
     */
    private String lastUserAgent;
    


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRegisterDeviceId() {
        return registerDeviceId;
    }

    public void setRegisterDeviceId(String registerDeviceId) {
        this.registerDeviceId = registerDeviceId;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginDeviceToken() {
        return lastLoginDeviceToken;
    }

    public void setLastLoginDeviceToken(String lastLoginDeviceToken) {
        this.lastLoginDeviceToken = lastLoginDeviceToken;
    }

    public String getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(String registerIP) {
        this.registerIP = registerIP;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getLastToken() {
		return lastToken;
	}

	public void setLastToken(String lastToken) {
		this.lastToken = lastToken;
	}

	public String getLastUserAgent() {
		return lastUserAgent;
	}

	public void setLastUserAgent(String lastUserAgent) {
		this.lastUserAgent = lastUserAgent;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

}
