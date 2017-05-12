package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * AdminNotFoundException
 *
 * @author guisheng.deng
 * @date 2017年05月10日
 * @reviewer
 * @description
 * @see
 */
public class AdminNotFoundException extends DepotNextDoorException {

    public static final int code = DepotNextDoorExceptions.User.USER_NOT_EXIST.getCode();
    public AdminNotFoundException(String message){
        super(message);
        super.setCode(code);
    }
}