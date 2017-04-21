package com.biz.gbck.transform.advertisement;

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
        advertisementVo.setId(advertisementRo.getId());
        advertisementVo.setPicturesLink(advertisementRo.getPicturesLink());
        advertisementVo.setClickLink(advertisementRo.getClickLink());
        advertisementVo.setBeginTimestamp(advertisementRo.getBeginTimestamp());
        advertisementVo.setEndTimestamp(advertisementRo.getEndTimestamp());
        advertisementVo.setPicturesLink(advertisementRo.getPicturesLink());
        advertisementVo.setResidenceTime(advertisementRo.getResidenceTime());
        advertisementVo.setPriority(advertisementRo.getPriority() == null ? 1 : advertisementRo.getPriority().intValue());
        return advertisementVo;
    }
}
