package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.tag.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 搜索标签VO
 * Created by lzz on 2017/5/4.
 */
public class SaleTagSearchVo {

    /**
     * 名字
     */
    @NotNull(message = "标签名不能为空")
    @NotBlank(message = "标签名不能为空")
    private String name;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer idx;

    /**
     * 启用状态
     */
    @NotNull(message = "启用或删除状态不能为空")
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

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
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
