package com.biz.pay.wechat.req;

import static com.biz.pay.wechat.lang.Keys.*;
import static com.google.common.collect.Sets.newTreeSet;
import static org.codelogger.utils.StringUtils.isBlank;

import java.util.Arrays;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.biz.pay.wechat.lang.TradeType;
import com.biz.pay.wechat.res.UnifiedOrderResponse;

public class UnifiedOrder extends WechatPayRequestBase<UnifiedOrderResponse> {

	public static final String API_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	public static final TreeSet<String> KEYS_PARAM_NAME = newTreeSet(
			Arrays.asList(BODY, OPENID, OUT_TRADE_NO, PRODUCT_ID, SPBILL_CREATE_IP, TOTAL_FEE, TRADE_TYPE, APPID,
					ATTACH, DEVICE_INFO, GOODS_TAG, MCH_ID, NONCE_STR, NOTIFY_URL, SIGN, TIME_START, TIME_EXPIRE
					,SUB_APPID,SUB_MCH_ID,SUB_OPENID));

	public UnifiedOrder(String outTradeNo, String body, Integer totalFee, String clientIp, TradeType tradeType,
			String timeExpire, String openid) {

		if (isBlank(outTradeNo)) {
			throw new IllegalArgumentException("outTradeNo can not be empty.");
		}
		if (isBlank(body)) {
			throw new IllegalArgumentException("body can not be empty.");
		}
		// if (getValue(totalFee) < 1) {
		// throw new IllegalArgumentException("totalFee can not less than 1.");
		// }
		if (isBlank(clientIp)) {
			throw new IllegalArgumentException("clientIp can not be empty.");
		}
		if (tradeType == null) {
			throw new IllegalArgumentException("tradeType can not be null.");
		}
		if (timeExpire != null && !timeExpire.matches("^\\d{14}$")) {
			throw new IllegalArgumentException("timeExpire format pattern is 'yyyyMMddHHmmss'");
		}
		setProperty(OUT_TRADE_NO, outTradeNo);
		setProperty(BODY, StringUtils.left(body, 30));
		setProperty(TOTAL_FEE, totalFee.toString());
		setProperty(SPBILL_CREATE_IP, clientIp);
		setProperty(TRADE_TYPE, tradeType.name());
		// setProperty(TIME_EXPIRE, timeExpire);
		if (StringUtils.isNotBlank(openid)) {
			setProperty(OPENID, openid);
		}
	}
	
	/**
	 * 创建一个公众平台微信统一订单
	 * @author bruce.qin
	 * @date 2016年9月30日
	 * @param outTradeNo	商户订单号
	 * @param body	商品描述
	 * @param totalFee	总金额单位分
	 * @param clientIp	用户手机端ip
	 * @param timeExpire	过期时间格式化yyyyMMddHHmmss
	 * @param openid		用户对父公众号的唯一标识
	 * @param subOpenid		用户对子公众号的唯一标识
	 */
	public UnifiedOrder(String outTradeNo, String body, Integer totalFee, String clientIp,
			String timeExpire,String openid, String subOpenid){
		if (isBlank(outTradeNo)) {
			throw new IllegalArgumentException("outTradeNo can not be empty.");
		}
		if (isBlank(body)) {
			throw new IllegalArgumentException("body can not be empty.");
		}
		// if (getValue(totalFee) < 1) {
		// throw new IllegalArgumentException("totalFee can not less than 1.");
		// }
		if (isBlank(clientIp)) {
			throw new IllegalArgumentException("clientIp can not be empty.");
		}
		if (timeExpire != null && !timeExpire.matches("^\\d{14}$")) {
			throw new IllegalArgumentException("timeExpire format pattern is 'yyyyMMddHHmmss'");
		}
		if(StringUtils.isEmpty(openid)&&StringUtils.isEmpty(subOpenid)){
			throw new IllegalArgumentException("openid or subOpenid can not be all empty.");
		}
		setProperty(OUT_TRADE_NO, outTradeNo);
		setProperty(BODY, StringUtils.left(body, 30));
		setProperty(TOTAL_FEE, totalFee.toString());
		setProperty(SPBILL_CREATE_IP, clientIp);
		setProperty(TRADE_TYPE, TradeType.JSAPI.name());
		if(StringUtils.isNotEmpty(timeExpire))
			setProperty(TIME_EXPIRE, timeExpire);
		if(StringUtils.isNoneEmpty(openid)){
			setProperty(OPENID, openid);
		}
		if (StringUtils.isNotBlank(subOpenid)) {
			setProperty(SUB_OPENID, subOpenid);
		}
	}

	@Override
	protected String getApiUrl() {
		return API_URL;
	}

	@Override
	protected TreeSet<String> getSignParamNames() {
		return KEYS_PARAM_NAME;
	}

	@Override
	protected UnifiedOrderResponse parseResponse(String responseXml) {
		return new UnifiedOrderResponse(responseXml);
	}

}
