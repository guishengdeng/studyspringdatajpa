package com.biz.gbck.dao.redis.repository.stock;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.StockChangeRo;
import com.biz.redis.util.RedisUtil;
import org.springframework.stereotype.Repository;

/**
 * @author lei
 * @date 2016年12月5日
 */
@Repository
public class StockChangeRedisDao extends CrudRedisDao<StockChangeRo, Long> {

	public static final int STOCK_CHANGE_ID_EXPIRE_PERIOD = 3 * 12 * 60 * 60; //库存变动1.5天过期
	public static final int STOCK_CHANGE_EXPIRE_PERIOD = 3 * 24 * 60 * 60; //库存变动3天过期

	public void save(StockChangeRo ro) {
		super.save(ro);
		expire(getHashKey(ro.getId()), STOCK_CHANGE_EXPIRE_PERIOD);
	}

	public void saveChange(Long id, String bill) {
		setex(getHashKey(getChangeIdKey(id)), STOCK_CHANGE_ID_EXPIRE_PERIOD, RedisUtil.toByteArray(bill));
	}

	public boolean exists(Long id) {
		return super.exists(getHashKey(getChangeIdKey(id)));
	}

	private String getChangeIdKey(Long id) {
		return String.valueOf(id);
	}
}
