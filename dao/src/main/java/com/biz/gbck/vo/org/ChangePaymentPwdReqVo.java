package com.biz.gbck.vo.org;

import com.biz.gbck.common.model.InitGlobalParams;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

import javax.validation.constraints.NotNull;

public class ChangePaymentPwdReqVo extends CommonReqVoBindUserId implements InitGlobalParams {

    /**
     * 原支付密码(md5加密)
     */
    @NotNull(message = "原支付密码不能为空")
    private String paymentPassword;

    /**
     * 新支付密码(md5加密)
     */
    @NotNull(message = "新支付密码不能为空")
    private String newPassword;

    /**
     * 确认支付密码(md5加密)
     */
    @NotNull(message = "确认支付密码不能为空")
    private String repeatPassword;

    private GlobalParams globalParams;

    public ChangePaymentPwdReqVo(String newPassword, String paymentPassword, String repeatPassword) {
        super();
        this.newPassword = newPassword;
        this.paymentPassword = paymentPassword;
        this.repeatPassword = repeatPassword;
    }

    public ChangePaymentPwdReqVo() {
        super();
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override public GlobalParams getGlobalParams() {
        return globalParams;
    }
}
