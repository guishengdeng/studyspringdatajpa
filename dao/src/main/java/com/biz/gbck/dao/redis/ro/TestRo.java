package com.biz.gbck.dao.redis.ro;

import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * @author david-liu
 * @date 2017年04月13日
 * @reviewer
 */
@Ro(key = "test")
@RoSortedSet(key = "list", score = "createTimestamp")
public class TestRo extends BaseRedisObject<String> {

    @FieldSortedSet(key = "testSortedSet", score = "createTimestamp")
    private String testSortedSet;

    public String getTestSortedSet() {
        return testSortedSet;
    }

    public void setTestSortedSet(String testSortedSet) {
        this.testSortedSet = testSortedSet;
    }
}
