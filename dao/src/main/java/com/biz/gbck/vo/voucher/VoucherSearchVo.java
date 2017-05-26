package com.biz.gbck.vo.voucher;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class VoucherSearchVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -369036263307990858L;

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 发放人
	 */
	private String issuerName;
	
	/**
	 * 发放时间
	 */
	private String startTime;
	
	@Min(1)
	private Integer page = 1;

	@Max(100)
	private Integer pageSize = 20;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
