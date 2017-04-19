package com.biz.gbck.vo.user;


import com.biz.gbck.vo.IRequestVo;

/**
 * 根据账号(手机号、邮箱、用户名+渠道编码)查找用户信息
 *
 * @author lei
 * @date 2017年02月10日
 * @reviewer
 * @see
 */
public class AccountNameRequestVo implements IRequestVo {

    private static final long serialVersionUID = -8573806836176376378L;

    /**
     * 根据用户id(非中台memberId)查询用户信息(必传)
     */
    private String accountName;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 检查用户是否存在, 若为true, 只返回简单会员信息(不含本地userId等)!!!!
     */
    private boolean check = false;

    public AccountNameRequestVo() {
    }

    public AccountNameRequestVo(String accountName, String channelCode) {
        this.accountName = accountName;
        this.channelCode = channelCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
