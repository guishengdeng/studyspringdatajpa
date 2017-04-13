package com.biz.message;

public interface BizMessage<M> {
	
	M getPayload();

	MessageProperties getProperties();
	
}
