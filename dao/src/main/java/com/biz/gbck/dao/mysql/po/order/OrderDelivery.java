package com.biz.gbck.dao.mysql.po.order;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;

/**
 *
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_order_delivery")
public class OrderDelivery extends BaseEntity {

	/**
	 * 订单
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "order_id")
	private Order order;

	/**
	 * 配送单号
	 */
	@Column(length = 50)
	private String deliveryCode;

	/**
	 * 收货人姓名
	 */
	@Column(length = 50)
	private String name;

	/**
	 * 联系手机号
	 */
	@Column(length = 11)
	private String mobile;

	/**
	 * 联系电话
	 */
	@Column(length = 15)
	private String tel;

	/**
	 * 收货地址
	 */
	@Column(length = 150)
	private String address;

	/**
	 * 物流公司
	 */
	@Column(length = 50)
	private String logicCompany;

	/**
	 * 配送单号
	 */
	@Column(length = 50)
	private String logicNo;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogicCompany() {
		return logicCompany;
	}

	public void setLogicCompany(String logicCompany) {
		this.logicCompany = logicCompany;
	}

	public String getLogicNo() {
		return logicNo;
	}

	public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}
}
