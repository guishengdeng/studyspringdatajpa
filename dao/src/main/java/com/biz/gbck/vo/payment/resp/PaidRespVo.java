package com.biz.gbck.vo.payment.resp;


import java.io.Serializable;

/**
 * 支付查询结果
 * @author yanweijin
 * @since 2016年9月2日
 * @usage 
 * @reviewer
 */
public interface PaidRespVo extends Serializable {

	/**
	 * @return 第三方交易单号
	 */
	String getTradeNo();

	/**
	 * @return 是否支付成功
	 */
	Boolean isPaid();

	/**
	 * 付款账号
	 *
	 * @return
	 * @author defei
	 */
	String getPayAccount();
}
