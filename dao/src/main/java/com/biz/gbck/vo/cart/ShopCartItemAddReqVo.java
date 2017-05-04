package com.biz.gbck.vo.cart;

import com.biz.core.util.DateUtil;
import com.biz.gbck.vo.user.BaseRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

/**
 * 添加购物车明细vo
 *
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemAddReqVo extends BaseRequestVo {

    /**
     * 商品编码
     */
    private String pCode;

    /**
     * 数量
     */
    private int quantity = 1;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
