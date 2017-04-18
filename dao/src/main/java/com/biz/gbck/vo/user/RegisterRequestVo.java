package com.biz.gbck.vo.user;

/**
 * 类说明：注册的请求对象
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 上午9:36:46
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public class RegisterRequestVo extends BaseRequestVo {

    private static final long serialVersionUID = -5941323366555041895L;

    /**
     * 账号(手机号或其他类型的账号)
     */
    private String name;

    /**
     * 手机短信验证码
     */
    private String smsCode;

    /**
     * 明文密码<无任何加密的密码>,如果为空则使用默认密码
     */
    private String password;

    /**
     * 注册渠道
     */
    private String channelCode;

    /**
     * 注册ip
     */
    private String ip;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
