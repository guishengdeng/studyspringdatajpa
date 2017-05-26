package com.biz.gbck.dao.redis.repository.cart;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.cart.ShopCartItemRo;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.ArrayUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 购物车redis操作dao
 *
 * @author lei
 * @date 2017/01/11
 */
@Repository("shopCartRedisDao")
public class ShopCartItemRedisDao extends CrudRedisDao<ShopCartItemRo, String> {

    /**
     * 保存购物车明细
     *
     * @param ro 优惠券ro
     */
    @Override
    public void save(ShopCartItemRo ro) {
        ro.setId(getId(ro.getUserId(), ro.getProductId()));
        ro.setUpdateTimestamp(DateUtil.now());
        super.save(ro);
    }

    /**
     * 批量保存购物车明细
     *
     * @param ros
     */
    public void save(List<ShopCartItemRo> ros) {
        if (CollectionUtils.isNotEmpty(ros)) {
            for (ShopCartItemRo ro : ros) {
                this.save(ro);
            }
        }
    }

    /**
     * 批量移除购物车商品
     *
     * @param userId     用户Id
     * @param productIds 商品id集合
     */
    public void deleteByUserIdAndProductIds(String userId, Collection<String> productIds) {
        if (userId == null || CollectionUtils.isEmpty(productIds)) {
            return;
        }
        List<String> ids = newArrayList();
        List<String> idKeys = newArrayList();
        for (String productId : productIds) {
            if (productId != null) {
                ids.add(getId(userId, productId));
                idKeys.add(super.getHashKey(getId(userId, productId)));
            }
        }
        super.pipeDelete(idKeys);
        super.zrem(this.getUserIdToShopCartProductIdSortedSetKey(userId), ArrayUtils.toArray(ids));
    }

    /**
     * 生成id (userId:productId)
     * @param userId
     * @param productId
     * @return
     */
    private String getId(String userId, String productId) {
        return String.format("%s:%s", userId, productId);
    }

    /**
     * 根据用户Id查询购物车商品明细
     * @param userId
     * @return
     */
    public ShopCartItemRo findByUserIdAndProductId(String userId, String productId) {
        return findOne(getId(userId, productId));
    }

    /**
     * 根据用户Id查询购物车商品明细
     * @param userId
     * @return
     */
    public List<ShopCartItemRo> findByUserId(String userId) {
        Set<byte[]> idBytes = super.zrevrange(getFieldSortedSetKey("userId", userId), 0, -1L);
        if (CollectionUtils.isNotEmpty(idBytes)) {
            return findByIds(idBytes);
        } else {
            return newArrayList();
        }
    }


    private String getUserIdToShopCartProductIdSortedSetKey(String userId) {
        return getKeyByParams("userId", userId);
    }


    public void removeAllByUserId(String userId) {
        Set<byte[]> idBytes = super.zrevrange(getFieldSortedSetKey("userId", userId), 0, -1L);
        List<String> ids = RedisUtil.bytesSetToStringList(idBytes);
        if (CollectionUtils.isNotEmpty(ids)) {
            List<String> idKeys = ids.stream().map(this::getHashKey).collect(Collectors.toList());
            this.pipeDelete(idKeys);
        }
        this.del(getFieldSortedSetKey("userId", userId));
    }
}
