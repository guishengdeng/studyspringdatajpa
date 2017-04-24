package com.biz.gbck.dao.mysql.po.demo;

import com.biz.gbck.dao.mysql.po.BasePo;
import com.biz.gbck.enums.CommonStatusEnum;

import javax.persistence.*;

/**
 * Created by defei on 4/17/17.
 */
@Entity
@Table(name = "demo_cat")
public class CatPO extends BasePo<Long> {

	@Id
	private Long id;

	/**
	 * 名字
	 */
	@Column(length = 20, unique = true, nullable = false)
	private String name;

	@Column(length = 255, unique = false)
	private String description;

	@Column
	@Convert(converter = SaleStatusEnum.Converter.class)
	private SaleStatusEnum saleStatus;

	/**
	 * 状态
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private CommonStatusEnum status;

	/**
	 * {@linkplain CatPO#id}
	 */
	@Override
	public Long getId() {

		return id;
	}

	/**
	 * {@linkplain CatPO#id}
	 */
	@Override
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * {@linkplain CatPO#name}
	 */
	public String getName() {

		return name;
	}

	/**
	 * {@linkplain CatPO#name}
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * {@linkplain CatPO#description}
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * {@linkplain CatPO#description}
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * {@linkplain CatPO#saleStatus}
	 */
	public SaleStatusEnum getSaleStatus() {

		return saleStatus;
	}

	/**
	 * {@linkplain CatPO#saleStatus}
	 */
	public void setSaleStatus(SaleStatusEnum saleStatus) {

		this.saleStatus = saleStatus;
	}

	/**
	 * {@linkplain CatPO#status}
	 */
	public CommonStatusEnum getStatus() {

		return status;
	}

	/**
	 * {@linkplain CatPO#status}
	 */
	public void setStatus(CommonStatusEnum status) {

		this.status = status;
	}
}
