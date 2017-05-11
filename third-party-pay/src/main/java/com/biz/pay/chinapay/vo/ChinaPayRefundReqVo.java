package com.biz.pay.chinapay.vo;

/**
 * Created by tanghaibin on 2017/2/25.
 * 银联退款
 */
public class ChinaPayRefundReqVo extends AbstractChinaPayReqVo{

    private String txnType = "04";

    private String txnSubType = "00";
    /**
     * 银联流水号
     */
    private String origQryId;

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getOrigQryId() {
        return origQryId;
    }

    public void setOrigQryId(String origQryId) {
        this.origQryId = origQryId;
    }
}
