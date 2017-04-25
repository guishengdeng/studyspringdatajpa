package com.biz.vo.cart;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 购物车数量vo
 *
 * @author lei
 * @date 2017/04/05
 */
public class ShopCartNumRespVo implements Serializable {

    /**
     * 总数量
     */
    private int totalNum = 0;

    /**
     * 选中数量
     */
    private int selectedNum = 0;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(int selectedNum) {
        this.selectedNum = selectedNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
