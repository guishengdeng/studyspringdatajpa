package com.biz.pay.wechat.req;

import static com.biz.pay.wechat.lang.Keys.*;
import static com.google.common.collect.Sets.newTreeSet;
import static org.codelogger.utils.StringUtils.isBlank;

import java.util.Arrays;
import java.util.TreeSet;

import javax.net.ssl.SSLContext;

import com.biz.pay.wechat.lang.SimpleHttpClient;
import com.biz.pay.wechat.res.TransferQueryResponse;

public class TransferQuery extends WechatPayRequestBase<TransferQueryResponse> {

	private static final String API_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";

	public static final TreeSet<String> KEYS_PARAM_NAME = newTreeSet(
			Arrays.asList(APPID, MCH_ID, NONCE_STR, SIGN, PARTNER_TRADE_NO));

	private SSLContext sslContext;

	public TransferQuery(String partnerTradeNo) {

		if (isBlank(partnerTradeNo)) {
			throw new IllegalArgumentException("Argument partnerTradeNo can not be blank.");
		}

		setProperty(PARTNER_TRADE_NO, partnerTradeNo);
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
	protected TransferQueryResponse parseResponse(String responseBody) {
		return new TransferQueryResponse(responseBody);
	}

	@Override
	protected TreeSet<String> getSignParamNames() {
		return KEYS_PARAM_NAME;
	}

	public void setSslContext(SSLContext sslContext) {
		this.sslContext = sslContext;
	}
}
