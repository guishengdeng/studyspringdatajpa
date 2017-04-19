package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.StockLockRo;
import com.biz.redis.util.RedisUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Repository;

/**
 * @author lei
 * @date 2016年12月5日
 */
@Repository
public class StockLockRedisDao extends CrudRedisDao<StockLockRo, String> {

    public static String getId(String productCode) {
        return productCode;
    }

    public static String getHashKey(String productCode) {
        return String.format("%s%s%s", "stock:product:lock", Constant.SEPARATOR, productCode);
    }

    public void save(StockLockRo ro) {
        super.save(ro);
    }

    public void delete(StockLockRo ro) {
        super.delete(ro);
    }

    public void save(List<StockLockRo> ros) {
        if (ros != null && !ros.isEmpty()) {
            for (StockLockRo ro : ros) {
                this.save(ro);
            }
        }
    }

    public void delete(List<StockLockRo> ros) {
        if (ros != null && !ros.isEmpty()) {
            for (StockLockRo ro : ros) {
                this.delete(ro);
            }
        }
    }

    /**
     * 根据商品编号查询
     */
    public StockLockRo findByProductCode(String productCode) {
        if (StringUtils.isBlank(productCode)) {
            return null;
        }
        return findOne(getHashKey(getId(productCode)));
    }

    /**
     * 根据商品编号查询锁定数量
     */
    public Integer findQuantityByProductCode(String productCode) {
        if (StringUtils.isBlank(productCode)) {
            return 0;
        }
        byte[] bytes = super.hget(getHashKey(getId(productCode)), Constant.RO_QUANTITY_FIELD);
        if (ArrayUtils.isNotEmpty(bytes) && NumberUtils.isNumber(new String(bytes))) {
            return RedisUtil.byteArrayToInt(bytes);
        } else
            return 0;
    }

    public List<StockLockRo> findByProductCodes(Collection<String> productCodes) {
        List<String> keys = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productCodes)) {
            for (String productCode : productCodes) {
                keys.add(getHashKey(getId(productCode)));
            }
        }
        return findByKeys(keys);
    }

    public void updateQuantity(String productCode, int quantity) {
        Long stock = hincrBy(getHashKey(getId(productCode)), "quantity".getBytes(), quantity);
        if (stock < 0) {
            hset(getHashKey(getId(productCode)), "quantity", RedisUtil.toByteArray(0));
        }
    }

    public void updateQuantities(Map<String, Integer> keyToStockQuantity) {
        super.pipeHincrBy(keyToStockQuantity, Constant.RO_QUANTITY_FIELD);
    }
}
