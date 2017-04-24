package com.biz.gbck.vo.user;

import com.biz.gbck.enums.user.Sex;
import java.io.Serializable;

/**
 * 类说明：
 *
 * @author xiaoyasong
 * @version 创建时间：2017年3月6日 下午5:18:25
 * @E-mail:yasong.xiao@biz-united.com.cn
 */

public class UpdateUserResponseVo implements Serializable {

    private static final long serialVersionUID = -722471285046236746L;

    /**
     * 用户Id(非中台)
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String username;

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
    private Sex sex;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

} 
