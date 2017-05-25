package com.biz.gbck.smscenter.interfaces;


import com.biz.gbck.smscenter.common.Message;

/**
 * Function: Define common action of publisher
 * Created by david-liu on 3/10/16.
 */
public interface Notification {

    void sendNotice(Message message);

}
