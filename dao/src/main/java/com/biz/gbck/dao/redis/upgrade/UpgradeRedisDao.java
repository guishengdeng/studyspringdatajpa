package com.biz.gbck.dao.redis.upgrade;

import com.biz.gbck.common.dao.redis.CommonRedisDao;
import com.biz.gbck.common.ro.RedisKeyGenerator.Upgrade;
import com.biz.gbck.common.ro.upgrade.UpgradeRo;
import com.biz.redis.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class UpgradeRedisDao extends CommonRedisDao {


    private final Logger logger = LoggerFactory.getLogger(UpgradeRedisDao.class);

    public void save(UpgradeRo ro) {
        String os = ro.getOs();
        long score = ro.versionToSroce();
        String setKey = Upgrade.getUpgradeSortSetKey(os);
        zadd(setKey, score, RedisUtil.toByteArray(ro.getId()));
        hmset(Upgrade.getUpgradeHashKey(ro.getId()), ro.toMap());
    }

    public UpgradeRo getUpgradeRo(String id) {
        Map<byte[], byte[]> map = hgetAll(Upgrade.getUpgradeHashKey(id));
        logger.debug("use key :{}", Upgrade.getUpgradeHashKey(id));
        if (map == null || map.isEmpty()) {
            return null;
        }
        UpgradeRo upgrade = new UpgradeRo();
        upgrade.fromMap(map);
        return upgrade;
    }

    public void delete(String id) {
        UpgradeRo upgrade = getUpgradeRo(id);
        if (upgrade != null) {
            String setKey = Upgrade.getUpgradeSortSetKey(upgrade.getOs());
            zrem(setKey, id);
            del(Upgrade.getUpgradeHashKey(id));
        }
    }

    public List<UpgradeRo> findAll(String os) {
        Set<byte[]> ids = super.zRange(Upgrade.getUpgradeSortSetKey(os), 0, -1);
        List<UpgradeRo> result = new ArrayList<UpgradeRo>();
        if (ids != null && !ids.isEmpty()) {
            for (byte[] idArr : ids) {
                String id = new String(idArr);
                UpgradeRo upgrade = getUpgradeRo(id);
                if (upgrade != null) {
                    result.add(upgrade);
                }
            }
        }
        return result;
    }


    /**
     * 判断版是否能够升级
     *
     * @param os
     * @param version
     * @return
     * @author gongshutao
     */
    public UpgradeRo needUpgrade(String os, String version, boolean inHourse) {
        String setKey = Upgrade.getUpgradeSortSetKey(os);
        long score = UpgradeRo.versionToSroce(version);
        logger.debug(" current score {}", score);
        UpgradeRo result = null;
        Set<byte[]> ids =
            super.zrevrangeByScore(setKey, String.valueOf(Long.MAX_VALUE), "(" + score);
        if (ids != null && !ids.isEmpty()) {
            logger.debug(" size : {}", ids.size());
            for (byte[] idArr : ids) {
                String id = new String(idArr);
                logger.debug(" id : {}", id);
                UpgradeRo upgrade = getUpgradeRo(id);
                logger.debug("{} is null {}", id, (upgrade == null));
                if (upgrade != null && inHourse == upgrade.getInhourse()) {
                    if (result == null)
                        result = upgrade;
                    logger.debug("result is null {}", (result == null));
                    if (upgrade.getForce()) {
                        result.setForce(true);
                        return result;
                    }
                }
            }
        }
        return result;
    }
}
