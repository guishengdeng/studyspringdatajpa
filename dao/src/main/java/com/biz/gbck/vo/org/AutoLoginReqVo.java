package com.biz.gbck.vo.org;


import com.biz.gbck.common.model.InitGlobalParamsWithToken;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

public class AutoLoginReqVo extends CommonReqVoBindUserId implements InitGlobalParamsWithToken {
	

	  private String token;

	  private GlobalParams globalParams;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public GlobalParams getGlobalParams() {
		return globalParams;
	}

	public void setGlobalParams(GlobalParams globalParams) {
		this.globalParams = globalParams;
	}
	
	

}
