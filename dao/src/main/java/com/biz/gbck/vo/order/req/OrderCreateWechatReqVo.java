package com.biz.gbck.vo.order.req;

import com.biz.gbck.vo.payment.req.IWechatPaymentReqVo;
import com.biz.pay.wechat.lang.TradeType;

import javax.validation.constraints.NotNull;

/**
 * 微信下单请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderCreateWechatReqVo extends OrderCreateReqVo implements IWechatPaymentReqVo {

    private static final long serialVersionUID = -1915622859110331952L;

    @NotNull(message = "微信支付交易类型不能为空")
    private TradeType tradeType = TradeType.APP;

    @NotNull(message = "微信支付appid不能为空")
    private String appId;

    private String openid;

    @Override
    public TradeType getTradeType() {
        return tradeType;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public String getOpenid() {
        return openid;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
