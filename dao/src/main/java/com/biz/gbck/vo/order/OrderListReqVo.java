package com.biz.gbck.vo.order;

import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.vo.user.BaseRequestVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 订单列表请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderListReqVo extends BaseRequestVo {

    private Integer page = 0;

    private Integer size = 10;

    /**
     * 订单状态 {@link OrderStatus}
     */
    private Integer status;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Pageable toPageable() {
        return new PageRequest(page, size);
    }

}
