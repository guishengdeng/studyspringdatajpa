package com.biz.service;

import com.biz.core.util.ExecutionUnit;
import com.biz.transaction.BizTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
}
