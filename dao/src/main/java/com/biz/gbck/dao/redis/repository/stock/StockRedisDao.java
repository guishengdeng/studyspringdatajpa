package com.biz.gbck.dao.redis.repository.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.StockRo;
import com.biz.redis.util.RedisUtil;
import java.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Repository;

/**
 * @author lei
 * @date 2016年12月5日
 */
@Repository
public class StockRedisDao extends CrudRedisDao<StockRo, String> {

    public void save(StockRo ro) {
        super.save(ro);
    }

    public void delete(StockRo ro) {
        super.delete(ro);
    }

    public void save(List<StockRo> ros) {
        if (CollectionUtils.isEmpty(ros)) {
            return;
        }
        super.save(ros);
    }

    public void delete(List<StockRo> ros) {
        if (CollectionUtils.isEmpty(ros)) {
            return;
        }
        for (StockRo ro : ros) {
            this.delete(ro);
        }
    }

    /**
     * 保存B类商品编码
     */
    public void saveTypeBProductCodes(Set<String> productCodes) {
        if (CollectionUtils.isNotEmpty(productCodes)) {
            super.pipeSadd(Constant.TYPE_B_PRODUCT_CODES_SET_KEY, productCodes.toArray(new String[productCodes.size()]));
        }
    }

    /**
     * 获取全部B类商品编码
     */
    public List<String> getAllTypeBProductCodes() {
        Set<byte[]> bytes = super.smembers(Constant.TYPE_B_PRODUCT_CODES_SET_KEY);
        return RedisUtil.bytesSetToStringList(bytes);
    }


    /**
     * 根据商品编号查询数量
     */
    public Integer getQuantityByProductCode(String productCode) {
        if (StringUtils.isBlank(productCode)) {
            return 0;
        }
        byte[] bytes = super.hget(getHashKey(getId(productCode)), Constant.RO_QUANTITY_FIELD);
        if (ArrayUtils.isNotEmpty(bytes) && NumberUtils.isNumber(new String(bytes))) {
            return RedisUtil.byteArrayToInt(bytes);
        } else
            return 0;
    }

    public List<StockRo> findByProductCodes(Collection<String> productCodes) {
        List<String> keys = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productCodes)) {
            for (String productCode : productCodes) {
                keys.add(getHashKey(getId(productCode)));
            }
        }
        return findByKeys(keys);
    }

    public void updateQuantity(String productCode, int quantity) {
        Long stock = hincrBy(getHashKey(getId(productCode)), Constant.RO_QUANTITY_FIELD.getBytes(), quantity);
        if (stock < 0) {
            hset(getHashKey(getId(productCode)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
        hset(getHashKey(getId(productCode)), Constant.RO_UPDATE_TIMESTAMP_FIELD, RedisUtil.toByteArray(DateUtil.now()));
    }

    public void updateQuantities(Map<String, Integer> keyToStockQuantity) {
        if (MapUtils.isNotEmpty(keyToStockQuantity)) {
            super.pipeHincrBy(keyToStockQuantity, Constant.RO_QUANTITY_FIELD);
        }
    }

    private String getId(String productCode) {
        return productCode;
    }

    public static String getHashKey(String productCode) {
        return String.format("%s%s%s", "stock:product", Constant.SEPARATOR, productCode);
    }
}
