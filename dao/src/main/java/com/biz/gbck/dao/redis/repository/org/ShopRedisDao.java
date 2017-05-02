package com.biz.gbck.dao.redis.repository.org;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by defei on 3/11/16.
 */
@Component
public class ShopRedisDao extends CrudRedisDao<ShopRo,String> {

    /*public void save(ShopRo shopRo) {

        hmset(RedisKeyGenerator.Shop.getShopHashKey(), shopRo.toMap());
        if (shopRo.getChannel() != null && shopRo.getChannelUserId() != null) {
            String channelAndChannelUserIdToShopIdHashKey = RedisKeyGenerator.Shop
                .getChannelAndChannelUserIdToShopIdHashKey(shopRo.getChannel(),
                    shopRo.getChannelUserId());
            set(channelAndChannelUserIdToShopIdHashKey, RedisUtil.toByteArray(shopRo.getId()));
        }
        zadd(RedisKeyGenerator.Shop.getAllShopSortSetKey(), shopRo.getCreateTime().getTime(),
            RedisUtil.toByteArray(shopRo.getId()));
    }

    public ShopRo findOne(Long shopId) {

        if (shopId == null) {
            return null;
        }
        String shopHashKey = RedisKeyGenerator.Shop.getShopHashKey(shopId);
        Map<byte[], byte[]> map = hgetAll(shopHashKey);
        if (MapUtils.isNotEmpty(map)) {
            ShopRo shopRo = new ShopRo();
            shopRo.fromMap(map);
            return shopRo;
        } else
            return null;
    }

    public void delete(ShopRo shopRo) {

        del(RedisKeyGenerator.Shop.getShopHashKey(shopRo.getId()));
        zrem(RedisKeyGenerator.Shop.getAllShopSortSetKey(), RedisUtil.toByteArray(shopRo.getId()));
    }

    public ShopRo findByChannelAndChannelUserId(Integer channel, Long channelUserId) {

        if (channel == null || channelUserId == null) {
            return null;
        } else {
            String channelAndChannelUserIdToShopIdHashKey = RedisKeyGenerator.Shop
                .getChannelAndChannelUserIdToShopIdHashKey(channel, channelUserId);
            byte[] bytes = get(channelAndChannelUserIdToShopIdHashKey);
            return bytes == null ? null : findOne(RedisUtil.byteArrayToLong(bytes));
        }
    }

    public void updateBlackList(String[] list) {
        List<String> oldBlackList = getBlackList();
        List<String> newBlackList = new ArrayList<>();

        if (list != null) {
            for (String tmpShopId : list) {
                String shopId = StringUtils.trimToEmpty(tmpShopId);
//                if (fixedMobile.length() == 11) {
                    newBlackList.add(shopId);
//                }
            }
        }
        oldBlackList.removeAll(newBlackList);
        for (String n : newBlackList) {
            zadd(RedisKeyGenerator.Shop.getBlackListShopKey(), System.currentTimeMillis(),
                    RedisUtil.toByteArray(n));
        }

    }

    public void deleteBlackList(List<String> shopIds) {
        for (String shopId : shopIds) {
            zrem(RedisKeyGenerator.Shop.getBlackListShopKey(), RedisUtil.toByteArray(shopId));
        }
    }

    public List<String> getBlackList () {

        String[] blackList = RedisUtil.bytesSetToStringArray(
                zRange(RedisKeyGenerator.Shop.getBlackListShopKey(), 0, -1));
        if (blackList == null) {
            return newArrayList();
        } else {
            return newArrayList(blackList);
        }
    }

    public boolean getShopCanOrder(String shopId) {
        return zscore(RedisKeyGenerator.Shop.getBlackListShopKey(),
                RedisUtil.toByteArray(shopId)) == null;
    }
*/
}

