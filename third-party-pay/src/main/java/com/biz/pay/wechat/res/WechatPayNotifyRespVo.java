package com.biz.pay.wechat.res;

import org.codelogger.utils.DateUtils;

import java.util.Date;

import static com.biz.pay.wechat.lang.Keys.*;
import static org.codelogger.utils.StringUtils.isBlank;

public class WechatPayNotifyRespVo extends BaseWechatPayRespVo {

	private static final long serialVersionUID = 950804369816025712L;
	
	private String[] ignoreParams;

    @Override protected String[] getIgnoreParams() {
        return ignoreParams;
    }

    public WechatPayNotifyRespVo(String responseXml, String... ignoreParams) {
        super(responseXml);
        this.ignoreParams = ignoreParams;
    }

    public String getTransactionId() {
        return getProperty(TRANSACTION_ID);
    }

    public String getBankType() {
        return getProperty(BANK_TYPE);
    }

    public Integer getTotalFee() {
        String totalFee = getProperty(TOTAL_FEE);
        return totalFee == null ? null : Integer.valueOf(totalFee);
    }

    public Integer getCashFee() {
        String totalFee = getProperty(CASH_FEE);
        return totalFee == null ? null : Integer.valueOf(totalFee);
    }

    public String getFeeType() {
        return getProperty(FEE_TYPE);
    }

    public Date getTimeEnd() {
        String timeEnd = getProperty(TIME_END);
        return isBlank(timeEnd) ?
            null :
            DateUtils.getDateFromString(timeEnd, TIME_END_DATE_FORMATER);
    }

    public String getCashFeeType() {
        return getProperty(CASH_FEE_TYPE);
    }

    public Integer getCouponFee() {
        String stringIntValue = getProperty(COUPON_FEE_0);
        return stringIntValue == null ? null : Integer.valueOf(stringIntValue);
    }

    public Integer getCouponCount() {
        String stringIntValue = getProperty(COUPON_COUNT);
        return stringIntValue == null ? null : Integer.valueOf(stringIntValue);
    }

    public String getCouponId() {
        return getProperty(COUPON_ID_0);
    }

    public String getAttach() {
        return getProperty(ATTACH);
    }
}
