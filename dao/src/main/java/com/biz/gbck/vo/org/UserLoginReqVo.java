package com.biz.gbck.vo.org;

import com.biz.gbck.common.model.InitGlobalParamsWithToken;
import com.biz.gbck.common.vo.WithoutBindRequestParams;
import com.biz.support.web.assist.GlobalParams;

/**
 * Created by defei on 3/16/16.
 */
public class UserLoginReqVo extends WithoutBindRequestParams implements InitGlobalParamsWithToken {

  /**
   * 账号
   */
  private String account;

  /**
   * 密码
   */
  private String password;

  private String ip;
  
  private String token;

  /**
   * 原始密码
   */
  private String originalPassword;
  
  private GlobalParams globalParams;

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

@Override
  public void setGlobalParams(GlobalParams globalParams) {
    this.globalParams = globalParams;
  }

  @Override
  public GlobalParams getGlobalParams() {
    return globalParams;
  }
}
