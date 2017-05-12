package com.biz.manage.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.core.util.JsonUtil;
import com.biz.manage.controller.BaseController;
import com.biz.manage.vo.product.ProductPriceReqVo;
import com.biz.manage.vo.product.ProductPriceVO;

/**
 * @author xs
 * @date 2017年05月02日
 * @reviewer
 */
@Controller
@RequestMapping("product")
//@Secured("ROLE_PRICE")
public class ProductPriceController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ProductPriceController.class);
    
    @GetMapping("price/list")
    public ModelAndView list() {
        logger.info(" ProductPriceController ModelAndView list ");
        List<ProductPriceVO> list=listProductPriceVO();
        Page<ProductPriceVO> page = new PageImpl<ProductPriceVO>(list);
        return new ModelAndView("product/price/list", "page", page);
    }
    
    @PostMapping("price/update")
    //@PreAuthorize("hasAuthority('OPT_PRICE_UPDATE')")
    @ResponseBody
    public Boolean update(String data) {
        logger.info("ProductPriceController update ");
        logger.info(" data {}",data);
        List<ProductPriceReqVo> list=JsonUtil.json2Obj(data, List.class, ProductPriceReqVo.class);
        if(CollectionUtils.isNotEmpty(list)){
            for (ProductPriceReqVo item : list) {
                System.out.println(item.getId()+"|"+item.getPrice());
            }
        }
        return true;
    }
    
    private List<ProductPriceVO> listProductPriceVO(){
        List<ProductPriceVO> list= new ArrayList<>();
        String s1="",s2="";
        for (int i = 0; i < 50; i++) {
            s1=String.valueOf(i+1);
            s2=StringUtils.leftPad(s1, 3, '0');
            ProductPriceVO vo=new ProductPriceVO();
            vo.setId(s1);
            vo.setProductCode("P".concat(s2));
            vo.setProductName("商品".concat(s2));
            vo.setPurchasePrice(100);
            vo.setSalePrice(100);
            list.add(vo);
        }
        
        return list;
    }
}
