package com.biz.message;

/**
 * 简单的消息体封装实现
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年7月31日
 */
public class SimpleBizMessage<M> implements BizMessage<M> {

    private M payload;
    private MessageProperties properties;


    public SimpleBizMessage() {
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

    public static <M> SimpleBizMessage<M> newMessage(M msg) {
        return new SimpleBizMessage<M>(msg);
    }

    @Override
    public M getPayload() {
        return payload;
    }

    public void setPayload(M payload) {
        this.payload = payload;
    }

    @Override
    public MessageProperties getProperties() {
        return properties;
    }

    public void setProperties(MessageProperties properties) {
        this.properties = properties;
    }

}
