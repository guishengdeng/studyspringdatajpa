package com.biz.manage.controller.stock;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.vo.product.ProductShowVo;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.manage.controller.BaseController;
import com.biz.service.stock.StockBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public ModelAndView Search(@ModelAttribute("productShowVo") ProductShowVo productShowVo) {
        Page<StockShowVo> page= stockBackendService.searchList(productShowVo);
        return new ModelAndView("goods/stock").addObject("page", page);
    }

}
