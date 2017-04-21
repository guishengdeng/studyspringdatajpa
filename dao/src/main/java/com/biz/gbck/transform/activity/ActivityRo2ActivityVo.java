package com.biz.gbck.transform.activity;

import com.biz.gbck.activity.frontend.ActivityVo;
import com.biz.gbck.dao.redis.ro.activity.ActivityRo;
import com.google.common.base.Function;
import org.springframework.beans.BeanUtils;

/**
 * Created by xys on 2017/4/18.
 */
public class ActivityRo2ActivityVo implements Function<ActivityRo,ActivityVo> {

    @Override
    public ActivityVo apply(ActivityRo activityRo) {
        if (activityRo == null){
            return null;
        }
        ActivityVo activityVo = new ActivityVo();
        BeanUtils.copyProperties(activityRo, activityVo);
        return activityVo;
    }
}
