package com.biz.gbck.dao.redis.repository.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.CompanyStockRo;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author lei
 * @date 2016年12月5日
 */
@Repository
public class CompanyStockRedisDao extends CrudRedisDao<CompanyStockRo, String> {

    public static String getId(Long companyId, Long productId) {
        return String.format("%s%s%s", companyId, Constant.SEPARATOR, productId);
    }

    public static String getHashKey(Long companyId, Long productId) {
        return String.format("%s%s%s%s%s", "stock:company", Constant.SEPARATOR, companyId, Constant.SEPARATOR,
                productId);
    }

    public void save(CompanyStockRo ro) {
        ro.setUpdateTimestamp(DateUtil.now());
        super.save(ro);
    }

    public void save(List<CompanyStockRo> ros) {
        if (CollectionUtils.isEmpty(ros)) {
            return;
        }
        super.save(ros);
    }

    public void delete(CompanyStockRo ro) {
        super.delete(ro);
    }

    /**
     * 根公司Id&商品id查询
     */
    public CompanyStockRo getByCompanyIdAndProductId(Long companyId, Long productId) {
        if (companyId == null || productId == null) {
            return null;
        }
        return findByKey(getHashKey(getId(companyId, productId)));
    }

    /**
     * 根据公司Id&商品id查询数量
     */
    public Integer getByQuantityCompanyIdAndProductId(Long companyId, Long productId) {
        if (companyId == null || productId == null) {
            return 0;
        }
        byte[] bytes = super.hget(getHashKey(getId(companyId, productId)), Constant.RO_QUANTITY_FIELD);
        if (ArrayUtils.isNotEmpty(bytes) && NumberUtils.isNumber(new String(bytes))) {
            return RedisUtil.byteArrayToInt(bytes);
        } else
            return 0;
    }


    /**
     * 根据公司Id&商品ids批量查询
     */
    public List<CompanyStockRo> findByCompanyIdAndProductIds(Long companyId, Set<Long> productIds) {
        if (companyId == null || CollectionUtils.isEmpty(productIds)) {
            return newArrayList();
        }
        List<String> keys = newArrayList();
        for (Long productId : productIds) {
            keys.add(getHashKey(getId(companyId, productId)));
        }
        return findByKeys(keys);
    }

    /**
     * 更新库存
     */
    public void updateQuantity(Long companyId, Long productId, int quantity) {
        Long stock = hincrBy(getHashKey(getId(companyId, productId)), Constant.RO_QUANTITY_FIELD.getBytes(), quantity);
        if (stock < 0) {
            hset(getHashKey(getId(companyId, productId)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
    }

    /**
     * 批量更新库存
     */
    public void updateQuantities(Map<String, Integer> keyToStockQuantity) {
        if (MapUtils.isNotEmpty(keyToStockQuantity)) {
            super.pipeHincrBy(keyToStockQuantity, Constant.RO_QUANTITY_FIELD);
        }
    }
}
