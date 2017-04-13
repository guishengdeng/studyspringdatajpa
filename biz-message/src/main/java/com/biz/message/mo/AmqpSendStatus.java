package com.biz.message.mo;

import java.io.Serializable;

/**
 * {@code Amqp}发送状态
 * <p>
 * 因为{@code Amqp}的通信{@code Channel}默认情况之下是自动开启{@code Acknowledge}的,
 * 所以在使用时只要没有显示地去更改{@code Channel}的{@code Acknowledgement Mode}, 消
 * 息都是自动{@code Acknowledgement Mode}
 * <p/>
 *
 * @author david-liu
 * @date 2017年04月01日
 * @reviewer
 */
public class AmqpSendStatus implements Serializable {
    private static final long serialVersionUID = 26932325639843311L;

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 错误消息
     */
    private String errMsg;

    /**
     * 原因
     */
    private Throwable cause;

    public AmqpSendStatus() {
    }

    public AmqpSendStatus(boolean isSuccess, String errMsg, Throwable cause) {
        this.isSuccess = isSuccess;
        this.errMsg = errMsg;
        this.cause = cause;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
