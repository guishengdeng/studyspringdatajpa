package com.biz.gbck.mo;

import java.io.Serializable;

/**
 * 微信通知回调信息
 * @author lei
 * @date 2017年5月8日
 * @reviewer 
 */
public class WechatNotifyMessage implements Serializable {

	private static final long serialVersionUID = 5017748340156881524L;

	private String xmlBody;

	private long ts = System.currentTimeMillis();

	public WechatNotifyMessage() {
	}

	public WechatNotifyMessage(String xmlBody) {
		this.xmlBody = xmlBody;
	}

	public String getXmlBody() {
		return xmlBody;
	}

	public void setXmlBody(String xmlBody) {
		this.xmlBody = xmlBody;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

}
