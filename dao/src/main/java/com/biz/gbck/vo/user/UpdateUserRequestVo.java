package com.biz.gbck.vo.user;

import com.biz.gbck.enums.user.Sex;
import com.biz.gbck.vo.IRequestVo;

/**
 * @author lei
 * @date 2017年02月15日
 * @reviewer
 * @see
 */
public class UpdateUserRequestVo implements IRequestVo {

    private static final long serialVersionUID = 4076099125560599038L;

    /**
     * 用户Id(非中台)
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String username;
    /**
     * 登录账户名
     */
    private String accountName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Sex sex = Sex.SECRECY;
    /**
     * 渠道编码
     */
    private String channelCode;

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
