package com.biz.gbck.dao.redis.repository.order;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.order.OrderRo;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.redis.util.RedisUtil;
import org.codelogger.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

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
     * 根据Id删除
     */
    @Override
    public void delete(Long id) {
        OrderRo orderRo = this.findOne(id);
        if (orderRo != null) {
            this.delete(orderRo);
        }
    }


    /**
     * 根据ro删除
     */
    @Override
    public void delete(OrderRo ro) {
        if (ro == null || ro.getId() == null || ro.getUserId() == null) {
            return;
        }
        Long id = ro.getId();
        Long userId = ro.getUserId();
        OrderShowStatus status = ro.getStatus();

        if (status != null) {
            super.zrem(this.orderPeriodSortedSetKey(userId, status), RedisUtil.toByteArray(id));
        } else {
            super.zrem(this.orderPeriodSortedSetKey(userId, OrderShowStatus.ALL), RedisUtil.toByteArray(id));
        }

        super.delete(ro);
    }

    /**
     * 分页查找订单id
     */
    public List<Long> findOrderIdsByUserIdWithPeriod(Long userId, OrderShowStatus status, int page, int size) {
        if (userId == null) {
            return newArrayList();
        }

        if (status == null) {
            status = OrderShowStatus.ALL;
        }
        return super.findIdListFromSortedSetRevrange(this.orderPeriodSortedSetKey(userId, status), page, size);
    }

    /**
     * 获取不同类型订单总数
     */
    public Long findCountByUserIdWithPeriod(Long userId, OrderShowStatus status) {
        if (userId == null) {
            return 0L;
        }

        if (status == null) {
            status = OrderShowStatus.ALL;
        }
        return super.zcount(this.orderPeriodSortedSetKey(userId, status), "-inf", "+inf");
    }

    /**
     * 根据订单
     *
     * @param orderCode
     * @return
     */
    public Long getOrderIdByOrderCode(String orderCode) {
        if (StringUtils.isNotBlank(orderCode)) {
            byte[] bytes = this.get(this.orderCodeToIdMappingKey(orderCode));
            if (bytes != null) {
                return RedisUtil.byteArrayToLong(bytes);
            }
        }
        return null;
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
