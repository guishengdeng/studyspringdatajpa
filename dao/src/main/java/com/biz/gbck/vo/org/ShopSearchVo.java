package com.biz.gbck.vo.org;

import com.biz.gbck.dao.mysql.po.org.ShopTypePo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by dylan on 2017-05-04
 */
public class ShopSearchVo {

	/**
	 * 商户id
	 */
	private Long id;

	/**
	 * 商户类型
	 */
	private ShopTypePo shopType;

	/**
	 * 公司名称
	 */
	 private String name;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 资质状态
	 */
	private Integer auditStatus;

	/**
	 * 资质状态Two
	 */
	private Integer auditStatusTwo;


	@Min(1)
	private Integer page = 1;

	@Max(100)
	private Integer pageSize = 20;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShopTypePo getShopType() {
		return shopType;
	}

	public void setShopType(ShopTypePo shopType) {
		this.shopType = shopType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getAuditStatusTwo() {
		return auditStatusTwo;
	}

	public void setAuditStatusTwo(Integer auditStatusTwo) {
		this.auditStatusTwo = auditStatusTwo;
	}

	/**
	 * {@linkplain ShopSearchVo#page}
	 */
	public Integer getPage() {

		return page;
	}

	/**
	 * {@linkplain ShopSearchVo#page}
	 */
	public void setPage(Integer page) {

		this.page = page;
	}

	/**
	 * {@linkplain ShopSearchVo#pageSize}
	 */
	public Integer getPageSize() {

		return pageSize;
	}

	/**
	 * {@linkplain ShopSearchVo#pageSize}
	 */
	public void setPageSize(Integer pageSize) {

		this.pageSize = pageSize;
	}
}
