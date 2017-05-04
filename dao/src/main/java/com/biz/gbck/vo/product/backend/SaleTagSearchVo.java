package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 搜索标签VO
 * Created by lzz on 2017/5/4.
 */
public class SaleTagSearchVo {

    /**
     * 名字
     */
    private String name;

    /**
     * 启用状态
     */
    private String saleStatus;

    @Min(1)
    private Integer page=1;

    @Max(100)
    private Integer pageSize=5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

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
