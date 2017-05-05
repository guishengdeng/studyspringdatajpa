package com.biz.gbck.vo.payment.req;

import com.biz.pay.wechat.lang.TradeType;

public class WehchatUnifiedOrderRequestVo extends PaymentRequestVo implements IUnifiedPaymentReqVo {

	private static final long serialVersionUID = -6282141856411916345L;

	private String ip;

    private TradeType tradeType = TradeType.APP;

    private String appId;

    private String openid;

	/* (non-Javadoc)
	 * @see com.bozhi.vo.payment.req.IUnifiedOrderRequest#getIp()
	 */
	@Override
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/* (non-Javadoc)
	 * @see com.bozhi.vo.payment.req.IUnifiedOrderRequest#getTradeType()
	 */
	@Override
	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	/* (non-Javadoc)
	 * @see com.bozhi.vo.payment.req.IUnifiedOrderRequest#getAppId()
	 */
	@Override
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	/* (non-Javadoc)
	 * @see com.bozhi.vo.payment.req.IUnifiedOrderRequest#getOpenid()
	 */
	@Override
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
    
}
