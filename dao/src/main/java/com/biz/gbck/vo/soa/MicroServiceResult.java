package com.biz.gbck.vo.soa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 微服务调用结果
 *
 * Created by david-liu on 2017/04/27 15:52.
 */
public class MicroServiceResult<T> implements Serializable {
    private static final long serialVersionUID = 7232884464275194499L;

    public static final Integer INTERNAL_ERROR_STATUS = 500;

    public static final Integer SUCCESS_STATUS = 200;

    private int status;

    private String msg;

    private T data;

    public MicroServiceResult() {

    }

    public static <T> MicroServiceResult<T> buildSuccess(){

        MicroServiceResult<T> result = new MicroServiceResult<>();
        result.setStatus(SUCCESS_STATUS);
        return result;
    }

    public static <T> MicroServiceResult<T> buildSuccess(T data){

        MicroServiceResult<T> result = buildSuccess();
        result.setData(data);
        return result;
    }

	@JsonIgnore
    private Exception exception;

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

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return this.status == SUCCESS_STATUS;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}


