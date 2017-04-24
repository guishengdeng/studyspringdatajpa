package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * @author death
 * @date 2016年09月26日
 * @revirewer
 * @see
 */
public class AliRecRespVo implements Serializable {
    private static final long serialVersionUID = 8731145861616935367L;

    private String code;

    private AliRecDataVo data;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AliRecDataVo getData() {
        return data;
    }

    public void setData(AliRecDataVo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AliRecRespVo{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
