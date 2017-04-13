package com.biz.gbck.dao;

import com.biz.gbck.dao.redis.repository.TestRepository;
import com.biz.gbck.dao.redis.ro.TestRo;
import com.biz.manage.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author david-liu
 * @date 2017年04月13日
 * @reviewer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedisDao {

    @Autowired
    private TestRepository testRepository;

    @Test
    public void testRedisDao() {
        TestRo ro = new TestRo();
        ro.setId("foo");
        ro.setTestSortedSet("bar");
        testRepository.save(ro);
    }

}
