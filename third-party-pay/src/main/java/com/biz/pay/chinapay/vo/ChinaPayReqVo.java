package com.biz.pay.chinapay.vo;

/**
 * Created by tanghaibin on 2017/2/8.
 *
 * 银联支付
 */
public class ChinaPayReqVo extends AbstractChinaPayReqVo{

    private String txnType = "01";

    private String txnSubType = "01";

    private String frontUrl;

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

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
}
