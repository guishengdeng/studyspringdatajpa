package com.biz.manage.controller.stock;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.vo.product.SearchVo;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.manage.controller.BaseController;
import com.biz.manage.servlet.ManageServlet;
import com.biz.service.security.interfaces.AdminService;
import com.biz.service.stock.StockBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lzz on 2017/5/17.
 */
@RestController
@Secured("ROLE_STOCK")
public class StockBackendController extends BaseController {

    @Autowired
    private StockBackendService stockBackendService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("stock")
    @PreAuthorize("hasAuthority('OPT_STOCK_LIST')")
    public ModelAndView list(@ModelAttribute("reqVo") SearchVo reqVo) {
        if (ManageServlet.getAdmin() != null) {
            Admin admin = adminService.getAdmin(ManageServlet.getAdmin().getUsername());
            reqVo.setCompanyId(admin.getCompany().getId());
        }
        Page<StockShowVo> page = stockBackendService.search(reqVo);
        return new ModelAndView("goods/stock").addObject("page", page);
    }

}
