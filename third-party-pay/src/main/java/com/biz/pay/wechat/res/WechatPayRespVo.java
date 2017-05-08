package com.biz.pay.wechat.res;

import com.biz.pay.wechat.lang.Keys;

public class WechatPayRespVo extends BaseWechatPayRespVo {

	private static final long serialVersionUID = 3369235436389049633L;

	public WechatPayRespVo(String responseXml) {
        super(responseXml);
    }

    public String getPrepayId() {
        return getProperty(Keys.PREPAY_ID);
    }

}
