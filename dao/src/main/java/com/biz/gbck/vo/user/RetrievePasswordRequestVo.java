package com.biz.gbck.vo.user;

/**
 * 类说明：找回密码的请求对象
 *
 * @author xiaoyasong
 * @version 创建时间：2017年1月3日 下午6:01:07
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public class RetrievePasswordRequestVo extends BaseRequestVo {

    private static final long serialVersionUID = 1484782819481681729L;

    /**
     * 账号(手机号或其他类型的账号)
     */
    private String name;

    /**
     * 手机短信验证码
     */
    private String smsCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 渠道代码
     */
    private String channelCode;

    /**
     * 页面验证码
     */
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
