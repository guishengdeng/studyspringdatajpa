package com.biz.vo.demo;

import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by defei on 4/17/17.
 */
public class CatReqVO {

	private Long id;

	@NotNull(message = "名字不能为空")
	@NotBlank(message = "名字不能为空")
	private String name;

	@NotNull(message = "销售状态不能为空")
	private SaleStatusEnum saleStatus;

	@NotNull(message = "状态不能为空")
	private CommonStatusEnum status;

	@NotNull(message = "描述不能为空")
	@NotBlank(message = "描述不能为空")
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
