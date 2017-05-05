package com.biz.pay.wechat.req;

import static com.biz.pay.wechat.lang.Keys.*;
import static com.google.common.collect.Sets.newTreeSet;
import static org.codelogger.utils.StringUtils.isBlank;
import static org.codelogger.utils.ValueUtils.getValue;

import java.util.Arrays;
import java.util.TreeSet;

import javax.net.ssl.SSLContext;

import com.biz.pay.wechat.lang.SimpleHttpClient;
import com.biz.pay.wechat.res.RefundResponse;

public class OrderRefund extends WechatPayRequestBase<RefundResponse> {

	private static final String API_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	public static final TreeSet<String> KEYS_PARAM_NAME = newTreeSet(Arrays.asList(APPID, MCH_ID, TRANSACTION_ID,
			OUT_TRADE_NO, OUT_REFUND_NO, TOTAL_FEE, REFUND_FEE, OP_USER_ID, NONCE_STR, SIGN));

	private SSLContext sslContext;

	public OrderRefund(String transactionId, String outTradeNo, String outRefundNo, Integer totalFee,
			Integer refundFee) {

		if (isBlank(transactionId) && isBlank(outTradeNo)) {
			throw new IllegalArgumentException("Argument transactionId and outTradeNo can not be both empty.");
		}
		if (isBlank(outRefundNo)) {
			throw new IllegalArgumentException("Argument outRefundNo can not be blank.");
		}
		if (getValue(totalFee) < 1) {
			throw new IllegalArgumentException("Argument totalFee can not less than 1.");
		}
		if (getValue(refundFee) < 1) {
			throw new IllegalArgumentException("Argument refundFee can not less than 1.");
		}
		if (refundFee > totalFee) {
			throw new IllegalArgumentException("Argument refundFee can not greater than totalFee.");
		}
		setProperty(TRANSACTION_ID, transactionId);
		setProperty(OUT_TRADE_NO, outTradeNo);
		setProperty(OUT_REFUND_NO, outRefundNo);
		setProperty(TOTAL_FEE, totalFee.toString());
		setProperty(REFUND_FEE, refundFee.toString());
	}

	@Override
	protected SimpleHttpClient getSimpleHttpClient() {
		if (sslContext == null) {
			throw new IllegalArgumentException("sslContext can not be null");
		} else {
			return new SimpleHttpClient(sslContext);
		}
	}

	@Override
	protected String getApiUrl() {
		return API_URL;
	}

	@Override
	protected RefundResponse parseResponse(String responseBody) {
		return new RefundResponse(responseBody);
	}

	@Override
	protected TreeSet<String> getSignParamNames() {
		return KEYS_PARAM_NAME;
	}

	public void setSslContext(SSLContext sslContext) {
		this.sslContext = sslContext;
	}
}
