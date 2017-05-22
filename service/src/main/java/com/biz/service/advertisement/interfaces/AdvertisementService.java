package com.biz.service.advertisement.interfaces;

import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.vo.advertisement.frontend.request.AdvertisementRequestVo;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */

public interface AdvertisementService {

    void saveOrUpdateAdvertisement(AdvertisementRequestVo advertisementVo);

    AdvertisementRo findById(String id);

    void delete(String id);

    void disable(String id);

    List<AdvertisementVo> findAdvertisementByStatus(CommonStatusEnum status);
}
