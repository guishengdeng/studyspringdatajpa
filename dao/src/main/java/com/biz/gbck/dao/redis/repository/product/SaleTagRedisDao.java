package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.meta.SaleTagRo;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Tuple;

/**
 * 销售标签 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class SaleTagRedisDao extends CrudRedisDao<SaleTagRo, Long> implements Serializable {

    private static final long serialVersionUID = 1010027829501008612L;

    public void addProduct2Saletag(String saletagId, String productId, Double idx) {
        zadd(getSaletagRelevanceProductSortedSetKey(saletagId), idx, productId.getBytes());
    }

    public Map<Long, Double> getProductBySaletagId(String saletagId) {
        Set<Tuple> productIdScore = zrangeWithScores(getSaletagRelevanceProductSortedSetKey(saletagId), 0, -1);
        Map<Long, Double> result = Maps.newHashMap();
        for (Tuple index : productIdScore) {
            result.put(Long.valueOf(index.getElement()), index.getScore());
        }
        return result;
    }

    public void removeProduct2saletag(String saletagId, String productId) {
        zrem(getSaletagRelevanceProductSortedSetKey(saletagId), productId);
    }

    /**
     * 获取销售标签 SortedSet Key(保存销售标签与商品映射)
     *
     * @return key
     */
    private String getSaletagRelevanceProductSortedSetKey(String saletagId) {
        return getKeyByParams("relevance", saletagId);
    }

}
