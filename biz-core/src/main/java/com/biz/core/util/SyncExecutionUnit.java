package com.biz.core.util;

/**
 * 同步操作执行单元,配合同步工具使用{@link SyncUtil}
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 * @see SyncUtil
 */
public interface SyncExecutionUnit {
    /**
     * 操作是否可执行,在进入同步体前判断一次,进入后判断一次
     *
     * @author yanweijin
     * @date 2016年7月24日
     */
    boolean isExecutable();

    /**
     * 获取同步锁数据源,一般是id,数据源经过hash处理和取模运算后,拿到同步锁池中的锁,来进行同步操作
     * 根据锁池的大小,降低碰撞概率
     *
     * @return 同步锁数据源, 不能为null
     * @author yanweijin
     * @date 2016年7月24日
     */
    Object getSyncLockSource();

    /**
     * 同步方法体,放置需要进行同步操作的逻辑
     *
     * @author yanweijin
     * @date 2016年7月24日
     * @see SyncUtil#syncExecute(SyncExecutionUnit)
     */

    <R> R execute();
}
