package com.biz.gbck.vo.platform;

import com.biz.gbck.dao.mysql.po.org.ShopTypePo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 用于查询平台公司列表
 * Created by dylan on 2017-05-04
 */
public class PlatformSearchVo {

	/**
	 * 合伙人ID
	 */
	private Long id;

	/**
	 * 合伙人名称
	 */
	 private String name;

	/**
	 * 手机号码
	 */
	private String mobile;

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


	/**
	 * {@linkplain PlatformSearchVo#page}
	 */
	public Integer getPage() {

		return page;
	}

	/**
	 * {@linkplain PlatformSearchVo#page}
	 */
	public void setPage(Integer page) {

		this.page = page;
	}

	/**
	 * {@linkplain PlatformSearchVo#pageSize}
	 */
	public Integer getPageSize() {

		return pageSize;
	}

	/**
	 * {@linkplain PlatformSearchVo#pageSize}
	 */
	public void setPageSize(Integer pageSize) {

		this.pageSize = pageSize;
	}
}
