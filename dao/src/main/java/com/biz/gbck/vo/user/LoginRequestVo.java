package com.biz.gbck.vo.user;

/**
 * 类说明：登录的请求对象
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 上午9:37:24
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public class LoginRequestVo extends BaseRequestVo {

    private static final long serialVersionUID = -8573806836176376378L;

    /**
     * 账号(手机号或其他类型的账号)
     */
    private String name;

    /**
     * 明文密码<无任何加密的密码>,如果为空则使用默认密码
     */
    private String password;

    /**
     * 渠道代码
     */
    private String channelCode;

    /**
     * 登录ip
     */
    private String ip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
