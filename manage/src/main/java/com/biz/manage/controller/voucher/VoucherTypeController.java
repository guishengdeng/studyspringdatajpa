package com.biz.manage.controller.voucher;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.dao.mysql.po.voucher.VoucherExpireType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherLimitType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypeStatus;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.voucher.VoucherTypeVo;
import com.biz.manage.util.AuthorityUtil;
import com.biz.service.product.backend.CategoryService;
import com.biz.service.security.interfaces.AdminService;
import com.biz.soa.feign.client.org.ShopTypeFeignClient;
import com.biz.soa.feign.client.voucher.VoucherTypeFeignClient;

/**
 * 
 * @ClassName: VoucherTypeController 
 * @Description: 优惠券类型控制器
 */
@Controller
@RequestMapping("/manage/voucherType")
//@Secured("ROLE_VOUCHERTYPE")
public class VoucherTypeController {

    private static final Logger logger = LoggerFactory.getLogger(VoucherTypeController.class);

    @Autowired
    private VoucherTypeFeignClient voucherTypeFeignClient;

    @Autowired
    private CategoryService categoryService;
    
    @Autowired 
    private AdminService manager;
    
    @Autowired 
    private ShopTypeFeignClient shopTypeFeignClient;
    
    /**
     * @Description: 进入优惠券类型列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('OPT_VOUCHERTYPE_LIST')")	
    public ModelAndView listVoucherTypes() {
        List<VoucherTypeRo> voucherTypeRos = voucherTypeFeignClient.allVoucherTypesInApp();
        Collections.sort(voucherTypeRos,new Comparator<VoucherTypeRo>(){  
            public int compare(VoucherTypeRo arg0, VoucherTypeRo arg1) {  
                return arg1.getId().compareTo(arg0.getId());  
            }  
        });  
        ModelAndView view = new ModelAndView("/voucherTypes/list");
        view.addObject("voucherTypes", voucherTypeRos);
        return view;
    }

    /**
     * * @Description: 进入新建优惠券类型页面
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('OPT_VOUCHERTYPE_ADD')")
    public ModelAndView createVoucherType() {
        logger.debug("Received /voucherType/add GET request");
        
        ModelAndView view = new ModelAndView("voucherTypes/add");
        // 可用管理员
        List<Admin> listEnableAdmins = manager.listEnableAdmins();
        view.addObject("listEnableAdmins", listEnableAdmins);
        
        // 商户类型
        List<ShopTypeRo> shopTypes = shopTypeFeignClient.findAllShopTypeRo();
        view.addObject("shopTypes", shopTypes);
        
        // 商品分类
        List<Category> categoryPos = categoryService.findAll();
        view.addObject("categories", categoryPos);
        view.addObject("voucherLimitTypes", VoucherLimitType.values());
        view.addObject("voucherExpireTypes", VoucherExpireType.values());
        view.addObject("voucherTypeStatusArray", VoucherTypeStatus.values());

        return view;
    }

    /**
     * 
     * @Description: 优惠券类型新建保存
     * @param voucherTypeVo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
//	@PreAuthorize("hasAuthority('OPT_VOUCHERTYPE_SAVE')")
    public ModelAndView saveCreate(VoucherTypeVo voucherTypeVo) {
        voucherTypeVo.setIssuerName(AuthorityUtil.getLoginUsername());
        voucherTypeFeignClient.save(voucherTypeVo);
        return new ModelAndView("redirect:/manage/voucherType/list.do");
    }
    
    /**
     * 
     * @Description: 到优惠券类型详情页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView details(@RequestParam("id") Long id) {
    	ModelAndView view = new ModelAndView("/voucherTypes/details");
    	   
        VoucherTypePo voucherTypePo = voucherTypeFeignClient.getVoucherTypeById(id);
        view.addObject("voucherTypePo", voucherTypePo);
        
        return view;
    }    

    /**
     * 
     * @Description: 到优惠券类型修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('OPT_VOUCHERTYPE_EDIT')")
    public ModelAndView editVoucherType(@RequestParam("id") Long id) {
        VoucherTypePo voucherTypePo = voucherTypeFeignClient.getVoucherTypeById(id);
        ModelAndView view = new ModelAndView("/voucherTypes/edit", "voucherType", voucherTypePo);
        return view;
    }

    /**
     * 
     * @Description: 修改某个优惠券类型
     * @param voucherTypeVo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
//	@PreAuthorize("hasAuthority('OPT_VOUCHERTYPE_UPDATE')")
    public ModelAndView saveEdit(VoucherTypeVo voucherTypeVo) {
    	voucherTypeFeignClient.update(voucherTypeVo);
          return new ModelAndView("redirect:/manage/voucherType/list.do");
    }
    
    /**
     * 
     * @Description: ajax更新优惠券类型的数量
     * @param id  优惠券类型ID
     * @param addIssueCount  数量
     * @return
     */
    @RequestMapping(value = "addIssueCount", method = RequestMethod.POST) 
    @ResponseBody
    public String addIssueCount(
    		@RequestParam("id") Long id,
    		@RequestParam("addIssueCount") int addIssueCount){
    	String result  = "error";
        try {
        	this.voucherTypeFeignClient.addVoucherTypeIssueCount(id, addIssueCount);
			result = "success";
		} catch (Exception e) {
			logger.error("更新优惠券数量异常,id["+id+"],addIssueCount["+addIssueCount+"]，异常信息["+e.getMessage()+"]",e);
		}
        return result;
    }     

    /**
     * 
     * @Description: 删除优惠券类型
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
//	@PreAuthorize("hasAuthority('OPT_VOUCHERTYPE_DELETE')")
    public ModelAndView deleteVoucherType(@RequestParam("id") Long id) {
    	voucherTypeFeignClient.deleteVoucherType(id);
        return new ModelAndView("redirect:/manage/voucherType/list.do");
    }
}
