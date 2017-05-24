package com.biz.manage.controller.shop;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.service.org.interfaces.ShopTypeService;
import com.biz.gbck.vo.org.ShopTypeCreateReqVo;
import com.biz.gbck.vo.org.ShopTypeUpdateReqVo;
import com.biz.soa.feign.client.org.ShopTypeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商户类型
 * dylan on 2017.5.2
 */
@Controller
@RequestMapping("shopTypes")
@Secured("ROLE_SHOPTYPE")
public class ShopTypeController {

    @Autowired
    private ShopTypeFeignClient shopTypeFeignClient;


    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOPTYPE_LIST')")
    public ModelAndView list() throws CommonException {
        return new ModelAndView("manage/shopType/list", "shopTypes",
            shopTypeFeignClient.findAllShopTypeRo());
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOPTYPE_SAVE')") public ModelAndView add()
        throws CommonException {
        return new ModelAndView("manage/shopType/detail");
    }

    @RequestMapping(method = RequestMethod.POST) @PreAuthorize("hasAuthority('OPT_SHOPTYPE_SAVE')")
    public ModelAndView submitAdd(ShopTypeCreateReqVo vo) throws CommonException {
        shopTypeFeignClient.saveShopType(vo);
        return new ModelAndView("redirect:/shopTypes.do");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOPTYPE_UPDATE')")
    public ModelAndView toUpdate(@PathVariable("id") Long id) throws CommonException {
        return new ModelAndView("manage/shopType/detail", "shopType",
            shopTypeFeignClient.findShopTypeRo(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOPTYPE_UPDATE')")
    public ModelAndView update(ShopTypeUpdateReqVo vo) throws CommonException {
        System.setProperty("file.encoding", "UTF-8");
        shopTypeFeignClient.updateShopType(vo);
        return new ModelAndView("redirect:/shopTypes.do");
    }

    @RequestMapping("/delete") @PreAuthorize("hasAuthority('OPT_SHOPTYPE_DELETE')")
    public ModelAndView delete(Long id) throws CommonException {
        shopTypeFeignClient.disableShopType(id);
        return new ModelAndView("redirect:/shopTypes.do");
    }



}
