package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.ExtendPropertyRo;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 * 分类扩展属性值 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class ExtendPropertyRedisDao extends CrudRedisDao<ExtendPropertyRo, Long> implements Serializable {

    private static final long serialVersionUID = -7489202969800177805L;
}
