package com.biz.gbck.vo.order.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by liqi1 on 2017/5/18.
 */
public class OrderReturnListReqVo {

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
