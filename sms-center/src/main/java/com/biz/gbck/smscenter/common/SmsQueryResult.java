package com.biz.gbck.smscenter.common;

/**
 * Function: Short message account information definition
 * Created by david-liu on 3/11/16.
 */
public class SmsQueryResult {

    private int SmsRemainCount;

    private float SmsAccount;

    public SmsQueryResult(int smsRemainCount, float smsAccount) {
        SmsRemainCount = smsRemainCount;
        SmsAccount = smsAccount;
    }

    public int getSmsRemainCount() {
        return SmsRemainCount;
    }

    public float getSmsAccount() {
        return SmsAccount;
    }
}
