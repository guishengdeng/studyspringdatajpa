package com.biz.gbck.mo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 支付宝回调消息
 * 
 * @author lei
 * @date 2017年5月8日
 * @reviewer
 */
public class AlipayNotifyMessage implements Serializable {

	private static final long serialVersionUID = -2666111964730860120L;

	private HashMap<String, String> param = new HashMap<String, String>();

	public HashMap<String, String> getParam() {
		return param;
	}

	public void setParam(HashMap<String, String> param) {
		this.param = param;
	}

	public void put(String key, String value) {
		this.param.put(key, value);
	}

	public String get(String key) {
		return this.param.get(key);
	}

}
