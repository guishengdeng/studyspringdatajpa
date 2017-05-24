package com.biz.gbck.vo.platform;


import com.biz.gbck.enums.CommonStatusEnum;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 用于查询合伙人返回值公司列表
 * Created by dylan on 2017-05-04
 */
public class PartnerSearchResVo {

	/**
	 * 合伙人ID
	 */
	private Long id;

	/**
	 * 城市合伙人名称
	 */
	private String name;
	/**
	 * 城市合伙人省名称
	 */
	String provinceName;
	/**
	 * 城市合伙人市级名称
	 */
	String cityName;

	/**
	 * 公司状态
	 */
	private CommonStatusEnum status;

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

	/**
	 * 上级平台公司名称
	 */
	private String platformName;

	public PartnerSearchResVo() {
	}

	public PartnerSearchResVo(Long id, String name, String provinceName, String cityName, CommonStatusEnum status, String corporateName, String mobile, Long platformId, String platformName) {
		this.id = id;
		this.name = name;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.status = status;
		this.corporateName = corporateName;
		this.mobile = mobile;
		this.platformId = platformId;
		this.platformName = platformName;
	}

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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public CommonStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CommonStatusEnum status) {
		this.status = status;
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

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
