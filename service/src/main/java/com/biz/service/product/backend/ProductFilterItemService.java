package com.biz.service.product.backend;


import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.exceptions.product.ProductFilterItemNotFoundException;
import com.biz.gbck.vo.product.backend.*;

/**
 * 过滤条件值sevice
 * @author wangyumin
 * @date 2017年1月5日
 */
public interface ProductFilterItemService {
	
	/**
	 * @param productFilterId 搜索条件ID
	 * @return 当前搜索条件下的所有值
	 */
	BootstrapTablePageResult<ProductFilterItemListVo> listProductFilterItems(Long productFilterId)throws IllegalParameterException,ProductFilterItemNotFoundException;
	
	/**
	 * @param id 条件值ID
	 * @return 需要修改的搜索条件值VO
	 * @throws ProductFilterItemNotFoundException
	 */
	UpdateProductFilterItemVo getUpdateProductFilterItemVo(Long id)throws ProductFilterItemNotFoundException;
	
	/**
	 * 新增的搜索条件值
	 * @param createVo
	 */
	void createProductFilterItem(CreateProductFilterItemVo createVo)throws ProductFilterItemNotFoundException;
	
	/**
	 * 修改的搜索条件值
	 * @param updateVo
	 */
	void updateProductFilterItem(UpdateProductFilterItemVo updateVo)throws ProductFilterItemNotFoundException;
	
	/**
	 * 删除搜索条件值
	 * @param id 值的ID
	 */
	void deleteProductFilterItem(Long id)throws ProductFilterItemNotFoundException;

	/**
	 * 保存排序
	 */
	void saveOrUpdateSort(ProductFilterItemSortVo vo);
	
}
