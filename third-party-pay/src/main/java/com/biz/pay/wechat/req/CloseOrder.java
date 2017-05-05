package com.biz.pay.wechat.req;

import static com.biz.pay.wechat.lang.Keys.APPID;
import static com.biz.pay.wechat.lang.Keys.MCH_ID;
import static com.biz.pay.wechat.lang.Keys.NONCE_STR;
import static com.biz.pay.wechat.lang.Keys.OUT_TRADE_NO;
import static com.biz.pay.wechat.lang.Keys.SIGN;
import static com.google.common.collect.Sets.newTreeSet;
import static org.codelogger.utils.StringUtils.isBlank;

import java.util.Arrays;
import java.util.TreeSet;

import com.biz.pay.wechat.res.CloseOrderResponse;

public class CloseOrder extends WechatPayRequestBase<CloseOrderResponse> {

	private static final String API_URL = "https://api.mch.weixin.qq.com/pay/closeorder";

	public static final TreeSet<String> KEYS_PARAM_NAME = newTreeSet(
			Arrays.asList(APPID, MCH_ID, OUT_TRADE_NO, NONCE_STR, SIGN));

	public CloseOrder(String outTradeNo) {

		if (isBlank(outTradeNo)) {
			throw new IllegalArgumentException("Argument transactionId and outTradeNo can not be empty.");
		}
		setProperty(OUT_TRADE_NO, outTradeNo);
	}

	@Override
	protected String getApiUrl() {
		return API_URL;
	}

	@Override
	protected CloseOrderResponse parseResponse(String responseBody) {
		return new CloseOrderResponse(responseBody);
	}

	@Override
	protected TreeSet<String> getSignParamNames() {
		return KEYS_PARAM_NAME;
	}
}
