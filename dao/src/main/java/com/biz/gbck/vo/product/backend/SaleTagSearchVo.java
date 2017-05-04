package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.tag.SaleStatusEnum;
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
    private SaleStatusEnum saleStatus;

    /**
     * （删除）状态
     */
    private CommonStatusEnum status;
    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
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
