package com.biz.vo.org;




import com.biz.core.codec.PasswordUtil;
import com.biz.gbck.common.model.InitGlobalParamsWithToken;
import com.biz.gbck.common.org.UserStatus;
import com.biz.gbck.common.vo.WithoutBindRequestParams;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.support.web.assist.GlobalParams;
import org.codelogger.utils.StringUtils;

import java.sql.Timestamp;

/**
 * Created by defei on 3/16/16.
 */
public class UserRegisterReqVo extends WithoutBindRequestParams
    implements InitGlobalParamsWithToken {

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 短信验证码
     */
    private String smsCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 推荐人
     */
    private String inviterCode;

    /**
     * 全局参数
     */
    private GlobalParams globalParams;

    /**
     * 原始密码
     */
    private String originalPassword;

    /**
     * 注册Ip
     */
    private String ip;

    private String token;


    public GlobalParams getGlobalParams() {
        return globalParams;
    }


    public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserPo toUserPo() {

        UserPo userPo = new UserPo();
        return toUserPo(userPo);
    }

    public UserPo toUserPo(UserPo userPo) {
        userPo.setMobile(mobile);
        userPo.setAccount(mobile);
        userPo.setPassword(password);
        userPo.setOriginalPassword(PasswordUtil.aes2Original(this.getOriginalPassword()));
        userPo.setName(mobile);
        userPo.setRegisterIP(ip);
        userPo.setLastLoginIP(ip);
        userPo.setRegisterDeviceId(globalParams.getDeviceId());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        userPo.setCreateTime(now);
        userPo.setLastLoginTime(now);
        userPo.setStatus(UserStatus.NORMAL.getValue());
        return userPo;
    }

    public String getMobile() {
        return StringUtils.trimAllWhitespace(mobile);
    }

    public String getSmsCode() {
        return smsCode;
    }

    public String getPassword() {
        return password;
    }

    public String getInviterCode() {
        return inviterCode;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }


    public String getOriginalPassword() {
        return originalPassword;
    }


    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }


}
