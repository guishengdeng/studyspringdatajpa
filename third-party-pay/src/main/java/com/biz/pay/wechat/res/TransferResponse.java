package com.biz.pay.wechat.res;

import static com.biz.pay.wechat.lang.Keys.*;
import static org.codelogger.utils.DateUtils.getDateFromString;
import static org.codelogger.utils.StringUtils.isBlank;

import java.util.Date;

public class TransferResponse extends WechatPayResponseBase {

	private static final long serialVersionUID = -8770000914872002696L;

	public TransferResponse(String responseXml) {
        super(responseXml);
    }

    @Override public String getAppId() {
        return getProperty(MCH_APPID);
    }

    @Override public String getMchId() {
        return getProperty(MCHID);
    }

    public String getPartnerTradeNo() {
        return getProperty(PARTNER_TRADE_NO);
    }

    public String getPaymentNo() {
        return getProperty(PAYMENT_NO);
    }

    public Date getPaymentTime() {
        String paymentTime = getProperty(PAYMENT_TIME);
        return isBlank(paymentTime) ?
            null :
            getDateFromString(paymentTime, PAYMENT_TIME_DATE_FORMATER);
    }

    public Boolean isTransferSuccess() {
        return isProcessSuccess();
    }

}
