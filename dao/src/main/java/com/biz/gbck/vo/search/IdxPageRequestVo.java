package com.biz.gbck.vo.search;

import java.io.Serializable;

/**
 * 分页索引请求Vo
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
public class IdxPageRequestVo implements Serializable {
    private static final long serialVersionUID = -6258388825256778255L;

    private Integer page = 0;

    private Integer pageSize = 10000;

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
