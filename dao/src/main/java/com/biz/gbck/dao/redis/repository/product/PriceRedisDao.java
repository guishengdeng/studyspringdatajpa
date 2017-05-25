package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import org.springframework.stereotype.Repository;

/**
 * 价格RedisDao
 *
 * Created by david-liu on 2017/04/21 13:49.
 */
@Repository
public class PriceRedisDao extends CrudRedisDao<PriceRO, String> {
}
