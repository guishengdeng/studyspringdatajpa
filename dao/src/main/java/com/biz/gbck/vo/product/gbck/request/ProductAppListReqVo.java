package com.biz.gbck.vo.product.gbck.request;

import com.biz.gbck.vo.product.gbck.response.ProductSearchFieldVo;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * App商品列表页请求Vo
 *
 * Created by david-liu on 2017/04/28 09:39.
 */
public class ProductAppListReqVo implements Serializable {
    private static final long serialVersionUID = -8016336127108731901L;

    /**
     * 上级采购方ID(向谁采购商品)
     */
    @NotNull(message = "上级采购方ID不能为空")
    private Long sellerId;

    /**
     * 价格组ID
     */
    @NotNull(message = "商品价格组ID不能为空")
    private Long priceGroupId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 第几页
     */
    private Integer page = 0;

    /**
     * 页大小
     */
    private Integer pageSize = 30;

    /**
     * 搜索过滤字段
     */
    private List<ProductSearchFieldVo> productSearchFields;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 排序
     */
    private String sort;

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductSearchFieldVo> getProductSearchFields() {
        return productSearchFields;
    }

    public void setProductSearchFields(List<ProductSearchFieldVo> productSearchFields) {
        this.productSearchFields = productSearchFields;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "ProductAppListReqVo{" +
                "sellerId=" + sellerId +
                ", priceGroupId=" + priceGroupId +
                ", categoryId=" + categoryId +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", productSearchFields=" + productSearchFields +
                ", keyword='" + keyword + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }

}
