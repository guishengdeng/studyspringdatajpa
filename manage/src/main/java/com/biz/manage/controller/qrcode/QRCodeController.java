package com.biz.manage.controller.qrcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.manage.controller.BaseController;
import com.biz.service.qrcode.interfaces.QRCodeService;

/**
 * @author xs
 * @date 2017年04月15日
 * @reviewer
 */
@Controller
@RequestMapping("qrcode")
@Secured("ROLE_QRCODE")
public class QRCodeController extends BaseController{
    @Autowired
    private QRCodeService qrcodeService;
    
    @GetMapping("list")
    @PreAuthorize("hasAuthority('OPT_QRCODE_LIST')")
    public ModelAndView list(@RequestParam(value = "status", required = false, defaultValue = "ENABLE") CommonStatusEnum status) {
        List<QRCodePO> qrcodes = qrcodeService.listByStatus(status);
        return new ModelAndView("qrcode/list", "qrcodes", qrcodes).addObject("enabled", status.isEnable());
    }
}
