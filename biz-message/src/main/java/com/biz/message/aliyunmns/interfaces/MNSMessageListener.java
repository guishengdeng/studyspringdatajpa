package com.biz.message.aliyunmns.interfaces;


import com.aliyun.mns.model.Message;

/**
 * FileName: MNSMessageListener
 * Description:
 * Author: david-liu
 * CreateTime: 2016-07-27 09:27
 */
public interface MNSMessageListener {

    void onMessage(Message message);

}
