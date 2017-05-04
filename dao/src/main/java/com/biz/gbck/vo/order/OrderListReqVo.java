package com.biz.gbck.vo.order;

import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.vo.user.BaseRequestVo;
import org.codelogger.utils.ValueUtils;
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

    private static final long serialVersionUID = -5292997499176479924L;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private Integer page = 0;

    private Integer size = DEFAULT_PAGE_SIZE;

    /**
     * 订单状态 {@link OrderStatus}
     */
    private Integer status;


    public Integer getPage() {
        return ValueUtils.getValue(page) > 0 ? page : 0;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return ValueUtils.getValue(size) > 0 ? size : DEFAULT_PAGE_SIZE;
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
