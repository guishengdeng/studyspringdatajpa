package com.biz.soa.org.service.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.biz.exception.SMSException;
import com.biz.gbck.common.com.AlidayuTemplateCode;
import com.biz.gbck.common.com.SMSType;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.UserPo;

import java.util.List;

/**
 * @author: liubin
 * @date 5/12/17 09:42
 */
public interface SmsSoaService {

    /**
     * 发送短信
     *
     * @param mobile    手机号
     * @param smsType   短信类型
     * @param smsCode   验证码
     * @param requestIp 客户端地址
     */
    void sendSMS(String mobile, SMSType smsType, String smsCode,
                        AlidayuTemplateCode templateCode, String templateParam, String requestIp)
            throws SMSException, CommonException;


    /**
     * 校验短信验证码是否正确,如果正确则使该短信验证码失效
     *
     * @param mobile  手机号
     * @param smsType 短信类型
     * @param smsCode 验证码
     */
    public Boolean validateAndDisableSMSCode(String mobile, SMSType smsType, String smsCode) throws CommonException;


    /**
     * 校验短信验证码是否正确,并使短信验证码继续有效
     *
     * @param mobile  手机号
     * @param smsType 短信类型
     * @param smsCode 验证码
     */
    public Boolean validateSMSCode(String mobile, SMSType smsType, String smsCode)
            throws CommonException;



    /**
     * 发送短信
     *
     * @param mobile       手机号
     * @param templateCode 短信模板编号
     */
    public void SMSMsg(String mobile, AlidayuTemplateCode templateCode, JSONObject templateParam)
            throws SMSException, CommonException;


    public void sendMessage2Users(List<UserPo> users, String templateCode, String content)
            throws CommonException;




}
