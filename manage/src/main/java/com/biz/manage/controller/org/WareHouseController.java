package com.biz.manage.controller.org;

import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.gbck.vo.warehouse.WarehouseResponseVo;
import com.biz.service.warehouse.backend.WarehouseBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * WareHouseController
 *
 * @author guisheng.deng
 * @date 2017年05月25日
 * @reviewer
 * @description
 * @see
 */
@Controller
@RequestMapping("/wareHouse")
@Secured("ROLE_WAREHOUSE")
public class WareHouseController {
    @Autowired
    private WarehouseBackendService warehouseBackendService;

    @PostMapping("/findByCompanyLevel")
    @PreAuthorize("hasAuthority('OPT_WAREHOUSE_LIST')")
    @ResponseBody
    public List<WarehouseResponseVo> findByCompanyLevel(CompanyLevel companyLevel){
        return warehouseBackendService.getRespVoByCompanyLevel(companyLevel);
    }
}