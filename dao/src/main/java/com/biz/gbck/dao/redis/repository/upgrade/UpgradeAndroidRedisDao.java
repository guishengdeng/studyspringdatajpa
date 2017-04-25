package com.biz.gbck.dao.redis.repository.upgrade;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeAndroidRo;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class UpgradeAndroidRedisDao extends CrudRedisDao<UpgradeAndroidRo,String> {

    public UpgradeAndroidRo getUpgradeAndroidRo(String id) {
        Map<byte[], byte[]> map = super.hgetAll(id);
        if (map == null || map.isEmpty()) {
            return null;
        }
        UpgradeAndroidRo upgrade = new UpgradeAndroidRo();
        upgrade.fromMap(map);
        return upgrade;
    }
}
