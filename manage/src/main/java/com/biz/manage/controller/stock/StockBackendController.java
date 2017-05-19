package com.biz.manage.controller.stock;

import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.manage.controller.BaseController;
import com.biz.service.stock.StockBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by lzz on 2017/5/17.
 */
@RestController
@Secured("ROLE_STOCK")
@RequestMapping("goods")
public class StockBackendController extends BaseController {

    @Autowired
    private StockBackendService stockBackendService;

    @GetMapping("stock")
    @PreAuthorize("hasAuthority('OPT_STOCK_LIST')")
    public ModelAndView list(StockShowVo stockShowVo) {
//        Page<Stock> page = stockBackendService.findList(stockShowVo);
//        return new ModelAndView("goods/stock").addObject("page", page);
        List<StockShowVo> list = stockBackendService.find();
        return new ModelAndView("goods/stock").addObject("list",list);
    }
}
