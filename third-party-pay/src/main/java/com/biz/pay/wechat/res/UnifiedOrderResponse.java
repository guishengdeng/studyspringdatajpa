package com.biz.pay.wechat.res;

import com.biz.pay.wechat.lang.Keys;

public class UnifiedOrderResponse extends WechatPayResponseBase {

	private static final long serialVersionUID = 3369235436389049633L;

	public UnifiedOrderResponse(String responseXml) {
        super(responseXml);
    }

    public String getPrepayId() {
        return getProperty(Keys.PREPAY_ID);
    }

}
