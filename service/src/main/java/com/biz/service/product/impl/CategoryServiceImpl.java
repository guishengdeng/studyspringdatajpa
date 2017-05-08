package com.biz.service.product.impl;

import com.biz.core.page.PageResult;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.transform.product.Category2CategoryListItemVo;
import com.biz.gbck.transform.product.Category2UpdateCategoryVo;
import com.biz.gbck.vo.product.backend.*;
import com.biz.gbck.vo.product.event.*;
import com.biz.service.product.backend.CategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分类 ServiceImpl(后台用)
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/19
 */
@Service
public class CategoryServiceImpl extends AbstractCategoryService implements CategoryService {

    /**
     * 后台分类树形结构缓存 key
     */
    private static final String BACKEND_TREE_VIEW_CACHE_KEY = "backend-tree-view";
    /**
     * 分类缓存容器
     */
    private static Map<String, Object> CATEGORY_CACHE = Maps.newHashMap();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CreateCategoryVo createCategory(CreateCategoryVo vo) throws CategoryNotFoundException {
        // 创建 Category, 并且设置 Id
        Category category = new Category();
        category.setId(idService.nextId());
        category.fromVo(vo);
        //设置默认排序
        Integer idx = categoryRepository.findMaxIdx(vo.getParentCategoryId());
        category.setIdx(Optional.of(idx).orElse(0) + 1);

        if (logger.isDebugEnabled()) {
            logger.debug("vo:{}", vo.toString());
        }

        // 设置父分类
        Category parentCategory = null;
        Long parentCategoryId = vo.getParentCategoryId();
        if (parentCategoryId != null) {
            parentCategory = categoryRepository.findByIdAndDeleteFlag(parentCategoryId, Boolean.FALSE);

            if (parentCategory == null) {
                throw new CategoryNotFoundException("父分类不存在");
            }

        }

        if (parentCategory == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("parent category is null, parentCategoryId is {}", vo.getParentCategoryId());
            }
        }
        category.setParent(parentCategory);
        this.saveCategory(category);
        vo.setId(category.getId());
        // 返回之前发送创建分类的事件
        this.publishEventUsingTx(new CreateCategoryEvent(this, new CreateCategoryEventVo(vo)));
        return vo;
    }

    @Override
    @Transactional
    public void updateCategory(UpdateCategoryVo vo) throws CategoryNotFoundException {
        // 根据 CategoryId获取 Category(未删除)
        Category category = categoryRepository.findByIdAndDeleteFlag(vo.getId(), Boolean.FALSE);
        // 如果分类不存在, 抛出异常
        if (category == null) {
            throw new CategoryNotFoundException("被编辑的分类不存在");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("vo:{}", vo.toString());
        }

        category.fromVo(vo);

        // 设置父分类
        Category parentCategory = null;
        Long parentCategoryId = vo.getParentCategoryId();
        if (parentCategoryId != null) {
            parentCategory = categoryRepository.findByIdAndDeleteFlag(parentCategoryId, Boolean.FALSE);
        }

        if (parentCategory == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("parent category is null, parentCategoryId is {}", vo.getParentCategoryId());
            }
        }

        category.setParent(parentCategory);
        this.saveCategory(category);

        // 更新分类成功之后发送更新分类事件
        this.publishEventUsingTx(new UpdateCategoryEvent(this, new UpdateCategoryEventVo(vo)));
    }

    @Override
    @Transactional
    public boolean deleteCategory(Long id) throws CategoryNotFoundException {
        Category category = categoryRepository.findOne(id);
        // 如果被删除的分类不存在, 抛出异常
        if (category == null) {
            throw new CategoryNotFoundException("被删除的分类不存在");
        }

        if (category.getDeleteFlag() == Boolean.TRUE) {
            return Boolean.TRUE;
        }
        category.setDeleteFlag(Boolean.TRUE);
        this.saveCategory(category);

        // 在函数返回之前发送删除分类事件
        this.publishEventUsingTx(new DeleteCategoryEvent(this, new DeleteCategoryEventVo(id)));
        return Boolean.TRUE;
    }

    @Override
    public BootstrapTablePageResult<CategoryListItemVo> listCategories(SearchPageVo searchPageVo, Long categoryId) {
        List<Category> list;
        if (categoryId != null) {
            list = categoryRepository.findByParentIdAndSeoKeywordsLikeAndDeleteFlag(categoryId, searchPageVo.getSearchValue(), Boolean.FALSE);
        } else {
            list = categoryRepository.findByParentIsNullAndDeleteFlagOrderByIdx(Boolean.FALSE);
        }
        BootstrapTablePageResult<CategoryListItemVo> pageResult = new BootstrapTablePageResult<>();
        pageResult.setRows(Lists.transform(list, new Category2CategoryListItemVo()));
        pageResult.setTotal(list.size());
        return pageResult;
    }

    @Override
    public CategoryItemVo getCategoryItem(Long categoryId) {
        Category category = categoryRepository.findOne(categoryId);
        CategoryItemVo categoryItemVo = new CategoryItemVo();
        categoryItemVo.setName(category.getName());
        categoryItemVo.setId(String.valueOf(category.getId()));
        categoryItemVo.setStatus(category.getStatus());
        return categoryItemVo;
    }

    @Override
    public UpdateCategoryVo getUpdateCategoryVo(Long categoryId) {
        Category category = categoryRepository.findOne(categoryId);
        return new Category2UpdateCategoryVo().apply(category);
    }

    @Override
    public Integer getCategoryMaxIdx(Long parentCategoryId) {
        List<Category> categories;
        if (parentCategoryId == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("top level category");
            }
            categories = categoryRepository.findByParentIsNullAndDeleteFlagOrderByIdx(Boolean.FALSE);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("parent category id: {}", parentCategoryId);
            }
            categories = categoryRepository.findByParentIdAndSeoKeywordsLikeAndDeleteFlag(parentCategoryId, null, Boolean.FALSE);
        }
        if (CollectionUtils.isNotEmpty(categories)) {
            if (logger.isDebugEnabled()) {
                logger.debug("categories is not empty, idx:{}", categories.size());
            }
            return categories.size();
        } else {
            return 0;
        }
    }

    @Override
    public List<CategoryTreeViewVo> getCategoryTreeViewVos() {
        // 获取所有的未删除的顶级分类列表
        List<Category> categories = categoryRepository.findByParentIsNullAndDeleteFlagOrderByIdx(Boolean.FALSE);
        return this.buildCategoryTreeView(categories);
    }

    @Override
    public void loadCategory2Cache() {
        List<Category> categories = categoryRepository.findByParentIsNullAndDeleteFlagOrderByIdx(Boolean.FALSE);
        List<CategoryTreeViewVo> categoryTreeViewVos = this.buildCategoryTreeView(categories);

        if (CollectionUtils.isNotEmpty(categories)) {
            if (logger.isDebugEnabled()) {
                logger.debug("loadCategory2Cache from repository: count {}", categories.size());
            }
            CATEGORY_CACHE.put(BACKEND_TREE_VIEW_CACHE_KEY, categoryTreeViewVos);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("categories in repository are empty");
            }
        }
    }

    /**
     * 递归构造分类树形结构
     *
     * @param categories 分类列表
     * @return 分类树形结构
     */
    private List<CategoryTreeViewVo> buildCategoryTreeView(List<Category> categories) {
        // 如果分类集合为空, 返回空集合
        if (CollectionUtils.isEmpty(categories)) {
            if (logger.isDebugEnabled()) {
                logger.debug("there is no category");
            }
            return Lists.newArrayList();
        }

        // 如果分类集合不为空, 递归构造
        return categories.stream().filter(category -> !category.getDeleteFlag()).map(category -> {
            CategoryTreeViewVo categoryTreeViewVo = new CategoryTreeViewVo();
            categoryTreeViewVo.setId(String.valueOf(category.getId()));
            categoryTreeViewVo.setName(category.getName());
            categoryTreeViewVo.setStatus(category.getStatus());
            if (CollectionUtils.isNotEmpty(category.getChildren())) {
                List<CategoryTreeViewVo> children = this.buildCategoryTreeView(category.getChildren());
                categoryTreeViewVo.setChildren(children);
            }
            return categoryTreeViewVo;
        }).collect(Collectors.toList());
    }

    /**
     * 保存排序
     */
    @Override
    @Transactional
    public void saveOrUpdateSort(CategorySortVo vo) {
        if (vo != null && vo.getCategorySortListVos() != null) {
            Map<Long, Integer> categoryId2IdxMap =
                    vo.getCategorySortListVos().stream()
                            .collect(Collectors.toMap(CategorySortListVo::getId, CategorySortListVo::getIdx));
            List<Category> categories = categoryRepository.findAll(categoryId2IdxMap.keySet());
            categories = categories.stream().map(category -> {
                category.setIdx(categoryId2IdxMap.get(category.getId()));
                return category;
            }).collect(Collectors.toList());
            categoryRepository.save(categories);
        }
    }

    @Override
    public List<IdNameVo> findCategoryByIds(List<Long> ids) {
        return categoryList2IdNameList(categoryRepository.findByIdAndDeleteFlag(ids, Boolean.FALSE));
    }

    @Override
    public PageResult<IdNameVo> findCategoryByName(SearchPageVo searchPageVo) {
        PageRequest pageRequest = new PageRequest(searchPageVo.getPageIndex(), searchPageVo.getPageSize());
        Page<Category> pageCategory = categoryRepository.findByNameLikeAndDeleteFlag("%" + searchPageVo.getSearchValue() + "%", Boolean.FALSE, pageRequest);
        List<IdNameVo> resultList = categoryList2IdNameList(pageCategory.getContent());
        return new PageResult<>(searchPageVo.getPageIndex(), searchPageVo.getPageSize(), (int) pageCategory.getTotalElements(), resultList);
    }

    private List<IdNameVo> categoryList2IdNameList(List<Category> categories) {
        return Optional.of(categories).orElse(Lists.newArrayList())
                .stream()
                .map(category -> new IdNameVo(String.valueOf(category.getId()), category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<IdNameVo> getSelectCategory() {
        List<Category> selectCategory = categoryRepository.findByDeleteFlag(Boolean.FALSE);
        return categoryList2IdNameList(selectCategory);
    }

    @Override
    public List<IdNameVo> getTopCategories() {
        List<Category> categories = categoryRepository.findByParentIsNullAndDeleteFlagOrderByIdx(Boolean.FALSE);
        return Optional.of(categories).orElse(Lists.newArrayList())
                .stream().map(category -> new IdNameVo(String.valueOf(category.getId()), category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

}
