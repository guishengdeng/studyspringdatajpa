package com.biz.gbck.exceptions.partner;

import com.biz.gbck.exceptions.DepotNextDoorException;

/**
 * Created by haibin.tang on 2017/5/8.
 * 合伙人异常信息
 */
public class PartnerExceptions  extends DepotNextDoorException {

    public PartnerExceptions(int code, String message) {
        super(message);
        super.setCode(code);
    }
}
