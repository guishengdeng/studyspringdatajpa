package com.biz.soa.org.controller;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.vo.org.ShopTypeCreateReqVo;
import com.biz.gbck.vo.org.ShopTypeUpdateReqVo;
import com.biz.soa.org.service.interfaces.ShopTypeSoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 商户类型
 * dylan on 2017.5.2
 */
@RestController
@RequestMapping("soa/shopTypes")
public class ShopTypeSoaController {

    @Autowired
    private ShopTypeSoaService typeSoaService;


    @RequestMapping(value = "findAllShopTypeRo", method = RequestMethod.POST)
    public List<ShopTypeRo> findAllShopTypeRo() {
        return  typeSoaService.findAllShopTypeRo(ShopTypeStatus.NORMAL);
    }

    @RequestMapping(value = "saveShopType", method = RequestMethod.POST)
    public void saveShopType(@RequestBody ShopTypeCreateReqVo vo) {
        typeSoaService.saveShopType(vo);
    }

    @RequestMapping(value = "findShopTypeRo", method = RequestMethod.POST)
    public ShopTypeRo findShopTypeRo(@RequestParam("id") Long id) {
        return   typeSoaService.findShopTypeRo(id);
    }

    @RequestMapping(value = "updateShopType", method = RequestMethod.POST)
    public ShopTypeRo updateShopType(@RequestBody ShopTypeUpdateReqVo vo) throws CommonException {
        return typeSoaService.updateShopType(vo);
    }

    @RequestMapping(value = "disableShopType", method = RequestMethod.POST)
    public Boolean disableShopType(@RequestParam("id") Long id) {
        return typeSoaService.disableShopType(id);
    }




}
