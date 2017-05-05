package com.biz.gbck.vo.payment.req;

import com.biz.pay.wechat.lang.TradeType;

public interface IUnifiedPaymentReqVo extends IPaymentReqVo {

	String getIp();

	TradeType getTradeType();

	String getAppId();

	String getOpenid();

}