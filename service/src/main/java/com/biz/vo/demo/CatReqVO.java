package com.biz.vo.demo;

import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;

import javax.validation.constraints.NotNull;

/**
 * Created by defei on 4/17/17.
 */
public class CatReqVO {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private SaleStatusEnum saleStatus;

	@NotNull
	private CommonStatusEnum status;

	@NotNull
	private String description;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public SaleStatusEnum getSaleStatus() {

		return saleStatus;
	}

	public void setSaleStatus(SaleStatusEnum saleStatus) {

		this.saleStatus = saleStatus;
	}

	public CommonStatusEnum getStatus() {

		return status;
	}

	public void setStatus(CommonStatusEnum status) {

		this.status = status;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}
}
