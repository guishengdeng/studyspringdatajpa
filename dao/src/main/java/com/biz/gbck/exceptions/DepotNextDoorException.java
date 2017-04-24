package com.biz.gbck.exceptions;

/**
 * Created by david-liu on 2017/04/17 11:04.
 */
public class DepotNextDoorException extends Exception {

    private static final long serialVersionUID = -6106051494556329838L;

    private int code;

    public DepotNextDoorException() {
        super();
    }

    public DepotNextDoorException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
