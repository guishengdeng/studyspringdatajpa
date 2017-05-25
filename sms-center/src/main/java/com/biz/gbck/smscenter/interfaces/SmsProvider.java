package com.biz.gbck.smscenter.interfaces;


import com.biz.gbck.smscenter.common.Message;
import com.biz.gbck.smscenter.common.SendResult;
import com.biz.gbck.smscenter.common.SmsQueryResult;

/**
 * Function: SmsProvider action define
 * Created by david-liu on 3/11/16.
 */
public abstract class SmsProvider {

     public abstract SendResult sendNotice(Message message);

     public abstract SmsQueryResult queryStatus();
}
