package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 商品库存 全量
 * 
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月23日
 * @reviewer
 */
@Ro(key = "stock:product")
public class StockRo extends BaseRedisObject<String> {

	/**
	 * stock:sku:id
	 */
	private String id;

	/**
	 * sku编码
	 */
	private String productCode;

	/**
	 * 当前库存 库存全量
	 */
	@RedisWriteIgnore(type = RedisWriteIgnore.IgnoreType.NULL)
	private Integer quantity = 0;

	public StockRo() {
		super();
	}

	public StockRo(String productCode, Integer quantity) {
		this();
		this.id = productCode;
		this.productCode = productCode;
		this.quantity = quantity;
		this.setUpdateTimestamp(DateUtil.now());
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
