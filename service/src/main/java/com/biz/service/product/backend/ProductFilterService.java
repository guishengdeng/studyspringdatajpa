package com.biz.service.product.backend;

import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.exceptions.product.ProductFilterNotFoundException;
import com.biz.gbck.vo.product.backend.*;

/**
 * 商品搜索过滤条件service
 *
 * @author wangyumin
 * @date 2017年1月4日
 */
public interface ProductFilterService {

    /**
     * 当前分类下的所有搜索条件
     *
     * @return 商品分类列表
     */
    BootstrapTablePageResult<ProductFilterListItemVo> listProductFilters(SearchPageVo searchPageVo, Long categoryId) throws IllegalParameterException, ProductFilterNotFoundException;

    /**
     * @return 需要更改的条件
     * @throws ProductFilterNotFoundException
     */
    UpdateProductFilterVo getUpdateProductFilterVo(Long id) throws ProductFilterNotFoundException;

    /**
     * @throws ProductFilterNotFoundException
     */
    void createProductFilter(CreateProductFilterVo createVo) throws ProductFilterNotFoundException;

    /**
     * @throws ProductFilterNotFoundException
     */
    void updateProductFilter(UpdateProductFilterVo updateVo) throws ProductFilterNotFoundException;

    /**
     * @throws ProductFilterNotFoundException
     */
    void deleteProductFilter(Long id) throws ProductFilterNotFoundException;

    /**
     * 修改排序
     */
    void saveOrUpdateSort(ProductFilterSortVo vo);

}
