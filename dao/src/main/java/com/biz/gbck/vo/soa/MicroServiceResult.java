package com.biz.gbck.vo.soa;

import java.io.Serializable;

/**
 * 微服务调用结果
 *
 * Created by david-liu on 2017/04/27 15:52.
 */
public class MicroServiceResult<T> implements Serializable {
    private static final long serialVersionUID = 7232884464275194499L;

    private int status;

    private String msg;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
