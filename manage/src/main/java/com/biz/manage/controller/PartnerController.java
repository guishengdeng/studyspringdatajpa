package com.biz.manage.controller;

import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.service.partner.interfaces.PartnerService;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.partner.PartnerRegisterReqVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by haibin.tang on 2017/5/8.
 *
 * 合伙人
 */
@Controller
@RequestMapping("partner")
public class PartnerController {

    private Logger logger = LoggerFactory.getLogger(PartnerController.class);

    @Autowired
    private PartnerService partnerService;

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 处理合伙人注册
     * @param reqVo
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public JSONResult register(PartnerRegisterReqVo reqVo) {
        try {
            partnerService.createPartner(reqVo);
            return new JSONResult();
        } catch (PartnerExceptions partnerExceptions) {
            return new JSONResult(partnerExceptions.getCode(), partnerExceptions.getMessage());
        }catch (Exception e) {
            logger.error("处理合伙人注册失败", e);
            return new JSONResult(ExceptionCode.Global.SERVER_DATA_ERROR, "服务器错误,请稍后重试");
        }
    }

    @GetMapping("validPartnerIsExist")
    @ResponseBody
    public JSONResult validPartnerIsExist(String username) {
        if(StringUtils.isBlank(username)) {
            return new JSONResult(false);
        }
        return new JSONResult(partnerService.validAccountIsExist(username));
    }
}
