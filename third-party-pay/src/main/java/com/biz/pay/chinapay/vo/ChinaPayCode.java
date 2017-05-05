package com.biz.pay.chinapay.vo;

/**
 * Created by tanghaibin on 2017/2/25.
 *
 * 银联错误应答码
 */
public enum ChinaPayCode {

    SUCCESS("00", "成功"),

    TIMEOUT("03", "交易通讯超时,请发起查询交易"),

    UNKNOWN_SUCESS("04", "交易状态为明, 请查询对账结果"),

    HAS_BEEN_ACCEPTED("05", "交易已受理，请稍候查询交易结果"),

    FAILED_SIGN("11", "签名失败"),

    DUPLICATE_DEAL("12", "重复交易");

    private String code;

    private String description;

    ChinaPayCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
