package com.biz.pay.wechat.res;

public class CloseOrderResponse extends WechatPayResponseBase {

	private static final long serialVersionUID = 8133419347968119981L;

	public CloseOrderResponse(String responseXml) {
        super(responseXml);
    }
}
