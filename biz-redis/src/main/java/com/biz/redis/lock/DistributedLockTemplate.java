package com.biz.redis.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁操作模板
 *
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月22日
 * @reviewer
 */
public interface DistributedLockTemplate {

    /**
     * 使用分布式锁，使用锁默认超时时间。
     */
    <T> T lock(DistributedLockCallback<T> callback);

    /**
     * 使用分布式锁。自定义锁的超时时间
     *
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     */
    <T> T lock(DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit);
}
