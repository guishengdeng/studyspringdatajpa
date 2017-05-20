package com.biz.rest.controller.voucher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.voucher.VoucherTypeVo;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.transformer.voucher.VoucherTypeRoToVoucherTypeVo;
import com.biz.soa.feign.client.voucher.VoucherTypeFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.google.common.collect.Lists;

@RestController
@RequestMapping("/voucherType")
public class VoucherTypeController extends BaseRestController {

    @Autowired
    private VoucherTypeFeignClient voucherTypeService;

    /**
     * 获取所有优惠券类型
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("all")
    public JSONResult allVoucherTypes(HttpServletRequest request, HttpServletResponse response) {
        List<VoucherTypeRo> roList = voucherTypeService.allVoucherTypesInApp();
        List<VoucherTypeVo> voList = Lists.transform(roList, new VoucherTypeRoToVoucherTypeVo());
        return new JSONResult(voList);
    }

}
