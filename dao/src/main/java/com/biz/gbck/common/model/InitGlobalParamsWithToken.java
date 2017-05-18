package com.biz.gbck.common.model;

import com.biz.support.web.assist.GlobalParams;

import java.io.Serializable;


public interface InitGlobalParamsWithToken extends InitGlobalParams,Serializable {

    void setGlobalParams(GlobalParams globalParams);

    GlobalParams getGlobalParams();
    
	public String getPushToken();

	public void setPushToken(String pushToken);

}
