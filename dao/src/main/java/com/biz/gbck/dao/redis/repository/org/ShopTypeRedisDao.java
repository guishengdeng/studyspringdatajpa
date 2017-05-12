package com.biz.gbck.dao.redis.repository.org;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.MapUtils;
import org.codelogger.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 商户类型 redis dao
 */
@Component
public class ShopTypeRedisDao extends CrudRedisDao<ShopTypeRo,String> {

    public List<ShopTypeRo> findAll() {

        Set<byte[]> idBytesSet = zRange(getKeyByParams(), 0, -1);
        List<ShopTypeRo> shopTypeRos = newArrayList();
        if (CollectionUtils.isNotEmpty(idBytesSet)) {
            for (byte[] idBytes : idBytesSet) {
                shopTypeRos.add(findOne(RedisUtil.byteArrayToLong(idBytes)));
            }
        }
        return shopTypeRos;
    }

    public List<ShopTypeRo> findAll(ShopTypeStatus shopTypeStatus) {

        Set<byte[]> idBytesSet =
            zRange(getKeyByParams(shopTypeStatus), 0, -1);
        List<ShopTypeRo> shopTypeRos = newArrayList();
        if (CollectionUtils.isNotEmpty(idBytesSet)) {
            for (byte[] idBytes : idBytesSet) {
                shopTypeRos.add(findOne(RedisUtil.byteArrayToLong(idBytes)));
            }
        }
        return shopTypeRos;
    }

    public void save(ShopTypeRo shopTypeRo) {

        hmset(getHashKey(shopTypeRo.getId()), shopTypeRo.toMap());
        long currentTimeMillis = System.currentTimeMillis();
        zadd(getKeyByParams(), currentTimeMillis,
            RedisUtil.toByteArray(shopTypeRo.getId()));
        if (Objects.equals(shopTypeRo.getStatus(), ShopTypeStatus.NORMAL.getValue())) {
            zadd(getKeyByParams(ShopTypeStatus.NORMAL), currentTimeMillis,
                RedisUtil.toByteArray(shopTypeRo.getId()));
        } else {
            zrem(getKeyByParams(ShopTypeStatus.NORMAL),
                RedisUtil.toByteArray(shopTypeRo.getId()));
        }
    }

    public void update(ShopTypeRo shopTypeRo) {
        hmset(getHashKey(shopTypeRo.getId()), shopTypeRo.toMap());
    }

    public ShopTypeRo findOne(Long shopTypeId) {
        String userInfoKey = getHashKey(shopTypeId);
        Map<byte[], byte[]> map = hgetAll(userInfoKey);
        if (MapUtils.isNotEmpty(map)) {
            ShopTypeRo userInfo = new ShopTypeRo();
            userInfo.fromMap(map);
            return userInfo;
        } else {
            return null;
        }
    }

    public void disableShopType(Long shopTypeId) {
        zrem(getKeyByParams(ShopTypeStatus.NORMAL),
            RedisUtil.toByteArray(shopTypeId));
    }

    public void delete(Long shopTypeId) {

        del(getHashKey(shopTypeId));
        zrem(getKeyByParams(), RedisUtil.toByteArray(shopTypeId));
        zrem(getKeyByParams(ShopTypeStatus.NORMAL),
            RedisUtil.toByteArray(shopTypeId));
    }
}
