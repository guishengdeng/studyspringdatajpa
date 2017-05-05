package com.biz.pay.wechat.res;

import static com.biz.pay.wechat.lang.Keys.*;

import com.biz.pay.wechat.lang.RefundChannel;
import com.biz.pay.wechat.lang.RefundStatus;

public class RefundQueryResponse extends WechatPayResponseBase {

	private static final long serialVersionUID = 4549216554698703899L;

	public RefundQueryResponse(String responseXml) {
        super(responseXml);
    }

    public RefundChannel getRefundChannel() {
        return getEnumInstance(RefundChannel.class, getProperty(REFUND_CHANNEL));
    }

    public RefundStatus getRefundStatus() {
        return getEnumInstance(RefundStatus.class, getProperty(REFUND_STATUS_0));
    }
}
