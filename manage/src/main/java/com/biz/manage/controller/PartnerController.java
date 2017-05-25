package com.biz.manage.controller;

import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.ali.oss.util.OssUtil;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.partner.ApprovalStatus;
import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.manage.servlet.ManageServlet;
import com.biz.service.partner.interfaces.PartnerService;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.partner.PartnerDetailRespVo;
import com.biz.vo.partner.PartnerRegisterReqVo;
import com.biz.vo.partner.PartnerReqVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by haibin.tang on 2017/5/8.
 * <p>
 * 合伙人
 */
@Controller
@RequestMapping("partner")
public class PartnerController {

    private Logger logger = LoggerFactory.getLogger(PartnerController.class);

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private OssConfig config;

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 合伙人审核页面
     *
     * @return
     */
    @RequestMapping("list")
    @PreAuthorize("hasAuthority('OPT_PARTNER_LIST')")
    public ModelAndView list(PartnerReqVo partnerSearchReqVo, HttpServletRequest request) {
        ModelAndView result = new ModelAndView("manage/partner/list");
        result.addObject("condition", partnerSearchReqVo);
        result.addObject("partners", partnerService.findAllByCondition(partnerSearchReqVo));
        return result;
    }

    /**
     * 页面
     *
     * @param id
     * @return
     */
    @RequestMapping("edit/{id}")
    @PreAuthorize("hasAuthority('OPT_PARTNER_EDIT')")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView result = new ModelAndView("/manage/partner/edit");
        PartnerDetailRespVo detailRespVo = partnerService.findById(id);
        appendPicPrefix(detailRespVo);
        if(detailRespVo != null && detailRespVo.getApprovalStatus() == ApprovalStatus.UNDER_REVIEW) {
            result.addObject("partner", detailRespVo);
        }
        return result;
    }

    /**
     * 明细
     *
     * @param id
     * @return
     */
    @RequestMapping("detail/{id}")
    @PreAuthorize("hasAuthority('OPT_PARTNER_DETAIL')")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView result = new ModelAndView("/manage/partner/detail");
        PartnerDetailRespVo detailRespVo = partnerService.findById(id);
        appendPicPrefix(detailRespVo);
        result.addObject("partner", detailRespVo);
        return result;
    }

    private void appendPicPrefix(PartnerDetailRespVo detailRespVo) {
        if (detailRespVo == null) {
            return;
        }
        if (StringUtils.isNoneBlank(detailRespVo.getBusinessLicense())) {
            detailRespVo.setBusinessLicense(OssUtil.getOssResourceUri(config.getProductBucketName(), config.getRemoteEndpoint(), detailRespVo.getBusinessLicense()));
        }
        if (StringUtils.isNoneBlank(detailRespVo.getWinePermit())) {
            detailRespVo.setWinePermit(OssUtil.getOssResourceUri(config.getProductBucketName(), config.getRemoteEndpoint(), detailRespVo.getWinePermit()));
        }
    }

    @PostMapping("audit")
    @PreAuthorize("hasAuthority('OPT_PARTNER_AUDIT')")
    @ResponseBody
    public JSONResult audit(PartnerReqVo reqVo) {
        try {
            Admin currentAdmin = ManageServlet.getAdmin();
            if (currentAdmin != null) {
                reqVo.setOperator(currentAdmin.getUsername());
            }
            partnerService.updatePartnerStatus(reqVo);
            return new JSONResult();
        } catch (CommonException e) {
            return new JSONResult(ExceptionCode.Global.INFO_TO_USER, e.getMessage());
        } catch (Exception e) {
            logger.error("审核合伙人失败", e);
            return new JSONResult(ExceptionCode.Global.SERVER_DATA_ERROR, "服务器错误,请稍后重试");
        }
    }

    /**
     * 处理合伙人注册
     *
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
        } catch (Exception e) {
            logger.error("处理合伙人注册失败", e);
            return new JSONResult(ExceptionCode.Global.SERVER_DATA_ERROR, "服务器错误,请稍后重试");
        }
    }

    @GetMapping("validPartnerIsExist")
    @ResponseBody
    public JSONResult validPartnerIsExist(String username) {
        if (StringUtils.isBlank(username)) {
            return new JSONResult(false);
        }
        return new JSONResult(partnerService.validAccountIsExist(username));
    }
}
