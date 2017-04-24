package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.BrandRo;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 * 品牌 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class BrandRedisDao extends CrudRedisDao<BrandRo, Long> implements Serializable {

    private static final long serialVersionUID = -1784004848159840673L;
}
