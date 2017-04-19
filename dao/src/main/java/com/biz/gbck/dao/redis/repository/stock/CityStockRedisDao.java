package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.CityStockRo;
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
public class CityStockRedisDao extends CrudRedisDao<CityStockRo, String> {

    public static String getId(Integer cityId, String productCode) {
        return String.format("%s%s%s", cityId, Constant.SEPARATOR, productCode);
    }

    public static String getHashKey(Integer cityId, String productCode) {
        return String.format("%s%s%s%s%s", "stock:city", Constant.SEPARATOR, cityId, Constant.SEPARATOR, productCode);
    }

    public void save(CityStockRo ro) {
        super.save(ro);
    }

    public void save(List<CityStockRo> ros) {
        if (CollectionUtils.isEmpty(ros)) {
            return;
        }
        super.save(ros);
    }

    public void delete(CityStockRo ro) {
        super.delete(ro);
    }

    public void delete(List<CityStockRo> ros) {
        if (ros != null && !ros.isEmpty()) {
            for (CityStockRo ro : ros) {
                this.delete(ro);
            }
        }
    }

    /**
     * 根据城市Id&商品编号查询
     */
    public CityStockRo findByCityIdAndProductCode(Integer cityId, String productCode) {
        if (cityId == null || StringUtils.isBlank(productCode)) {
            return null;
        }
        return super.findByKey(getHashKey(getId(cityId, productCode)));
    }

    /**
     * 根据城市Id&商品编码查询
     */
    public Integer getQuantityByCityIdAndProductCode(Integer cityId, String productCode) {
        if (cityId == null || StringUtils.isBlank(productCode)) {
            return 0;
        }
        byte[] bytes = super.hget(getHashKey(getId(cityId, productCode)), Constant.RO_QUANTITY_FIELD);
        if (ArrayUtils.isNotEmpty(bytes) && NumberUtils.isNumber(new String(bytes))) {
            return RedisUtil.byteArrayToInt(bytes);
        } else
            return 0;
    }

    /**
     * 根据城市Id&商品编码批量查询
     */
    public List<CityStockRo> getByCityIdAndProductCodes(Integer cityId, Set<String> productCodes) {
        if (cityId == null || CollectionUtils.isEmpty(productCodes)) {
            return newArrayList();
        }
        List<String> keys = newArrayList();
        for (String productCode : productCodes) {
            keys.add(getHashKey(getId(cityId, productCode)));
        }
        return findByKeys(keys);
    }

    public void updateQuantity(Integer cityId, String productCode, int quantity) {
        Long incredQuantity = hincrBy(getHashKey(getId(cityId, productCode)), Constant.RO_QUANTITY_FIELD.getBytes(),
                quantity);
        if (incredQuantity < 0) {
            hset(getHashKey(getId(cityId, productCode)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
    }

    public void updateQuantities(Map<String, Integer> keyToCityStockQuantity) {
        super.pipeHincrBy(keyToCityStockQuantity, Constant.RO_QUANTITY_FIELD);
    }
}
