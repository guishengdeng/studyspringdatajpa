package com.biz.vo.cart;

import com.biz.gbck.vo.user.BaseRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 购物车列表reqVo
 *
 * @author lei
 * @date 2017/04/22
 */
public class ShopCartListReqVo extends BaseRequestVo {

    private int page = 0;

    private int size = 20;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
