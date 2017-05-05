package com.biz.pay.alipay.vo;

import com.biz.pay.alipay.IAlipayPayment;
import com.biz.pay.alipay.config.AlipayConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codelogger.utils.DateUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by tanghaibin on 2017/2/22.
 * 即时到帐有密交易退款 vo
 */
public class RefundPayVo {
    /**
     * 合作者身份Id
     */
    @NotNull
    private String partner;
    /**
     * 参数编码字符集
     */
    @JsonProperty("_input_charset")
    @NotNull
    private String paramEncoding = AlipayConfig.input_charset;
    /**
     * 服务器异步通知页面路径
     */
    @JsonProperty("notify_url")
    private String notifyUrl;
    /**
     * 卖家用户ID
     */
    @JsonProperty("seller_user_id")
    @NotNull
    private String sellerId;
    /**
     * 退款请求时间
     * 必须为当前时间
     */
    @JsonProperty("refund_date")
    @NotNull
    private String refundDate = DateUtils.getDateFormat(DateUtils.getCurrentDate(), "yyyy-MM-dd HH:mm:ss");
    /**
     * 退款批次号
     */
    @JsonProperty("batch_no")
    @NotNull
    private String batchNo;
    /***
     * 总笔数
     */
    @JsonProperty("batch_num")
    @NotNull
    private String batchNum;
    /**
     * 单笔数据集
     */
    @JsonProperty("detail_data")
    @NotNull
    private String detailData;

    /**
     * 支付宝接口名称
     */
    private String service = IAlipayPayment.Service.REFUND_FASTPAY_BY_PLATFORM_PWD;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getDetailData() {
        return detailData;
    }

    public void setDetailData(String detailData) {
        this.detailData = detailData;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getParamEncoding() {
        return paramEncoding;
    }

    public void setParamEncoding(String paramEncoding) {
        this.paramEncoding = paramEncoding;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
