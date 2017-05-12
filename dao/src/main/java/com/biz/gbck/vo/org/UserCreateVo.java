package com.biz.gbck.vo.org;


import com.biz.gbck.common.org.UserStatus;
import com.biz.gbck.dao.mysql.po.org.UserPo;

/**
 * Created by defei on 3/16/16.
 */
public class UserCreateVo {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;
    
    private String originalPassword;

    public UserPo toUserPo() {

        UserPo userPo = new UserPo();
        userPo.setMobile(mobile);
        userPo.setAccount(mobile);
        userPo.setPassword(password);
        userPo.setOriginalPassword(originalPassword);
        userPo.setName(mobile);
        userPo.setStatus(UserStatus.NORMAL.getValue());
        return userPo;
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

    public String getPassword() {
        return password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

}
