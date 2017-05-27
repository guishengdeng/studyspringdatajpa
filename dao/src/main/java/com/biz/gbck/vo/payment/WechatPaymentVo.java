package com.biz.gbck.vo.payment;

import com.biz.pay.wechat.lang.ResultCode;
import com.biz.pay.wechat.lang.TradeType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * Created by lzz on 2017/5/16.
 */
public class WechatPaymentVo {

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
