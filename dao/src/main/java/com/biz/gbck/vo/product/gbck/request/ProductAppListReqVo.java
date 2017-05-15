package com.biz.gbck.vo.product.gbck.request;

import com.biz.gbck.vo.product.gbck.response.ProductFilterVO;
import com.biz.gbck.vo.product.gbck.response.ProductSearchFieldVo;
import com.biz.support.web.assist.GlobalParams;
import com.biz.support.web.assist.GlobalParamsAware;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * App商品列表页请求Vo
 *
 * Created by david-liu on 2017/04/28 09:39.
 */
public class ProductAppListReqVo implements GlobalParamsAware, Serializable {
    private static final long serialVersionUID = -8016336127108731901L;

    /**
     * 上级采购方ID(向谁采购商品)
     */
    private Long sellerId;

    /**
     * 价格组ID
     */
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

    /**
     * 搜索结果集过滤条件
     */
    private Map<String, ProductFilterVO> filterMap;

    /**
     * 全局参数
     */
    private GlobalParams globalParams;

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

    public Map<String, ProductFilterVO> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(Map<String, ProductFilterVO> filterMap) {
        this.filterMap = filterMap;
    }

    @Override
    public GlobalParams getGlobalParams() {
        return globalParams;
    }

    @Override
    public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
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
