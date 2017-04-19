package com.biz.service.activity.interfaces;

import com.biz.gbck.activity.frontend.ActivityVo;
import com.biz.gbck.advertisement.frontend.AdvertisementVo;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */
public interface ActivityService {

    void saveOrUpdateActivity(ActivityVo activityVo);

    List<ActivityVo> findAllActivitys();
}
