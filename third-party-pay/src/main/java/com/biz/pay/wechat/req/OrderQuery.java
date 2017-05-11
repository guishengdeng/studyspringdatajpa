package com.biz.pay.wechat.req;

import static com.biz.pay.wechat.lang.Keys.*;
import static com.google.common.collect.Sets.newTreeSet;
import static org.codelogger.utils.StringUtils.isBlank;

import java.util.Arrays;
import java.util.TreeSet;

import com.biz.pay.wechat.res.OrderQueryResponse;

public class OrderQuery extends WechatPayRequestBase<OrderQueryResponse> {

	public static final String API_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

	public static final TreeSet<String> KEYS_PARAM_NAME = newTreeSet(
			Arrays.asList(TRANSACTION_ID, OUT_TRADE_NO, APPID, MCH_ID, NONCE_STR, SIGN));

	public OrderQuery(String transactionId, String outTradeNo) {

		if (isBlank(transactionId) && isBlank(outTradeNo)) {
			throw new IllegalArgumentException("Argument transactionId and outTradeNo can not be both empty.");
		}
		setProperty(TRANSACTION_ID, transactionId);
		setProperty(OUT_TRADE_NO, outTradeNo);
	}

	@Override
	protected String getApiUrl() {
		return API_URL;
	}

	@Override
	protected OrderQueryResponse parseResponse(String responseBody) {
		return new OrderQueryResponse(responseBody);
	}

	@Override
	protected TreeSet<String> getSignParamNames() {
		return KEYS_PARAM_NAME;
	}
}
