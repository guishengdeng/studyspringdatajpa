package com.biz.service.activity;

import com.biz.gbck.activity.frontend.ActivityVo;
import com.biz.gbck.dao.redis.repository.activity.ActivityRedisDao;
import com.biz.gbck.dao.redis.ro.activity.ActivityRo;
import com.biz.service.AbstractBaseService;
import com.biz.service.activity.interfaces.ActivityService;
import com.biz.gbck.transform.activity.ActivityRo2ActivityVo;
import com.biz.gbck.transform.activity.ActivityVo2ActivityRo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */
@Service
public class ActivityServiceImpl extends AbstractBaseService implements ActivityService {

    @Autowired
    private ActivityRedisDao activityRedisDao;

    @Override
    public void saveOrUpdateActivity(ActivityVo req) {
        //TODO 定义异常信息
        String activityId = req.getId();
        ActivityRo ro;
        //如果没有id 则进行保存操作
        if (StringUtils.isBlank(activityId)) {
            //TODO 转换器
            req.setId(String.valueOf(idService.nextId()));
            ro = new ActivityVo2ActivityRo().apply(req);
        }else {
            ro = activityRedisDao.findOne(activityId);
            buildActivityRo(ro,req);
        }
        activityRedisDao.save(ro);
    }

    @Override
    public List<ActivityVo> findAllActivitys() {
        List<ActivityRo> ros = activityRedisDao.findAll();
        return Lists.transform(ros, new ActivityRo2ActivityVo());
    }

    @Override
    public ActivityRo findById(String id) {
        if (StringUtils.isNotBlank(id)){
            return activityRedisDao.findOne(id);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        activityRedisDao.delete(id);
    }

    public ActivityRo buildActivityRo(ActivityRo ro, ActivityVo vo) {
        if (vo == null){
            return null;
        }
        BeanUtils.copyProperties(ro, vo);
        return ro;
    }
}
