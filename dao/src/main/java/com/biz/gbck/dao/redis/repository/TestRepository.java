package com.biz.gbck.dao.redis.repository;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.TestRo;
import org.springframework.stereotype.Repository;

/**
 * @author david-liu
 * @date 2017年04月13日
 * @reviewer
 */
@Repository
public class TestRepository extends CrudRedisDao<TestRo, String> {
}
