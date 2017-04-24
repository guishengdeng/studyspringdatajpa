package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.DepotStockLockRo;
import com.biz.redis.util.RedisUtil;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author lei
 * @date 2016年12月5日
 */
@Repository
public class DepotStockLockRedisDao extends CrudRedisDao<DepotStockLockRo, String> {


    public static String getId(String depotCode, String productCode) {
        return String.format("%s%s%s", depotCode, Constant.SEPARATOR, productCode);
    }

    public static String getHashKey(String depotCode, String productCode) {
        return String.format("%s%s%s%s%s", "stock:depot:lock", Constant.SEPARATOR, depotCode, Constant.SEPARATOR, productCode);
    }

    public void delete(DepotStockLockRo ro) {
        super.delete(ro);
    }

    /**
     * 根据门店编码&商品编号查询
     */
    public DepotStockLockRo findByProvinceIdAndProductCode(String depotCode, String productCode) {
        if (StringUtils.isBlank(depotCode) || StringUtils.isBlank(productCode)) {
            return null;
        }
        return super.findOne(getId(depotCode, productCode));
    }

    /**
     * 根据门店编码&商品编号查询锁定数量
     */
    public Integer getQuantityByDepotCodeAndProductCode(String depotCode, String productCode) {
        if (StringUtils.isBlank(depotCode) || StringUtils.isBlank(productCode)) {
            return 0;
        }
        byte[] bytes = super.hget(getHashKey(getId(depotCode, productCode)), Constant.RO_QUANTITY_FIELD);
        if (ArrayUtils.isNotEmpty(bytes) && NumberUtils.isNumber(new String(bytes))) {
            return RedisUtil.byteArrayToInt(bytes);
        } else
            return 0;
    }

    /**
     * 根据门店编码&商品编码批量查询
     */
    public List<DepotStockLockRo> getByDepotCodeAndProductCodes(String depotCode, Set<String> productCodes) {
        if (StringUtils.isBlank(depotCode) || CollectionUtils.isEmpty(productCodes)) {
            return newArrayList();
        }
        List<String> keys = newArrayList();
        for (String productCode : productCodes) {
            keys.add(getHashKey(getId(depotCode, productCode)));
        }
        return findByKeys(keys);
    }

    public void updateQuantity(String depotCode, String productCode, int quantity) {
        Long incredQuantity = hincrBy(getHashKey(getId(depotCode, productCode)), Constant.RO_QUANTITY_FIELD.getBytes
                (), quantity);
        if (incredQuantity < 0) {
            hset(getHashKey(getId(depotCode, productCode)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
    }

    public void updateQuantities(Map<String, Integer> keyToDepotStockQuantity) {
        super.pipeHincrBy(keyToDepotStockQuantity, Constant.RO_QUANTITY_FIELD);
    }
}
