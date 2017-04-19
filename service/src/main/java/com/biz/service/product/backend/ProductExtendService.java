package com.biz.service.product.backend;


import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.exceptions.product.ProductExtendNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import java.util.List;

/**
 * 商品模块分类属性service
 *
 * @author wangyumin
 * @date 2016年12月27日
 */
public interface ProductExtendService {

    /**
     * 当前分类下返回所有的分类属性
     *
     * @return 商品分类列表
     */
    BootstrapTablePageResult<CategoryPropertyListItemVo> listCategoryProperties(SearchPageVo searchPageVo, Long categoryId) throws IllegalParameterException, ProductExtendNotFoundException;

    /**
     * 增加一个分类下的属性
     */
    void createCategoryProperty(CreateCategoryPropertyVo createCategoryPropertyVo) throws ProductExtendNotFoundException;


    /**
     * 获得修改的Vo
     *
     * EditCategoryPropertyVo 修改分类属性Vo
     */
    EditCategoryPropertyVo getEditCategoryPropertyVo(Long productExtendId) throws ProductExtendNotFoundException;


    /**
     * 修改属性
     */
    void editCategoryProperty(EditCategoryPropertyVo editCategoryPropertyVo) throws ProductExtendNotFoundException;

    /**
     * 删除分类的一个属性
     *
     * @return true:删除成功, false:删除失败
     */
    boolean deleteProductExtend(Long productExtendId) throws ProductExtendNotFoundException;

    /**
     * 保存排序
     */
    void saveOrUpdateSort(PropertySortVo vo);

    /**
     * 返回所有分类属性
     */
    List<AllProductExtendVo> findAllProductExtend();

}
