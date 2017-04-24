package com.biz.service.product.backend;

import com.biz.core.page.PageResult;
import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import java.util.List;

/**
 * SOA 商品模块分类 Service(后台用)
 *
 * @author david-liu
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public interface CategoryService {

    /**
     * 创建分类
     *
     * @param createCategoryVo 商品分类创建 Vo
     * @return vo 带分类 ID
     */
    CreateCategoryVo createCategory(CreateCategoryVo createCategoryVo) throws CategoryNotFoundException;

    /**
     * 编辑分类
     *
     * @param updateCategoryVo 商品分类 Vo
     */
    void updateCategory(UpdateCategoryVo updateCategoryVo) throws CategoryNotFoundException;

    /**
     * 删除分类
     *
     * @param id 商品分类 ID
     * @return true: 删除成功, false: 删除失败
     */
    boolean deleteCategory(Long id) throws CategoryNotFoundException;

    /**
     * 获取当前层级分类的最大 Idx
     *
     * @param parentCategoryId 父分类 ID(如果为顶级分类父分类 Id 为 NULL)
     * @return 最大 IDX
     */
    Integer getCategoryMaxIdx(Long parentCategoryId);

    /**
     * 返回给前端展示 TreeView
     *
     * @return TreeView 集合
     */
    List<CategoryTreeViewVo> getCategoryTreeViewVos();

    /**
     * 列出所有的分类
     *
     * @param searchPageVo 分页参数
     * @return 商品分类列表
     */
    //todo add category id
    BootstrapTablePageResult<CategoryListItemVo> listCategories(SearchPageVo searchPageVo, Long categoryId);

    /**
     * 将所有的分类信息缓存到内存当中去
     */
    void loadCategory2Cache();

    /**
     * 通过categoryId获取category 基本信息
     *
     * @param categoryId 分类 ID
     */
    CategoryItemVo getCategoryItem(Long categoryId);

    /**
     * 获取 分类 编辑信息
     */
    UpdateCategoryVo getUpdateCategoryVo(Long categoryId);

    /**
     * 保存排序
     */
    void saveOrUpdateSort(CategorySortVo vo);


    /**
     * 根据分类id集合查询分类
     */
    List<IdNameVo> findCategoryByIds(List<Long> ids);


    /**
     * 根据分类名称分页查询分类
     */
    PageResult<IdNameVo> findCategoryByName(SearchPageVo searchPageVo);


    /**
     * 获取下拉列表中的category list
     */
    List<IdNameVo> getSelectCategory();

    /**
     * 获取到最顶层的分类
     */
    List<IdNameVo> getTopCategories();
}
