package com.biz.gbck.dao.redis.repository.upgrade;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.redis.util.RedisUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;


@Repository
public class UpgradeRedisDao extends CrudRedisDao<UpgradeRo,String> {

    /**
     *判断是否升级
     */
    public UpgradeRo needUpgrade(String os, String version, boolean inHourse) {
        String setKey = getKeyByParams(os);
        long score = UpgradeRo.versionToSroce(version);
        UpgradeRo result = null;
        Set<byte[]> ids =
                super.zrevrangeByScore(setKey, String.valueOf(Long.MAX_VALUE), "(" + score);
        if (ids != null && !ids.isEmpty()) {
            for (byte[] idArr : ids) {
                String id = new String(idArr);
                UpgradeRo upgrade = getUpgradeRo(id);
                if (upgrade != null && inHourse == upgrade.getInhourse()) {
                    if (result == null)
                        result = upgrade;
                    if (upgrade.getForce()) {
                        result.setForce(true);
                        return result;
                    }
                }
            }
        }
        return result;
    }

    /**
     *根据hash key 或者封装对应ro
     */
    public UpgradeRo getUpgradeRo(String id) {
        Map<byte[], byte[]> map = super.hgetAll(getHashKey(id));
        logger.debug("use key :{}",id);
        if (map == null || map.isEmpty()) {
            return null;
        }
        UpgradeRo upgrade = new UpgradeRo();
        upgrade.fromMap(map);
        return upgrade;
    }

    /**
     *查询对应系统所有ro
     */
    public List<UpgradeRo> findAll(String os) {
        String key=getKeyByParams(os);
        Set<byte[]> ids = super.zRange(key, 0, -1);
        List<UpgradeRo> result =newArrayList();
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
     *保存ro
     */
    public void save(UpgradeRo ro) {
        String os = ro.getOs();
        long score = ro.versionToSroce();
        String setKey = getKeyByParams(os);
        zadd(setKey, score, RedisUtil.toByteArray(ro.getId()));
        hmset(getHashKey(ro.getId()), ro.toMap());
    }

    public void delete(String id) {
        UpgradeRo upgrade = getUpgradeRo(id);
        if (upgrade != null) {
            String setKey =getKeyByParams(upgrade.getOs());
            zrem(setKey, id);
            del(getHashKey(id));
        }
    }

}
