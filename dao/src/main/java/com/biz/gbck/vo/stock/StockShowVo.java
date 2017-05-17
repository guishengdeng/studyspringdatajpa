package com.biz.gbck.vo.stock;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 库存
 * Created by lzz on 2017/5/17.
 */
public class StockShowVo implements Serializable {

    private static final long serialVersionUID = 4812282780325449250L;

    private Long id;

    /**
     * 隔壁仓库、省公司(平台公司)、合伙人
     */
    private Long companyId;

    /**
     * 公司名称(冗余)
     */
    private String name;

    /**
     * 商品Id
     */
    private Long productId;


    /**
     * 库存数量
     */
    private Integer quantity;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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