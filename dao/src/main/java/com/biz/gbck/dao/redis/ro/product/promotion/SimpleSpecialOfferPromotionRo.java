package com.biz.gbck.dao.redis.ro.product.promotion;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 简单特价Ro
 * <p>
 * Ro的ID为价格组ID + 商品编码
 * 这样做能在列表页和底层页取简单特价的时候少做Redis操作,
 * 但是这样做针对1个商品简单特价促销对应的Ro数量就是 1 * 价格组数量
 * </p>
 *
 * Created by david-liu on 2017/04/25 12:27.
 */
@Ro(key = "product:promotion:simpleSpecialOffer")
@RoSortedSet(key = "list")
public class SimpleSpecialOfferPromotionRo extends BaseRedisObject<String> {
    private static final long serialVersionUID = -3587986498940399357L;


}
