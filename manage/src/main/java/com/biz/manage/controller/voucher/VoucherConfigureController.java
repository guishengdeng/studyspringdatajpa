package com.biz.manage.controller.voucher;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.voucher.VoucherCfgVo;
import com.biz.soa.feign.client.voucher.VoucherConfigureFeignClient;
import com.biz.soa.feign.client.voucher.VoucherTypeFeignClient;

/**
 * 
 * @ClassName: VoucherConfigureController 
 * @Description: 优惠券配置控制器
 *  
 */
@Controller
@RequestMapping("/manage/voucherconfigure")
public class VoucherConfigureController {
	
	private static final Logger logger = LoggerFactory.getLogger(VoucherConfigureController.class);

    @Autowired
    private VoucherTypeFeignClient voucherTypeService;
    
    @Autowired
    private VoucherConfigureFeignClient voucherConfigureService;

    /**
     * 
     * @Description: 到优惠券配置页面
     * @return
     */
    @RequestMapping(value = "/toVoucherconfigure", method = RequestMethod.GET)
    public ModelAndView toVoucherconfigure() {
    	ModelAndView view = new ModelAndView("/voucherconfigure/set");
    	 
        List<VoucherTypeRo> voucherTypeRos = voucherTypeService.allVoucherTypesInApp();
        view.addObject("voucherTypeRos", voucherTypeRos);
        
        VoucherConfigure[] voucherConfigures = VoucherConfigure.values();
        view.addObject("voucherConfigures", voucherConfigures);

        List<VoucherCfgVo> voucherCfgs = voucherConfigureService.findAll();
        view.addObject("vouchercfglist",voucherCfgs);
        return view;
    }

    /**
     * 
     * @Description: 优惠券配置设置
     * @param voucherconfigure
     * @param voucherType
     * @param isOpen  1开启   0关闭
     * @return
     */
    @RequestMapping(value = "/saveVoucherconfigure", method = RequestMethod.POST)
    @ResponseBody
    public String saveVoucherconfigure(VoucherConfigure voucherconfigure,Long voucherType,String quantity,String isOpen) {
    	String result  = "error";
        try {
            if(StringUtils.contains("0",isOpen)) {
            	voucherConfigureService.delete(voucherconfigure, voucherType);
            } else {
            	voucherConfigureService.save(voucherconfigure, voucherType,Integer.parseInt(quantity));
            }
			result = "success";
		} catch (Exception e) {
			logger.error("优惠券配置设置,voucherconfigure["+voucherconfigure+"],voucherType["+voucherType+"]，异常信息["+e.getMessage()+"]",e);
		}
        return result;
    }
    
    /**
     * 
     * @Description: 根据运营类型获取对应的运营配置信息
     * @param voucherconfigure
     * @return
     */
    @RequestMapping(value="/getVoucherconfigure",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getVoucherconfigure(String voucherconfigure) {
        try {
        	if(voucherconfigure!=null && !"".equals(voucherconfigure)){
        		VoucherConfigureRo voucherConfigureRo = this.voucherConfigureService.getVoucherConfigureRo(voucherconfigure);
        		if(voucherConfigureRo!=null){
            		return String.valueOf(voucherConfigureRo.getVoucherTypes());
        		}
        	}       	
		} catch (Exception e) {
			logger.error("根据运营类型获取对应的运营配置信息异常，voucherconfigure["+voucherconfigure+"]，异常信息["+e.getMessage()+"]",e);
		}
        return null;
    }

}
