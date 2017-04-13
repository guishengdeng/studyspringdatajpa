package com.biz.message;

/**
 * 简单的消息体封装实现
 * @author yanweijin
 * @since 2016年7月31日
 * @usage 
 * @reviewer 
 * @param <M>
 */
public class SimpleBizMessage<M> implements BizMessage<M>{

	private M payload;
	private MessageProperties properties;
	
	
	public SimpleBizMessage() {
	}

	public static <M> SimpleBizMessage<M> newMessage(M msg){
		return new SimpleBizMessage<M>(msg);
	}
	
	private SimpleBizMessage(M payload) {
		super();
		this.payload = payload;
		this.properties = new MessageProperties();
	}

	public SimpleBizMessage(M payload, MessageProperties properties) {
		super();
		this.payload = payload;
		this.properties = properties;
	}

	@Override
	public M getPayload() {
		return payload;
	}

	@Override
	public MessageProperties getProperties() {
		return properties;
	}

	public void setPayload(M payload) {
		this.payload = payload;
	}

	public void setProperties(MessageProperties properties) {
		this.properties = properties;
	}

}
