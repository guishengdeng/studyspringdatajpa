package com.biz.pay.wechat.res;

import static com.biz.pay.wechat.lang.Keys.*;

import com.biz.pay.wechat.lang.RefundChannel;

public class RefundResponse extends BaseWechatPayRespVo {

	private static final long serialVersionUID = 2598297178486496872L;

	public RefundResponse(String responseXml) {
        super(responseXml);
    }

    public Boolean isRefunded() {
        return isProcessSuccess();
    }

    public String getOutRefundMo() {
        return getProperty(OUT_REFUND_NO);
    }

    public String getRefundId() {
        return getProperty(REFUND_ID);
    }

    public RefundChannel getRefundChannel() {
        return getEnumInstance(RefundChannel.class, getProperty(REFUND_CHANNEL));
    }
}
