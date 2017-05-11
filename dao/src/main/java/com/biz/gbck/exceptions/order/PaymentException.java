package com.biz.gbck.exceptions.order;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 订单支付异常
 *
 * @author lei
 * @date 2017年5月6日
 * @reviewer
 * @see
 */
public class PaymentException extends DepotNextDoorException {


    private static final long serialVersionUID = 1345181844392645914L;

    private static final int code = DepotNextDoorExceptions.Payment.PAYMENT_ERROR.getCode();

    public PaymentException(String message) {
        super(message);
        super.setCode(code);
    }

}
