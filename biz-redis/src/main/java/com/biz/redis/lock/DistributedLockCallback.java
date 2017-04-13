package com.biz.redis.lock;

/**
 * 分布式锁回调接口
 *
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月22日
 * @reviewer
 */
public interface DistributedLockCallback<T> {

    /**
     * 调用者必须在此方法中实现需要加分布式锁的业务逻辑
     */
    T process();

    /**
     * 得到分布式锁名称
     */
    String getLockName();
}
