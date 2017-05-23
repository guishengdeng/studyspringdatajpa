package com.biz.soa.feign.hystrix.org;

import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.vo.org.ShopTypeCreateReqVo;
import com.biz.gbck.vo.org.ShopTypeUpdateReqVo;
import com.biz.soa.feign.client.org.ShopTypeFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created dylan
 */
@Component
public class ShopTypeFeignClientHystrix implements ShopTypeFeignClient {


    @Override
    public List<ShopTypeRo> findAllShopTypeRo() {
        return null;
    }

    @Override
    public void saveShopType(@RequestBody ShopTypeCreateReqVo vo) {

    }

    @Override
    public ShopTypeRo findShopTypeRo(@RequestParam("id") Long id) {
        return null;
    }

    @Override
    public ShopTypeRo updateShopType(@RequestBody ShopTypeUpdateReqVo vo) {
        return null;
    }

    @Override
    public Boolean disableShopType(@RequestParam("id") Long id) {
        return null;
    }
}
