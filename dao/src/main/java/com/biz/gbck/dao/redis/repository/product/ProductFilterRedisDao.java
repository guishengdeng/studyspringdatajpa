package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.meta.ProductFilterRo;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 * 商品过滤条件 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class ProductFilterRedisDao extends CrudRedisDao<ProductFilterRo, Long> implements Serializable {

    private static final long serialVersionUID = 5759794126786959336L;
}
