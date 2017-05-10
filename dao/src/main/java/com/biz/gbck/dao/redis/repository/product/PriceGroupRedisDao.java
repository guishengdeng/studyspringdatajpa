package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.price.PriceGroupRO;
import org.springframework.stereotype.Repository;

/**
 * 价格组RedisDao
 *
 * Created by david-liu on 2017/05/03 15:58.
 */
@Repository
public class PriceGroupRedisDao extends CrudRedisDao<PriceGroupRO, Long> {
}
