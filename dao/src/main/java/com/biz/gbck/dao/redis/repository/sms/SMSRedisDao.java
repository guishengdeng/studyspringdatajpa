package com.biz.gbck.dao.redis.repository.sms;

import com.biz.gbck.common.com.SMSType;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.sms.SMSRo;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by defei on 3/11/16.
 */
@Component
public class SMSRedisDao extends CrudRedisDao<SMSRo,String> {

    public void saveSMSCodeAndAutoExpire(String mobile, SMSType smsType, String code,
                                         int validTimeInSecond) {

        hmset(getKeyByParams(mobile, smsType),
            new SMSRo(code, System.currentTimeMillis()).toMap());
        expire(getKeyByParams(mobile, smsType), validTimeInSecond);
    }

    public SMSRo findSMSCode(String mobile, SMSType smsType) {

        Map<byte[], byte[]> data =
            hgetAll(getKeyByParams(mobile, smsType));
        if (MapUtils.isNotEmpty(data)) {
            SMSRo smsRo = new SMSRo();
            smsRo.fromMap(data);
            return smsRo;
        }
        return null;
    }

    public void deleteSMSCode(String mobile, SMSType smsType) {
        del(getKeyByParams(mobile, smsType));
    }

}
