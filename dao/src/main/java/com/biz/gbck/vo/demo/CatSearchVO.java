package com.biz.gbck.vo.demo;

import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by defei on 4/17/17.
 */
public class CatSearchVO {

	private SaleStatusEnum saleStatus;

	private CommonStatusEnum status;

	@Min(1)
	private Integer page = 1;

	@Max(100)
	private Integer pageSize = 20;

	/**
	 * {@linkplain CatSearchVO#saleStatus}
	 */
	public SaleStatusEnum getSaleStatus() {

		return saleStatus;
	}

	/**
	 * {@linkplain CatSearchVO#saleStatus}
	 */
	public void setSaleStatus(SaleStatusEnum saleStatus) {

		this.saleStatus = saleStatus;
	}

	/**
	 * {@linkplain CatSearchVO#status}
	 */
	public CommonStatusEnum getStatus() {

		return status;
	}

	/**
	 * {@linkplain CatSearchVO#status}
	 */
	public void setStatus(CommonStatusEnum status) {

		this.status = status;
	}

	/**
	 * {@linkplain CatSearchVO#page}
	 */
	public Integer getPage() {

		return page;
	}

	/**
	 * {@linkplain CatSearchVO#page}
	 */
	public void setPage(Integer page) {

		this.page = page;
	}

	/**
	 * {@linkplain CatSearchVO#pageSize}
	 */
	public Integer getPageSize() {

		return pageSize;
	}

	/**
	 * {@linkplain CatSearchVO#pageSize}
	 */
	public void setPageSize(Integer pageSize) {

		this.pageSize = pageSize;
	}
}
