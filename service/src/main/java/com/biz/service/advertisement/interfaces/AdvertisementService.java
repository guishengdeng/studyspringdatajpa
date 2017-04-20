package com.biz.service.advertisement.interfaces;

import com.biz.core.page.PageResult;
import com.biz.gbck.advertisement.frontend.AdvertisementQueryParamVo;
import com.biz.gbck.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.advertisement.frontend.request.AdvertisementRequestVo;
import com.biz.gbck.dao.redis.ro.activity.ActivityRo;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */

public interface AdvertisementService {

    void saveOrUpdateAdvertisement(AdvertisementRequestVo advertisementVo);

    PageResult<AdvertisementVo> findAdvertisement(AdvertisementQueryParamVo vo);

    List<AdvertisementVo> findAllAdvertisements();

    AdvertisementRo findById(String id);

    void delete(String id);
}
