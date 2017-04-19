package com.biz.service.advertisementtransformer;

import com.biz.gbck.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.google.common.base.Function;
import org.springframework.beans.BeanUtils;

/**
 * Created by xys on 2017/4/18.
 */
public class AdvertisementVo2AdvertisementRo implements Function<AdvertisementVo,AdvertisementRo> {

    @Override
    public AdvertisementRo apply(AdvertisementVo advertisementVo) {
        if (advertisementVo == null){
            return null;
        }
        AdvertisementRo advertisementRo = new AdvertisementRo();
        BeanUtils.copyProperties(advertisementVo, advertisementRo);
        return advertisementRo;
    }
}
