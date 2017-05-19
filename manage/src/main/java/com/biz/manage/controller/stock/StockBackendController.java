package com.biz.manage.controller.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.manage.controller.BaseController;
import com.biz.service.stock.StockBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lzz on 2017/5/17.
 */
@Controller
@Secured("ROLE_STOCK")
@RequestMapping("goods")
public class StockBackendController extends BaseController {

    @Autowired
    private StockBackendService stockBackendService;GIT GI

    @GetMapping("stock")
    @PreAuthorize("hasAuthority('OPT_STOCK_LIST')")
    public ModelAndView list(StockShowVo stockShowVo) {
        Page<Stock> page = stockBackendService.findList(stockShowVo);
        return new ModelAndView("goods/stock").addObject("page", page);
    }
}
