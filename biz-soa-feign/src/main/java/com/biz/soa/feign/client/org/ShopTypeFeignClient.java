package com.biz.soa.feign.client.org;

import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.vo.org.ShopTypeCreateReqVo;
import com.biz.gbck.vo.org.ShopTypeUpdateReqVo;
import com.biz.soa.feign.hystrix.org.ShopTypeFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 商户类型
 * @author: dylan
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = ShopTypeFeignClientHystrix.class)
public interface ShopTypeFeignClient {

    @RequestMapping(value = "soa/shopTypes/findAllShopTypeRo", method = RequestMethod.POST)
    List<ShopTypeRo> findAllShopTypeRo();

    @RequestMapping(value = "soa/shopTypes/saveShopType", method = RequestMethod.POST)
    void saveShopType(@RequestBody ShopTypeCreateReqVo vo);

    @RequestMapping(value = "soa/shopTypes/findShopTypeRo", method = RequestMethod.POST)
    ShopTypeRo findShopTypeRo(@RequestParam("id") Long id);

    @RequestMapping(value = "soa/shopTypes/updateShopType", method = RequestMethod.POST)
    ShopTypeRo updateShopType(@RequestBody ShopTypeUpdateReqVo vo);

    @RequestMapping(value = "soa/shopTypes/disableShopType", method = RequestMethod.POST)
    Boolean disableShopType(@RequestParam("id") Long id);


}
