package com.biz.gbck.vo.user;

/**
 * 校验密码请求vo
 *
 * @author lei
 * @date 2017年02月20日
 * @reviewer
 * @see
 */
public class VerifyPasswordRequestVo extends BaseRequestVo {

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

}
