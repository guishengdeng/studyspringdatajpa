package com.biz.soa.org.controller;

import com.biz.soa.base.SoaBaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liubin
 * @date 5/9/17 09:38
 */
@RestController
@RequestMapping(value = "/org/shop")
public class ShopController extends SoaBaseController {

    @GetMapping(value = "/test")
    private String test() {

        return "test";
    }

}
