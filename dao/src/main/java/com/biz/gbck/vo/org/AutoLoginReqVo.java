package com.biz.gbck.vo.org;


import com.biz.gbck.common.model.InitGlobalParamsWithToken;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

public class AutoLoginReqVo extends CommonReqVoBindUserId implements InitGlobalParamsWithToken {
	

	  private String pushToken;

	  private GlobalParams globalParams;

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public GlobalParams getGlobalParams() {
		return globalParams;
	}

	public void setGlobalParams(GlobalParams globalParams) {
		this.globalParams = globalParams;
	}
	
	

}
