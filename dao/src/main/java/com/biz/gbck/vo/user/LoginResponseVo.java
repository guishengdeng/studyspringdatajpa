package com.biz.gbck.vo.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 类说明：登录的响应对象
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 上午9:36:59
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public class LoginResponseVo implements Serializable {

    private static final long serialVersionUID = 8808955142529638792L;

    /**
     * 账号Id
     */
    private Long accountId;

    private Long userId;
    /**
     * 账号
     */
    private String name;

    /**
     * 注册时间
     */
    private Timestamp regtime;

    /**
     * 最近登录时间
     */
    private Timestamp loginDate;

    /**
     * 登录次数
     */
    private Integer loginTimes;

    /**
     * 中台用户id
     */
    private Long memberId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRegtime() {
        return regtime;
    }

    public void setRegtime(Timestamp regtime) {
        this.regtime = regtime;
    }

    public Timestamp getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Timestamp loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
