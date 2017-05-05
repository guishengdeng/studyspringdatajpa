package com.biz.pay.wechat.req;

import static com.biz.pay.wechat.lang.Keys.*;
import static com.google.common.collect.Sets.newTreeSet;
import static org.codelogger.utils.StringUtils.isBlank;

import java.util.Arrays;
import java.util.TreeSet;

import com.biz.pay.wechat.res.RefundQueryResponse;

public class RefundQuery extends WechatPayRequestBase<RefundQueryResponse> {

	public static final String API_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

	public static final TreeSet<String> KEYS_PARAM_NAME = newTreeSet(
			Arrays.asList(REFUND_ID, OUT_REFUND_NO, APPID, MCH_ID, NONCE_STR, SIGN));

	public RefundQuery(String refundId, String outRefundNo) {

		if (isBlank(refundId) && isBlank(outRefundNo)) {
			throw new IllegalArgumentException("Argument refundId and outRefundNo can not be both empty.");
		}
		setProperty(REFUND_ID, refundId);
		setProperty(OUT_REFUND_NO, outRefundNo);
	}

	@Override
	protected String getApiUrl() {
		return API_URL;
	}

	@Override
	protected RefundQueryResponse parseResponse(String responseBody) {
		return new RefundQueryResponse(responseBody);
	}

	@Override
	protected TreeSet<String> getSignParamNames() {
		return KEYS_PARAM_NAME;
	}
}
