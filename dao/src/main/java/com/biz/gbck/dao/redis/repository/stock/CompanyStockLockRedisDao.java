package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.CompanyStockLockRo;
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
public class CompanyStockLockRedisDao extends CrudRedisDao<CompanyStockLockRo, String> {


    public static String getId(Long companyId, Long productId) {
        return String.format("%s%s%s", companyId, Constant.SEPARATOR, productId);
    }

    public static String getHashKey(Long companyId, Long productId) {
        return String.format("%s%s%s%s%s", "stock:company:lock", Constant.SEPARATOR, companyId, Constant.SEPARATOR,
                productId);
    }

    public void delete(CompanyStockLockRo ro) {
        super.delete(ro);
    }

    /**
     * 根据合伙人id&商品id查询
     */
    public CompanyStockLockRo getByPartnerIdAndProductId(Long companyId, Long productId) {
        if (companyId == null  || productId == null) {
            return null;
        }
        return super.findOne(getId(companyId, productId));
    }

    /**
     * 根据合伙人id&商品id查询锁定数量
     */
    public Integer getQuantityByCompanyIdAndProductId(Long companyId, Long productId) {
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
     * 根据合伙人id&商品id集合批量查询
     */
    public List<CompanyStockLockRo> findByCompanyIdAndProductId(Long companyId, Set<Long> productIds) {
        if (companyId == null  || CollectionUtils.isEmpty(productIds)) {
            return newArrayList();
        }
        List<String> keys = newArrayList();
        for (Long productId : productIds) {
            keys.add(getHashKey(getId(companyId, productId)));
        }
        return findByKeys(keys);
    }

    public void updateQuantity(Long companyId, Long productId, int quantity) {
        Long incredQuantity = hincrBy(getHashKey(getId(companyId, productId)), Constant.RO_QUANTITY_FIELD.getBytes
                (), quantity);
        if (incredQuantity < 0) {
            hset(getHashKey(getId(companyId, productId)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
    }

    public void updateQuantities(Map<String, Integer> keyToDepotStockQuantity) {
        super.pipeHincrBy(keyToDepotStockQuantity, Constant.RO_QUANTITY_FIELD);
    }
}
