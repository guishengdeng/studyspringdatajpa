package com.biz.gbck.dao.mysql.po.payment;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name = "payment_log")
@Inheritance(strategy = InheritanceType.JOINED)
public class PaymentLogPo implements  Serializable {

	private static final long serialVersionUID = 5126011849782800917L;

	@Id
	private Long id;

	@Column(length = 60)
	private String transactionId;

	@Column
	private Long orderPaymentId;

	@Column(columnDefinition = "timestamp")
	private Timestamp ts;

	@Column(columnDefinition = "text")
	private String log;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}



  public Long getOrderPaymentId() {
    return orderPaymentId;
  }

  public void setOrderPaymentId(Long orderPaymentId) {
    this.orderPaymentId = orderPaymentId;
  }

  public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}
}
