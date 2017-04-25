package com.biz.gbck.dao.redis.repository.advertisement;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.activity.ActivityRo;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.util.RedisUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by xys on 2017/4/18.
 */
@Repository
public class AdvertisementRedisDao extends CrudRedisDao<AdvertisementRo, String> {

    @Override
    public void save(AdvertisementRo ro){
        String statusMappingKey = statusMappingKey(ro.getStatus());
        super.zadd(statusMappingKey,ro.getCreateTimestamp(),ro.getId());
        super.save(ro);
    }

    @Override
    public void delete(AdvertisementRo ro) {
        super.delete(ro);
        zrem(statusMappingKey(ro.getStatus()), RedisUtil.toByteArray(ro.getId()));
    }

    private String statusMappingKey(Integer status) {
        return getKeyByParams("status",status);
    }

    public List<AdvertisementRo> findAdvertisementByStatus(Integer status) {
        String key = statusMappingKey(status);
        Set<byte[]> bytes = zRange(key, 0, -1);
        List<AdvertisementRo> ros = findByIds(bytes);
        return ros;
    }
}
