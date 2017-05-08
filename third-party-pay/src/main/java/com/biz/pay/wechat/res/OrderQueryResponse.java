package com.biz.pay.wechat.res;

import java.util.Objects;

import com.biz.pay.wechat.lang.Keys;
import com.biz.pay.wechat.lang.TradeState;

public class OrderQueryResponse extends WechatPayResponseBase {

	private static final long serialVersionUID = -4709792443891928690L;

	public OrderQueryResponse(String responseXml) {
        super(responseXml);
    }

    public TradeState getTradeStatus() {
        return getEnumInstance(TradeState.class, getProperty(Keys.TRADE_STATE));
    }

    public Boolean isPaid() {
        return isProcessSuccess() && Objects.equals(getTradeStatus(), TradeState.SUCCESS);
    }
}
