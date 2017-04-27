package com.biz.manage.controller.qrcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.dao.mysql.po.qrcode.QRCodeResumePO;
import com.biz.gbck.vo.qrcode.QRCodeSearchVO;
import com.biz.manage.controller.BaseController;
import com.biz.service.qrcode.interfaces.QRCodeResumeService;
import com.biz.service.qrcode.interfaces.QRCodeService;

/**
 * @author xs
 * @date 2017年04月15日
 * @reviewer
 */
@Controller
@RequestMapping("qrcode")
@Secured("ROLE_QRCODE")
public class QRCodeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    private QRCodeService qrcodeService;

    @Autowired
    private QRCodeResumeService qrcodeResumeService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('OPT_QRCODE_LIST')")
    public ModelAndView list(@ModelAttribute("reqVo") QRCodeSearchVO reqVo) {
        Page<QRCodePO> qrcodes = qrcodeService.searchQRCode(reqVo);
        ModelAndView mav=new ModelAndView("qrcode/list");
        mav.addObject("qrcodes", qrcodes);
        return mav;
    }

    @PostMapping("delete")
    @PreAuthorize("hasAuthority('OPT_QRCODE_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String id) {
        try {
            qrcodeService.remove(id);
            return true;
        } catch (Exception e) {
            logger.info("Kill cat[{}] failed.", id, e);
            return false;
        }
    }

    /***** 二维码履历 **************************************************/
    @GetMapping("resume/list")
    @PreAuthorize("hasAuthority('OPT_QRCODE_RESUME_LIST')")
    public ModelAndView listQRCodeResume(@ModelAttribute("reqVo") QRCodeSearchVO reqVo) {
        logger.debug("进入  qrcode/resume/list 页面");
        Page<QRCodeResumePO> qrcodes = qrcodeResumeService.searchQRCodeResume(reqVo);
        ModelAndView mav=new ModelAndView("qrcode/resume/list");
        mav.addObject("qrcodes", qrcodes);
        return mav;
    }
}
