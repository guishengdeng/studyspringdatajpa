package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.meta.ProductExtendRo;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 * 商品扩展属性 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class ProductExtendRedisDao extends CrudRedisDao<ProductExtendRo, Long> implements Serializable {

    private static final long serialVersionUID = 1580429208041405703L;
}
