package com.biz.gbck.vo.user;

/**
 * 类说明：修改密码的请求对象
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 上午9:41:29
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public class UpdatePasswordRequestVo extends BaseRequestVo {

    private static final long serialVersionUID = -3248354761747476183L;

    /**
     * 用户名(包括手机号,1919用户名)
     */
    private String name;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 旧密码,明文密码<无任何加密的密码>
     */
    private String oldPassword;

    /**
     * 新密码,明文密码<无任何加密的密码>
     */
    private String newPassword;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
