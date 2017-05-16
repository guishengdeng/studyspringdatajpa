package com.biz.gbck.vo.payment.pay;

import com.biz.pay.wechat.lang.ResultCode;
import com.biz.pay.wechat.lang.TradeType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * Created by lzz on 2017/5/15.
 */
public class AlipayPaymentVo {

    /**
     * 通知编号
     */
    private String notifyId;

    /**
     * 买家支付宝用户号:买家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。
     */
    private String buyerId;

    /**
     * 买家支付宝账号:买家支付宝账号，可以是 Email或手机号码。
     */
    private String buyerEmail;

    /**
     * 是否在交易过程中使用了红包。
     */
    private String useCoupon;

    private Timestamp notifyTime;

    private String subject;

    /**
     * 是否调整总价:该交易是否调整过价格。
     */
    private String isTotalFeeAjust;

    /**
     * 折扣
     */
    private Integer discount;
    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 交易创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 交易付款时间
     */
    private Timestamp gmtPayment;

    /**
     * 商品单价:price 等于 total_fee
     */
    private Integer price;

    /**
     * 交易金额:该笔订单的总金额。
     */
    private Integer totalFee;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 支付类型
     */
    private String paymentType;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 5;

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
    }

    public Timestamp getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Timestamp notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIsTotalFeeAjust() {
        return isTotalFeeAjust;
    }

    public void setIsTotalFeeAjust(String isTotalFeeAjust) {
        this.isTotalFeeAjust = isTotalFeeAjust;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Timestamp gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Created by lzz on 2017/5/15.
     */
    public static class WechatPaymentVo {

        private ResultCode resultCode;

        private String errCode;

        private String errCodeDes;

        private String openid;

        private TradeType tradeType;

        private String bankType;

        private Integer totalFee;

        private String feeType;

        private Integer cashFee;

        private String cashFeeType;

        private Integer couponFee;

        private Integer couponCount;

        private String couponId;

        private String attach;

        private Timestamp timeEnd;

        @Min(1)
        private Integer page = 1;

        @Max(100)
        private Integer pageSize = 5;

        public ResultCode getResultCode() {
            return resultCode;
        }

        public void setResultCode(ResultCode resultCode) {
            this.resultCode = resultCode;
        }

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getErrCodeDes() {
            return errCodeDes;
        }

        public void setErrCodeDes(String errCodeDes) {
            this.errCodeDes = errCodeDes;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public TradeType getTradeType() {
            return tradeType;
        }

        public void setTradeType(TradeType tradeType) {
            this.tradeType = tradeType;
        }

        public String getBankType() {
            return bankType;
        }

        public void setBankType(String bankType) {
            this.bankType = bankType;
        }

        public Integer getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(Integer totalFee) {
            this.totalFee = totalFee;
        }

        public String getFeeType() {
            return feeType;
        }

        public void setFeeType(String feeType) {
            this.feeType = feeType;
        }

        public Integer getCashFee() {
            return cashFee;
        }

        public void setCashFee(Integer cashFee) {
            this.cashFee = cashFee;
        }

        public String getCashFeeType() {
            return cashFeeType;
        }

        public void setCashFeeType(String cashFeeType) {
            this.cashFeeType = cashFeeType;
        }

        public Integer getCouponFee() {
            return couponFee;
        }

        public void setCouponFee(Integer couponFee) {
            this.couponFee = couponFee;
        }

        public Integer getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(Integer couponCount) {
            this.couponCount = couponCount;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public Timestamp getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(Timestamp timeEnd) {
            this.timeEnd = timeEnd;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }
}
