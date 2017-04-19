package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.OrderStockLockRo;
import com.biz.redis.util.RedisUtil;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Tuple;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author lei
 * @date 2016年12月5日
 */
@Repository
public class OrderStockLockRedisDao extends CrudRedisDao<OrderStockLockRo, String> {

    public static final String STOCK_ORDER_SORTED_SET_KEY = "stock:order:list";

    public void recordOrderLockStock(String orderCode, Map<String, Integer> keyToQuantity, int aliveMinutes) {
        if (StringUtils.isBlank(orderCode) || MapUtils.isEmpty(keyToQuantity)) {
            return;
        }
        for (Map.Entry<String, Integer> entry : keyToQuantity.entrySet()) {
            super.zadd(getStockOrderSortedSetKey(orderCode), ValueUtils.getValue(entry.getValue()), RedisUtil.toByteArray(entry.getKey()));
        }
        long expireTimeMillis = System.currentTimeMillis() + aliveMinutes * 60 * 1000;
        super.zadd(STOCK_ORDER_SORTED_SET_KEY, new Date(expireTimeMillis), orderCode);
    }

    public boolean existOrderStockLock(String orderCode) {
        return ValueUtils.getValue(super.zscoreToInt(STOCK_ORDER_SORTED_SET_KEY, RedisUtil.toByteArray(orderCode))) > 0;
    }

    public Map<String, Integer> getOrderStockLockKeyWithQuantities(String orderCode) {
        Map<String, Integer> keyWithQuantities = newHashMap();
        Set<Tuple> set = super.zrevrangeWithScore(getStockOrderSortedSetKey(orderCode), 0, -1);
        for (Tuple tuple : set) {
            keyWithQuantities.put(tuple.getElement(), ValueUtils.getValue(tuple.getScore()).intValue());
        }
        return keyWithQuantities;
    }

    /**
     * 更新各级锁定库存
     */
    public void updateQuantities(Map<String, Integer> keyToStockLockQuantity) {
        super.pipeHincrBy(keyToStockLockQuantity, Constant.RO_QUANTITY_FIELD);
    }


    public void removeExpiredOrderStockLockKeys(String orderCode, Set<String> keys) {
        if (StringUtils.isBlank(orderCode) || CollectionUtils.isEmpty(keys)) {
            return;
        }
        super.pipeZrem(getStockOrderSortedSetKey(orderCode), keys);
    }

    public Set<String> getExpiredLockStockOrderCodes(Timestamp currentTimestamp) {
        return super.zrangeByScore(STOCK_ORDER_SORTED_SET_KEY, 0, currentTimestamp.getTime());
    }

    public void removedExpiredLockStockOrder(String expiredLockStockOrderCode) {
        if (StringUtils.isBlank(expiredLockStockOrderCode)) {
            return;
        }
        super.zrem(STOCK_ORDER_SORTED_SET_KEY, expiredLockStockOrderCode);
        super.del(getStockOrderSortedSetKey(expiredLockStockOrderCode));
    }

    private String getStockOrderSortedSetKey(String orderCode) {
        return super.getHashKey(orderCode);
    }
}
