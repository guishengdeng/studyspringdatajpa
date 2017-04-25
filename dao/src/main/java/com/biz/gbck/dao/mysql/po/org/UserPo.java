package com.biz.gbck.dao.mysql.po.org;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity @Table(name = "usr_user") public class UserPo extends BaseEntity {

    @Id private Long id;

    /**
     * 老b2bid
     */
    private Long oldId;

    /**
     * 登录账号
     */
    @Column(unique = true) private String account;

    /**
     * 名字
     */
    @Column(nullable = false, length = 40) private String name;

    /**
     * 电话
     */
    @Column(nullable = false, length = 11) private String mobile;

    /**
     * 密码
     */
    @Column(nullable = false, length = 32) private String password;

    /**
     * 头像
     */
    @Column(length = 50) private String avatar;

    /**
     * 绑定的店铺
     */
    @ManyToOne @JoinColumn(name = "shopId") private ShopPo shop;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 注册时的设备号
     */
    @Column(length = 64) private String registerDeviceId;

    /**
     * 注册时的ip地址
     */
    @Column(length = 15) private String registerIP;

    /**
     * 最后一次登录设备
     */
    private String lastLoginDeviceToken;

    /**
     * 最后一次登录ip
     */
    @Column(length = 15) private String lastLoginIP;

    /**
     * 最后一次登录时间
     */
    private Timestamp lastLoginTime;

    /**
     * 是否是店长
     */
    @Column(nullable = false) private Boolean isAdmin = false;

    /**
     * 用户状态
     */
    @Column(nullable = false) private Integer status;
    
    
    /**
     * 原始密码(明文)
     */
    @Column(nullable = true, length = 50) private String originalPassword;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

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

    public ShopPo getShop() {
        return shop;
    }

    public void setShop(ShopPo shop) {
        this.shop = shop;
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

    public String getLastLoginDeviceToken() {
        return lastLoginDeviceToken;
    }

    public void setLastLoginDeviceToken(String lastLoginDeviceToken) {
        this.lastLoginDeviceToken = lastLoginDeviceToken;
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

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}
    
    
}
