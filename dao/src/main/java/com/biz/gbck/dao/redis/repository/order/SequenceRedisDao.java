package com.biz.gbck.dao.redis.repository.order;

import com.biz.core.asserts.SystemAsserts;
import com.biz.core.util.SyncExecutionUnit;
import com.biz.core.util.SyncUtil;
import com.biz.gbck.dao.redis.SimpleDataRedisDao;
import com.biz.gbck.enums.order.SequenceType;
import org.springframework.stereotype.Repository;

/**
 * 序列号生成dao
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */

@Repository
public class SequenceRedisDao extends SimpleDataRedisDao {

    private final int EXPIRED_TIME = 25 * 60 * 60;//25小时,单位: 秒

    private final SyncUtil syncUtil = new SyncUtil(16);

    public Integer nextSequence(SequenceType type, String dateStr) {
        SystemAsserts.notNull(dateStr, "生成序列号date不能为空");
        SystemAsserts.notNull(type, "生成序列号type不能为空");
        String key = this.getSequenceKey(type.getName(), dateStr);
        init(key);
        return incr(key).intValue();
    }

    private void init(final String key){
        syncUtil.syncExecute(new SyncExecutionUnit() {
            @Override
            public boolean isExecutable() {
                return !exists(key);
            }
            @Override
            public Object getSyncLockSource() {
                return key;
            }
            @Override
            public <R> R execute() {
                incr(key);
                expire(key, EXPIRED_TIME);
                return null;
            }
        });
    }

    private String getSequenceKey(String name, String dateStr){
        return String.format("sequence:%s:%s", name, dateStr);
    }

}
