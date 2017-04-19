package com.biz.service.advertisementtransformer;

import com.biz.gbck.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.google.common.base.Function;
import org.springframework.beans.BeanUtils;

/**
 * Created by xys on 2017/4/18.
 */
public class AdvertisementRo2AdvertisementVo implements Function<AdvertisementRo,AdvertisementVo> {

    @Override
    public AdvertisementVo apply(AdvertisementRo advertisementRo) {
        if (advertisementRo == null){
            return null;
        }
        AdvertisementVo advertisementVo = new AdvertisementVo();
        BeanUtils.copyProperties(advertisementRo, advertisementVo);
        return null;
    }
}
