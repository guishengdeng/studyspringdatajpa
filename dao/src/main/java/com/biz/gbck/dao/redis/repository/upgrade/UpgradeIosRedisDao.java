package com.biz.gbck.dao.redis.repository.upgrade;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeIosRo;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class UpgradeIosRedisDao extends CrudRedisDao<UpgradeIosRo,String> {

    public UpgradeIosRo getUpgradeIosRo(String id) {
        Map<byte[], byte[]> map = super.hgetAll(id);
        if (map == null || map.isEmpty()) {
            return null;
        }
        UpgradeIosRo upgrade = new UpgradeIosRo();
        upgrade.fromMap(map);
        return upgrade;
    }
}
