package com.biz.manage.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.gbck.common.org.ProductStatus;
import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.service.product.frontend.bbc.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductSelectTag extends TagSupport {

    private static final long serialVersionUID = -2123253186591293149L;

    private static final String PRODUCT_TAG_DATA = "product_tag_data";
    
    @Autowired
    private ProductService productService;

    @Override 
    public int doStartTag() throws JspException {
        if (pageContext.getRequest().getAttribute(PRODUCT_TAG_DATA) == null) {
//            List<ProductRo> onSaleProducts =
//                productService.findProductRosByStatus(ProductStatus.ON_SALE);
        	 List<ProductRo> onSaleProducts = new ArrayList<>();
        	 ProductRo pro = new ProductRo();
        	 pro.setName("test");
        	 pro.setId("123");
        	 onSaleProducts.add(pro);
            pageContext.getRequest().setAttribute(PRODUCT_TAG_DATA, onSaleProducts);
        }
        return SKIP_BODY;
    }
}