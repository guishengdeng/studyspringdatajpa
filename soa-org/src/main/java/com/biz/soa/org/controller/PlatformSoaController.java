package com.biz.soa.org.controller;

import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatformResSearchVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.soa.org.service.interfaces.PlatformSoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 平台公司
 * Created by dylan on 17-5-8.
 */
@RestController
@RequestMapping("soa/platform")
public class PlatformSoaController {

    @Autowired
    private PlatformSoaService platformSoaService;


    @RequestMapping(value = "findPlatformList", method = RequestMethod.POST)
    public PageVO<PlatformResSearchVo> findPlatformList(@RequestBody PlatformSearchVo vo) {
        return platformSoaService.findPlatformList(vo);
    }

    @RequestMapping(value = "findPartnerList", method = RequestMethod.POST)
    public PageVO<PartnerSearchResVo> findPartnerList(@RequestBody PartnerSearchVo vo) {
        return platformSoaService.findPartnerList(vo);
    }

    @RequestMapping(value = "findPartnerById", method = RequestMethod.POST)
    public PartnerSearchResVo findPartnerById(@RequestParam("id") Long id) {
        return platformSoaService.findPartnerById(id);
    }




}
