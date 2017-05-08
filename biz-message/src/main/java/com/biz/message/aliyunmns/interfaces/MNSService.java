package com.biz.message.aliyunmns.interfaces;

import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.TopicMessage;

import java.util.List;
import java.util.Map;

/**
 * FileName: MNSService
 * Description:
 * Author: david-liu
 * CreateTime: 2016-07-26 09:39
 */
public interface MNSService {

    void doInit();

    TopicMessage publishMessage2Topic(String topicName, TopicMessage topicMessage)
        throws Exception;

    List<Message> batchPutMessage2Queue(String queueName, List<Message> messages)
        throws Exception;

    Message putMessage2Queue(String queueName, Message message) throws Exception;

    List<Message> batchPeekMessageFromQueue(String queueName, int peekCount)
        throws Exception;

    Message peekMessageFromQueue(String queueName) throws Exception;

    void deleteMessage(String queueName, String receiptHandle) throws Exception;

    void batchDeleteMessage(String queueName, List<String> receiptHandles) throws Exception;

    void bindListenerToQueue(String queueName, MNSMessageListener mnsMessageListener)
        throws Exception;

    void bindListenerToQueue(Map<String, MNSMessageListener> queueListenerMap)
        throws Exception;
}
