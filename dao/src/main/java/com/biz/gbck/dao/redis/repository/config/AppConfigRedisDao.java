package com.biz.gbck.dao.redis.repository.config;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.config.AppConfigRo;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppConfigRedisDao  extends CrudRedisDao<AppConfigRo,String> {

    public AppConfigRo get() {
        Map<byte[], byte[]> map = hgetAll(getKeyByParams());
        if (MapUtils.isNotEmpty(map)) {
            AppConfigRo ro = new AppConfigRo();
            ro.fromMap(map);
            return ro;
        } else
            return null;
    }

    public void add(AppConfigRo ro) {
        hmset(getKeyByParams(), ro.toMap());
    }

}
