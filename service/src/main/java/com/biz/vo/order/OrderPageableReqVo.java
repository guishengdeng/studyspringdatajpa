package com.biz.vo.order;

import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.gbck.vo.user.BaseRequestVo;

/**
 * 订单分页请求vo
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
public class OrderPageableReqVo extends BaseRequestVo {

    // 默认为0即第一页
    private int page = 0;

    // 默认为每页10条
    // 不提供从手机提交的请求数据反序列化出size的途径,但是应该可以在后台手动修改
    private int size = 10;

    /**
     * 状态
     */
    private OrderShowStatus status;

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

    public OrderShowStatus getStatus() {
        return status;
    }

    public void setStatus(OrderShowStatus status) {
        this.status = status;
    }
}
