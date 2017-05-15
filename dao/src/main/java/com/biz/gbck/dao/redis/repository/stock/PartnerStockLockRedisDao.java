package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.PartnerStockLockRo;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author lei
 * @date 2017年05月10日
 */
@Repository
public class PartnerStockLockRedisDao extends CrudRedisDao<PartnerStockLockRo, String> {


    public static String getId(Long partnerId, Long productId) {
        return String.format("%s%s%s", partnerId, Constant.SEPARATOR, productId);
    }

    public static String getHashKey(Long partnerId, Long productId) {
        return String.format("%s%s%s%s%s", "stock:partner:lock", Constant.SEPARATOR, partnerId, Constant.SEPARATOR,
                productId);
    }

    public void delete(PartnerStockLockRo ro) {
        super.delete(ro);
    }

    /**
     * 根据合伙人id&商品id查询
     */
    public PartnerStockLockRo getByPartnerIdAndProductId(Long partnerId, Long productId) {
        if (partnerId == null  || productId == null) {
            return null;
        }
        return super.findOne(getId(partnerId, productId));
    }

    /**
     * 根据合伙人id&商品id查询锁定数量
     */
    public Integer getQuantityByPartnerIdAndProductId(Long partnerId, Long productId) {
        if (partnerId == null || productId == null) {
            return 0;
        }
        byte[] bytes = super.hget(getHashKey(getId(partnerId, productId)), Constant.RO_QUANTITY_FIELD);
        if (ArrayUtils.isNotEmpty(bytes) && NumberUtils.isNumber(new String(bytes))) {
            return RedisUtil.byteArrayToInt(bytes);
        } else
            return 0;
    }

    /**
     * 根据合伙人id&商品id集合批量查询
     */
    public List<PartnerStockLockRo> findByParnterIdAndProductId(Long partnerId, Set<Long> productIds) {
        if (partnerId == null  || CollectionUtils.isEmpty(productIds)) {
            return newArrayList();
        }
        List<String> keys = newArrayList();
        for (Long productId : productIds) {
            keys.add(getHashKey(getId(partnerId, productId)));
        }
        return findByKeys(keys);
    }

    public void updateQuantity(Long partnerId, Long productId, int quantity) {
        Long incredQuantity = hincrBy(getHashKey(getId(partnerId, productId)), Constant.RO_QUANTITY_FIELD.getBytes
                (), quantity);
        if (incredQuantity < 0) {
            hset(getHashKey(getId(partnerId, productId)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
    }

    public void updateQuantities(Map<String, Integer> keyToDepotStockQuantity) {
        super.pipeHincrBy(keyToDepotStockQuantity, Constant.RO_QUANTITY_FIELD);
    }
}
