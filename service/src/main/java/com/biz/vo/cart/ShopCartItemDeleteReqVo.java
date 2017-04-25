package com.biz.vo.cart;

import com.biz.core.util.DateUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 删除购物车明细vo
 *
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemDeleteReqVo implements Serializable {

    /**
     * 商品编码
     */
    @NotNull(message = "商品编码不能为空")
    private String pCode;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 操作时间
     */
    private Timestamp operateTime = DateUtil.now();

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
