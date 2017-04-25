package com.biz.gbck.dao.redis.repository.cart;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.cart.ShopCartItemRo;
import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.ArrayUtils;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
        ro.setId(getId(ro.getUserId(), ro.getProductCode()));
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
     * @param productCodes 商品编码集合
     */
    public void deleteByUserIdAndProductCodes(Long userId, Collection<String> productCodes) {
        if (userId == null || CollectionUtils.isEmpty(productCodes)) {
            return;
        }
        List<String> ids = newArrayList();
        List<String> idKeys = newArrayList();
        for (String productCode : productCodes) {
            if (productCode != null) {
                ids.add(getId(userId, productCode));
                idKeys.add(super.getHashKey(getId(userId, productCode)));
            }
        }
        super.pipeDelete(idKeys);
        super.zrem(this.getUserIdToShopCartProductIdSortedSetKey(userId), ArrayUtils.toArray(ids));
    }

    /**
     * 生成id (userId:productCode)
     * @param userId
     * @param productCode
     * @return
     */
    private String getId(Long userId, String productCode) {
        return String.format("%s:%s", userId, productCode);
    }

    /**
     * 根据用户Id查询购物车商品明细
     * @param userId
     * @return
     */
    public ShopCartItemRo findByUserIdAndProductCode(Long userId, String productCode) {
        return findOne(getId(userId, productCode));
    }

    /**
     * 根据用户Id查询购物车商品明细
     * @param userId
     * @return
     */
    public List<ShopCartItemRo> findByUserId(Long userId) {
        Set<byte[]> idBytes = super.zrevrange(getFieldSortedSetKey("userId", userId), 0, -1L);
        if (CollectionUtils.isNotEmpty(idBytes)) {
            return findByIds(idBytes);
        } else {
            return newArrayList();
        }
    }


    private String getUserIdToShopCartProductIdSortedSetKey(Long userId) {
        return getKeyByParams("userId", userId);
    }


}
