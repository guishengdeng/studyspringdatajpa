package com.biz.pay.chinapay.vo;

/**
 * Created by tanghaibin on 2017/2/8.
 */
public class ChinaPayResultVo {
    /**
     * 编码
     */
    private String encoding;
    /**
     * 证书id
     */
    private String certId;
    /**
     * 签名
     */
    private String signature;
    /**
     * 交易类型
     */
    private String txnType;
    /**
     * 商户代码
     */
    private String merId;
    /**
     * 商户订单号
     */
    private String orderId;
    /**
     * 交易币种
     */
    private String currencyCode;
    /**
     * 交易金额
     */
    private String txnAmt;
    /**
     * 订单发送时间
     */
    private String txnTime;
    /**
     * 商户自定义保留域
     */
    private String reqReserved;
    /**
     * 查询流水号
     */
    private String queryId;
    /**
     * 应答码
     */
    private String respCode;
    /**
     * 应答信息
     */
    private String respMsg;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getReqReserved() {
        return reqReserved;
    }

    public void setReqReserved(String reqReserved) {
        this.reqReserved = reqReserved;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    @Override
    public String toString() {
        return "ChinaPayResultVo{" +
                "encoding='" + encoding + '\'' +
                ", certId='" + certId + '\'' +
                ", signature='" + signature + '\'' +
                ", txnType='" + txnType + '\'' +
                ", merId='" + merId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", txnTime='" + txnTime + '\'' +
                ", reqReserved='" + reqReserved + '\'' +
                ", queryId='" + queryId + '\'' +
                ", respCode='" + respCode + '\'' +
                ", respMsg='" + respMsg + '\'' +
                '}';
    }
}
