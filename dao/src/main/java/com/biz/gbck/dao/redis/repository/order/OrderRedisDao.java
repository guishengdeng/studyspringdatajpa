package com.biz.gbck.dao.redis.repository.order;

import com.biz.core.asserts.SystemAsserts;
import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.order.OrderRo;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.lang.math.NumberUtils;
import org.codelogger.utils.CollectionUtils;
import org.codelogger.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

        Long id = ro.getId();
        Long userId = ro.getUserId();
        OrderShowStatus status = ro.getStatus();

        this.clean(id, userId, status);
        super.save(ro);

        if (status == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("订单状态为空. order: {}", ro);
            }
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
     * 移除冗余状态
     */
    private void clean(Long orderId, Long userId, OrderShowStatus status) {
        OrderRo orderRo = super.findOne(orderId);
        if (orderRo != null && orderRo.getStatus() != status) {
            SystemAsserts.isTrue(Objects.equals(userId, orderRo.getUserId()), "订单所在用户不匹配");
            super.zrem(this.orderPeriodSortedSetKey(userId, orderRo.getStatus()), RedisUtil.toByteArray(orderRo.getId()));
            if (status != OrderShowStatus.PRE_PAY) { //移出待支付
                super.zrem(this.orderExpireSortedSetKey(), RedisUtil.toByteArray(orderId));
            }
        }
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
    public List<Long> findOrderIdsByUserIdWithPeriod(Long userId, OrderShowStatus status, String lastFlag, int size) {
        if (userId == null) {
            return newArrayList();
        }

        if (status == null) {
            status = OrderShowStatus.ALL;
        }
        long page = lastFlag != null ? super.zrevrank(this.orderPeriodSortedSetKey(userId, status), lastFlag.getBytes
                ()) + 1 : 0;

        return super.findIdListFromSortedSetRevrange(this.orderPeriodSortedSetKey(userId, status), (int) page, size);
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
     * 根据订单编码获取id
     * @param orderCode
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
     * 查询所有应该过期的订单id
     * @author yanweijin
     * @date 2016年9月21日
     * @return
     */
    public List<Long> getAllToBeExpiredOrderIds(){
        Set<String> idKeys =
                zrangeByScore(this.orderExpireSortedSetKey(), 0L, DateUtil.now().getTime());
        if (CollectionUtils.isNotEmpty(idKeys)){
            zremrangeByRank(this.orderExpireSortedSetKey(), 0, idKeys.size() - 1);
        }
        return idKeys.stream().filter(NumberUtils::isNumber).map(Long::valueOf).collect(Collectors.toList());
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
