package com.biz.service;

import com.biz.core.asserts.SystemAsserts;
import com.biz.core.event.BizEvent;
import com.biz.core.event.BizEventPublisher;
import com.biz.core.transaction.BizTransactionManager;
import com.biz.core.util.ExecutionUnit;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.redis.bean.BaseRedisObject;
import com.biz.support.jpa.po.BaseEntity;
import com.google.common.base.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 基础抽象类
 *
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
public abstract class AbstractBaseService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected IdService idService;

    @Autowired
    protected BizEventPublisher publisher;

    /**
     * 使用事务管理器执行redis操作,只有事务成功提交之后才会执行这些操作,本次请求在操作结束后才会返回
     * 类似于使用事务管理器同步发送事件
     *
     * @author yanweijin
     * @date 2016年8月15日
     */
    protected void delayExecuteRedisOpt(ExecutionUnit unit) {
        BizTransactionManager.delayExecuteIfInTx(unit);
    }

    /**
     * 在事务中时,在事务提交(commit)之前执行的代码<br>
     * 用例:		在同一个事务中,我修改并希望持久化一对RO-PO,但我不确定这个对象(主要是PO)在之后的代码中是否会被修改,<br>
     * 因此我将持久化RO-PO的代码放在本方法中,使持久化操作依然在事务之内,并且也达到代码后置的效果,<br>
     * 具体例子可以查看本方法调用链<br>
     *
     * @author yanweijin
     * @date 2016年9月1日
     */
    protected void preCommitOpt(ExecutionUnit unit) {
        BizTransactionManager.preCommitOpt(unit);
    }

    /**
     * 将PO持久化,并同步更新到redis
     * 对redis的修改将在事务提交后生效,如果事务失败,则不会生效
     * 返回PO转换成RO之后的对象
     *
     * @param repo PO使用的JPA repository
     * @param redisDao RO使用的redis dao
     * @param transfer PO转换为RO的函数
     * @param po 要持久化的PO对象,只能是BaseEntity的子类
     * @return 从po转换为ro之后的ro对象
     * @author yanweijin
     * @date 2016年8月19日
     */
    protected <PO extends BaseEntity, RO extends BaseRedisObject<Long>> RO saveOrUpdateUsingPo(JpaRepository<PO,
            Long> repo, final CrudRedisDao<RO, Long> redisDao, PO po, Function<PO, RO> transfer) {
        SystemAsserts.notNull(po.getId(), "po必须要有id");
        if (logger.isDebugEnabled()) {
            logger.debug("save-po {}", po);
        }
        final RO ro = transfer.apply(po);
        if (logger.isDebugEnabled()) {
            logger.debug("transfer-po2ro, ro={}", ro);
        }
        delayExecuteRedisOpt(() -> redisDao.save(ro));
        repo.save(po);
        return ro;
    }

    /**
     * 使用事务管理器异步发送事件,事件只有在事务成功提交之后才会开始发布
     *
     * @author yanweijin
     * @date 2016年8月15日
     */
    protected void publishEventUsingTx(BizEvent event) {
        publisher.publishEventUsingTransactionManager(event);
    }


}
