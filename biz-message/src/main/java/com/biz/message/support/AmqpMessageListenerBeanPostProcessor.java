package com.biz.message.support;

import com.biz.core.util.JsonUtil;
import com.biz.message.BizMessage;
import com.biz.message.MessageListener;
import com.biz.message.QueueParser;
import com.biz.message.SimpleBizMessage;
import com.google.common.collect.Lists;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

/**
 * 用于对接rabbit的监听器初始化处理器
 *
 * @author yanweijin
 * @usage 配置为spring管理的bean即可
 * @reviewer
 * @since 2016年7月31日
 */
public class AmqpMessageListenerBeanPostProcessor extends AbstractMessageListenerBeanPostProcessor implements RabbitListenerConfigurer {

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        logger.debug("listener configure begin,listener size=" + listenerContainer.size());
        for (final MessageListener<?> ml : listenerContainer) {
            QueueParser parser = new QueueParser(ml.getListenedQueue());
            AmqpListenerAdapter adapter = new AmqpListenerAdapter(ml);
            SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
            endpoint.setQueueNames(parser.getName());
            endpoint.setMessageListener(adapter);
            endpoint.setId(UUID.randomUUID().toString());
            registrar.registerEndpoint(endpoint);
        }
    }

    /**
     * 转换统一监听器与amqp监听器的适配器
     *
     * @author yanweijin
     * @date 2016年7月31日
     * @reviewer
     */
    private class AmqpListenerAdapter implements org.springframework.amqp.core.MessageListener {

        MessageListener<?> ml;
        Class<?> messageClass;
        Class<?>[] messageClassGenerics;

        AmqpListenerAdapter(MessageListener<?> ml) {
            this.ml = ml;
            //			MessageListener.class.getMethod("onMessage", BizMessage.class);
            Method method = ReflectionUtils.findMethod(ml.getClass(), "onMessage", BizMessage.class);
            ResolvableType messageType = ResolvableType.forMethodParameter(method, 0);
            ResolvableType type = messageType.getGeneric(0);// BizMessage的泛型M,为消息类型
            this.messageClass = type.getRawClass();// 消息类型的class
            List<Class<?>> genericList = Lists.newArrayList();// 消息类型的泛型(如果有
            for (ResolvableType genes : type.getGenerics()) {
                genericList.add(genes.getRawClass());
            }
            this.messageClassGenerics = genericList.toArray(new Class<?>[genericList.size()]);
            logger.debug("wrap listener: {} -> {}", ml, this);
        }

        private Object toPayload(Message msg) {
            String body = null;
            try {
                body = new String(msg.getBody(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return JsonUtil.json2Obj(body, messageClass, messageClassGenerics);
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void onMessage(Message message) {
            Object payload = toPayload(message);
            BizMessage bizMsg = SimpleBizMessage.newMessage(payload);
            ml.onMessage(bizMsg);
        }

    }

}
