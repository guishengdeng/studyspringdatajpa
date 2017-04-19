package com.biz.gbck.exceptions.evaluation;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 评价不存在异常
 *
 * @author yangzichun
 * @date 2017/2/21
 */
public class EvaluationNotExistException extends DepotNextDoorException {


    private static final int code = DepotNextDoorExceptions.Product.EVALUATION_NOT_EXISTS.getCode();

    public EvaluationNotExistException(String message) {
        super(message);
        super.setCode(code);
    }
}
