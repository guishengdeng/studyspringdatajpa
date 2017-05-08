package com.biz.gbck.vo.payment.req;

import com.biz.pay.wechat.lang.TradeType;

public interface IWechatPaymentReqVo extends IPaymentReqVo {

	String getIp();

	TradeType getTradeType();

	String getAppId();

	String getOpenid();

}