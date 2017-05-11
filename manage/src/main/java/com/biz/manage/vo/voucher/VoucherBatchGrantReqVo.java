package com.biz.manage.vo.voucher;

/**
 * @author: liubin
 * @date 1/6/17 15:16
 */
public class VoucherBatchGrantReqVo {

    private Long voucherTypeId;

    private Integer dispatcherCnt;

    private String smsTemplateId;

    private String smsContent;

    public Integer getDispatcherCnt() {
        return dispatcherCnt;
    }

    public void setDispatcherCnt(Integer dispatcherCnt) {
        this.dispatcherCnt = dispatcherCnt;
    }

    public Long getVoucherTypeId() {
        return voucherTypeId;
    }

    public void setVoucherTypeId(Long voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}
