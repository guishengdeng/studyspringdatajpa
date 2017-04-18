package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.ProvinceStockLockRo;
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
public class ProvinceStockLockRedisDao extends CrudRedisDao<ProvinceStockLockRo, String> {

    /**
     * 根据省份Id&商品编号查询
     */
    public ProvinceStockLockRo findByProvinceIdAndProductCode(Integer provinceId, String productCode) {
        if (provinceId == null || StringUtils.isBlank(productCode)) {
            return null;
        }
        return super.findByKey(getHashKey(getId(provinceId, productCode)));
    }

    public void delete(ProvinceStockLockRo ro) {
        super.delete(ro);
        del(getProvinceIdSetKey(ro.getProvinceId()));
    }

    public void delete(List<ProvinceStockLockRo> ros) {
        if (ros != null && !ros.isEmpty()) {
            for (ProvinceStockLockRo ro : ros) {
                this.delete(ro);
            }
        }
    }

    /**
     * 根据省Id&商品查询数量
     */
    public Integer getQuantityByProvinceIdAndProductCode(Integer provinceId, String productCode) {
        if (provinceId == null || StringUtils.isBlank(productCode)) {
            return 0;
        }
        byte[] bytes = super.hget(getHashKey(getId(provinceId, productCode)), Constant.RO_QUANTITY_FIELD);
        if (ArrayUtils.isNotEmpty(bytes) && NumberUtils.isNumber(new String(bytes))) {
            return RedisUtil.byteArrayToInt(bytes);
        } else
            return 0;
    }

    /**
     * 根据省Id&商品编码批量查询
     */
    public List<ProvinceStockLockRo> getByProvinceIdAndProductCodes(Integer provinceId, Set<String> productCodes) {
        if (provinceId == null || CollectionUtils.isEmpty(productCodes)) {
            return newArrayList();
        }
        List<String> keys = newArrayList();
        for (String productCode : productCodes) {
            keys.add(getHashKey(getId(provinceId, productCode)));
        }
        return findByKeys(keys);
    }

    public void updateQuantity(Integer provinceId, String productCode, int quantity) {
        Long incredQuantity = hincrBy(getHashKey(getId(provinceId, productCode)), Constant.RO_QUANTITY_FIELD
                .getBytes(), quantity);
        if (incredQuantity < 0) {
            hset(getHashKey(getId(provinceId, productCode)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
    }

    public void updateQuantities(Map<String, Integer> keyToProvinceStockQuantity) {
        super.pipeHincrBy(keyToProvinceStockQuantity, Constant.RO_QUANTITY_FIELD);
    }

    private String getProvinceIdSetKey(Integer provinceId) {
        return getKeyByParams("provinceId", provinceId);
    }

    public static String getId(Integer provinceId, String productCode) {
        return String.format("%s%s%s", provinceId, Constant.SEPARATOR, productCode);
    }

    public static String getHashKey(Integer provinceId, String productCode) {
        return String.format("%s%s%s%s%s", "stock:province:lock", Constant.SEPARATOR, provinceId, Constant
                .SEPARATOR, productCode);
    }
}
