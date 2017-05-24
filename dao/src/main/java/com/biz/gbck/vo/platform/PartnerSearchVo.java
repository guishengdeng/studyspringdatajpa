package com.biz.gbck.vo.platform;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 用于查询合伙人列表
 * Created by dylan on 2017-05-04
 */
public class PartnerSearchVo {

	/**
	 * 合伙人ID
	 */
	private Long id;

	/**
	 * 法人名称
	 */
	 private String corporateName;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 平台公司ID
	 */
	private Long platformId;


	@Min(1)
	private Integer page = 1;

	@Max(100)
	private Integer pageSize = 20;

	public PartnerSearchVo() {
	}

	public PartnerSearchVo(Long platformId) {
		this.platformId = platformId;
		this.pageSize=Integer.MAX_VALUE;
	}

	public PartnerSearchVo(Long id, String corporateName, String mobile, Long platformId, Integer page, Integer pageSize) {
		this.id = id;
		this.corporateName = corporateName;
		this.mobile = mobile;
		this.platformId = platformId;
		this.page = page;
		this.pageSize = pageSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * {@linkplain PartnerSearchVo#page}
	 */
	public Integer getPage() {

		return page;
	}

	/**
	 * {@linkplain PartnerSearchVo#page}
	 */
	public void setPage(Integer page) {

		this.page = page;
	}

	/**
	 * {@linkplain PartnerSearchVo#pageSize}
	 */
	public Integer getPageSize() {

		return pageSize;
	}

	/**
	 * {@linkplain PartnerSearchVo#pageSize}
	 */
	public void setPageSize(Integer pageSize) {

		this.pageSize = pageSize;
	}
}
