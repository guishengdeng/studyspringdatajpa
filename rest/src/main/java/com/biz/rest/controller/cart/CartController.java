package com.biz.rest.controller.cart;

import com.biz.rest.controller.BaseRestController;
import com.biz.support.web.handler.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车controller
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseRestController {


    @RequestMapping("add")
    public JSONResult addCart(HttpServletRequest request) {
        return null;
    }

}
