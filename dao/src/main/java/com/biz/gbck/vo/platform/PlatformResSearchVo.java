package com.biz.gbck.vo.platform;


import com.biz.gbck.enums.CommonStatusEnum;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 用于查询平台公司列表
 * Created by dylan on 2017-05-04
 */
public class PlatformResSearchVo {

	private Long id;  //平台公司ID

	private String name; //平台公司名称

	 private String corporateName; //法人名称

	private String mobile; //手机号码

	private CommonStatusEnum status; //公司状态

	public PlatformResSearchVo(Long id, String name, String corporateName, String mobile, CommonStatusEnum status) {
		this.id = id;
		this.name = name;
		this.corporateName = corporateName;
		this.mobile = mobile;
		this.status = status;
	}

	public PlatformResSearchVo() {
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

	public CommonStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CommonStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
