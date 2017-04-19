package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 更改中台状态的返回Vo
 *
 * @author zhangcheng
 * @date 2017/3/16
 * @reviewer
 * @see
 */
public class UpdateMnsProductStatusResponseVo implements Serializable {

    private static final long serialVersionUID = 2953200921709339725L;

    /**
     * 创建新的Product对象id
     */
    private String productId;

    /**
     * 返回码（“0”：成功，“1”：失败，“3”：未找到该中台商品）
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
