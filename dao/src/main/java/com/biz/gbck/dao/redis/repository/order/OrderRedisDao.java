package com.biz.gbck.dao.redis.repository.order;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.order.OrderRo;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.redis.util.RedisUtil;
import org.springframework.stereotype.Repository;

/**
 * 订单redisDao
 *
 * @author lei
 * @date 2017年04月24日
 * @reviewer
 * @see
 */
@Repository
public class OrderRedisDao extends CrudRedisDao<OrderRo, Long> {

    @Override
    public void save(OrderRo ro) {

        if (ro == null || ro.getId() == null || ro.getUserId() == null) {
            return;
        }

        super.save(ro);

        Long id = ro.getId();
        Long userId = ro.getUserId();
        OrderShowStatus status = ro.getStatus();

        if (status == null) {
            logger.debug("订单状态为空");
        } else if (status == OrderShowStatus.PRE_PAY) { //待付款过期
            super.zadd(this.orderExpireSortedSetKey(), ro.getExpireTimestamp(), id);
        } else {
            super.zadd(this.orderPeriodSortedSetKey(userId, status), ro.getCreateTimestamp(), id);
        }

        //用户全部订单SortedSet
        super.zadd(this.orderPeriodSortedSetKey(userId, OrderShowStatus.ALL), ro.getCreateTimestamp(), id);

        //orderCode ==> id
        super.set(this.orderCodeToIdMappingKey(ro.getOrderCode()), RedisUtil.toByteArray(id));

    }



    /**
     * 订单编码映射订单Id
     */
    private String orderCodeToIdMappingKey(String orderCode) {
        return getKeyByParams("orderCode", orderCode);
    }

    /**
     * 用户订单状态SortedSet
     */
    private String orderPeriodSortedSetKey(Long userId, OrderShowStatus status) {
        return getKeyByParams("userId", userId, status);
    }

    /**
     * 过期订单SortedSet
     */
    private String orderExpireSortedSetKey() {
        return getKeyByParams("expire:list");
    }
}
