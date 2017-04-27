package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.meta.ProductFilterItemRo;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 * 商品过滤条件项 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class ProductFilterItemRedisDao extends CrudRedisDao<ProductFilterItemRo, Long> implements Serializable {

    private static final long serialVersionUID = -4168436948392222158L;


}
