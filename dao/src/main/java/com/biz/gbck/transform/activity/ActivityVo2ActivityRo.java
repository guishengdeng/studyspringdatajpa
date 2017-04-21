package com.biz.gbck.transform.activity;

import com.biz.gbck.vo.activity.frontend.ActivityVo;
import com.biz.gbck.dao.redis.ro.activity.ActivityRo;
import com.google.common.base.Function;
import org.springframework.beans.BeanUtils;

/**
 * Created by xys on 2017/4/18.
 */
public class ActivityVo2ActivityRo implements Function<ActivityVo,ActivityRo> {

    @Override
    public ActivityRo apply(ActivityVo activityVo) {
        if (activityVo == null){
            return null;
        }
        ActivityRo activityRo = new ActivityRo();
        BeanUtils.copyProperties(activityVo, activityRo);
        return activityRo;
    }
}
