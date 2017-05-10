package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 测试促销Vo
 *
 * Created by david-liu on 2017/04/27 00:01.
 */
public class TestPromotionVo implements Serializable {
    private static final long serialVersionUID = 1475816996588854526L;

    private String name;

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
