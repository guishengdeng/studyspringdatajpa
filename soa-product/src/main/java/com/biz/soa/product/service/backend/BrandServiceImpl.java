package com.biz.soa.product.service.backend;

import com.biz.core.page.PageResult;
import com.biz.gbck.dao.mysql.po.product.meta.Brand;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.exceptions.product.BrandNotFoundException;
import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.product.backend.BrandService;
import com.biz.gbck.transform.product.Brand2BrandListItemVo;
import com.biz.gbck.transform.product.Brand2IdNameVo;
import com.biz.gbck.transform.product.Brand2UpdateBrandVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 品牌 ServiceImpl
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/19
 */
@Service
public class BrandServiceImpl extends AbstractBrandService implements BrandService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 创建品牌
     * 1.创建品牌Po并设置ID
     * 2.将Vo转换为Po
     * 3.设置品牌的分类
     * 4.持久化品牌并返回
     *
     * @param vo 创建品牌 Vo
     * @throws CategoryNotFoundException
     */
    @Override
    @Transactional
    public CreateBrandVo createBrand(CreateBrandVo vo) throws CategoryNotFoundException {
        // 创建品牌, 并且设置 ID
        Brand brand = new Brand();
        brand.setId(idService.nextId());
        brand.fromVo(vo);

        if (logger.isDebugEnabled()) {
            logger.debug("vo: {}", vo.toString());
        }

        // 设置分类
        List<Category> categories = Lists.newArrayList();
        setBrandCategory(vo.getCategoryId(), brand, categories);

        this.saveBrand(brand);
        vo.setId(brand.getId());
        return vo;
    }

    private void setBrandCategory(Long categoryId, Brand brand, List<Category> categories) throws CategoryNotFoundException {
        if (categoryId == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("categoryId is null");
            }
            brand.setCategories(categories);
        } else {
            // 查找数据库中未删除的分类
            Category category = categoryRepository.findByIdAndDeleteFlag(categoryId, Boolean.FALSE);
            if (category == null) {
                logger.debug("categoryId {} is invalid (not exist or already be deleted)");
                throw new CategoryNotFoundException("分类不存在/分类已被删除");
            } else {
                categories.add(category);
                brand.setCategories(categories);
            }
        }
    }

    /**
     * 更新Brand
     * 1.根据条件获取brand
     * 2.根据vo更新brand
     * 3.设置brand的category
     * 4.持久化brand
     *
     * @throws BrandNotFoundException
     * @throws CategoryNotFoundException
     */
    @Override
    @Transactional
    public void updateBrand(UpdateBrandVo vo) throws BrandNotFoundException, CategoryNotFoundException {
        // 根据 ID 获取 Brand(未删除)
        Brand brand = brandRepository.findByIdAndDeleteFlag(vo.getId(), Boolean.FALSE);
        brand.fromVo(vo);
        // 如果品牌不存在, 抛出异常
        if (brand.getId() == null) {
            throw new BrandNotFoundException("被编辑的品牌不存在或者已被删除");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("vo:{}", vo.toString());
        }

        // 设置分类
        List<Category> categories = Lists.newArrayList();
        setBrandCategory(vo.getCategoryId(), brand, categories);
        brand.setCategories(categories);
        this.saveBrand(brand);

        if (logger.isDebugEnabled()) {
            logger.debug("po after save: {}", brand.toString());
        }
    }

    /**
     * 删除品牌
     * 1.查找品牌
     * 2.设置删除标志
     * 3.持久化
     *
     * @param id 品牌 ID
     * @throws BrandNotFoundException
     */
    @Override
    @Transactional
    public boolean deleteBrand(Long id) throws BrandNotFoundException {
        Brand brand = brandRepository.findOne(id);
        // 如果需要被删除的品牌不存在, 抛出异常
        if (brand == null) {
            throw new BrandNotFoundException("被删除的品牌不存在");
        }

        if (brand.getDeleteFlag() == Boolean.TRUE) {
            return Boolean.TRUE;
        }
        brand.setDeleteFlag(Boolean.TRUE);
        this.saveBrand(brand);
        return Boolean.TRUE;
    }

    /**
     * 返回brand列表
     * 1.判断查询分页Vo是否合法,不合法抛出异常
     * 2.通过分类Id查找category,判断分类是否合法,不合法抛出异常
     * 3.判断是否级联查询子分类对应的brand
     * 4.若查询当前分类和它的子分类的所有品牌, 结果需要分页
     * 4.1根据查询条件查询brand,设置分页参数
     * 5.若不级联查询
     * 5.1判断分类ID是否为空或者查询条件是否不为空
     * 5.1.1根据查询条件查询brand,不分页
     * 5.1.2根据categoryId和brand名称查询分类下的brand,不分页
     * 6.返回查询结果vo
     *
     * @param searchPageVo 品牌分页查询Vo
     * @throws IllegalParameterException
     * @throws CategoryNotFoundException
     */
    @Override
    public BootstrapTablePageResult<BrandListItemVo> listBrands(SearchPageVo searchPageVo, Long categoryId,
                                                                Boolean cascadeChildCategory)
            throws IllegalParameterException, CategoryNotFoundException {

        if (searchPageVo == null || searchPageVo.getSearchValue() == null) {
            throw new IllegalParameterException("分类 ID, searchPageVo#searchValue 不能为空");
        }

        Category category = categoryRepository.findByIdAndDeleteFlag(categoryId, Boolean.FALSE);

        if (categoryId != null && category == null) {
            throw new CategoryNotFoundException("分类不存在/分类已被删除");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("vo: {}, categoryId: {}, show child category brand: {}", searchPageVo, categoryId,
                    cascadeChildCategory);
        }

        BootstrapTablePageResult<BrandListItemVo> pageResult = new BootstrapTablePageResult<>();
        List<Brand> brands;
        Page<Brand> brandPage;
        PageRequest pageRequest = new PageRequest(searchPageVo.getPageIndex() - 1, searchPageVo.getPageSize());
        if (cascadeChildCategory != null && cascadeChildCategory) {
            // 查询当前分类和它的子分类的所有品牌, 结果需要分页
            brandPage = this.buildCategoryNotDeleteBrandsCascadeChildren(category,
                    searchPageVo.getSearchValue(), searchPageVo);
        } else {
            //不查询子分类
            if (categoryId == null || StringUtils.isNotBlank(searchPageVo.getSearchValue())) {
                if (StringUtils.isNotBlank(searchPageVo.getSearchValue())) {
                    brandPage = brandRepository.findByDeleteFlagAndNameLike(Boolean.FALSE,
                            "%" + searchPageVo.getSearchValue() + "%", pageRequest);
                } else {
                    brandPage = brandRepository.findByDeleteFlag(Boolean.FALSE, pageRequest);
                }

            } else {
                // 查询当前分类下的所有品牌, 结果不需要分页
                brandPage = brandRepository.findByCategoriesIdAndNameLikeAndDeleteFlag(categoryId,
                        "%" + searchPageVo.getSearchValue() + "%", Boolean.FALSE, pageRequest);
            }
        }
        brands = brandPage.getContent();
        pageResult.setTotal((int) brandPage.getTotalElements());
        pageResult.setRows(Lists.transform(brands, new Brand2BrandListItemVo(null)));
        return pageResult;
    }

    /**
     * 获取更新Brand信息
     * 1.查询brand
     * 2.brand transform vo,返回
     */
    @Override
    public UpdateBrandVo getUpdateBrandVo(Long brandId) {
        Brand brand = brandRepository.findOne(brandId);
        return new Brand2UpdateBrandVo().apply(brand);
    }

    /**
     * 保存brand排序
     * 1.判断vo是否合法
     * 2.构造待更新排序的brand ListId,和id与idx的映射关系
     * 3.通过listId查找List brand
     * 4.设置idx,并持久化listBrand
     */
    @Override
    @Transactional
    public void saveOrUpdateSort(BrandSortVo vo) {
        if (vo != null && vo.getBrandSortListVos() != null) {
            List<Long> brandIds = Lists.newArrayList();
            Map<Long, Integer> brandIdAndIdx = new HashMap<>();
            for (BrandSortListVo index : vo.getBrandSortListVos()) {
                brandIds.add(index.getId());
                brandIdAndIdx.put(index.getId(), index.getIdx());
            }
            List<Brand> brands = brandRepository.findAll(brandIds);
            for (Brand brand : brands) {
                brand.setIdx(brandIdAndIdx.get(brand.getId()));
            }
            this.saveBrands(brands);
        }
    }

    /**
     * 查询brand名称信息
     * 1.根据ids返回符合条件的Brand的id与名称信息
     */
    @Override
    public List<IdNameVo> findBrandByIds(List<Long> ids) {
        return brandList2IdNameList(brandRepository.findByDeleteFlagAndIdIn(Boolean.FALSE, ids));
    }

    private List<IdNameVo> brandList2IdNameList(List<Brand> brandList) {
        List<IdNameVo> resultList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(brandList)) {
            for (Brand index : brandList) {
                resultList.add(new IdNameVo(String.valueOf(index.getId()), index.getName()));
            }
        }
        return resultList;
    }

    /**
     * 通过品牌名称分页查找品牌IdNameVo
     * 1.根据查询条件查找分页Brnad
     * 2.将listBrand转换为ListIdName
     * 3.返回分页
     */
    @Override
    public PageResult<IdNameVo> findBrandsByName(SearchPageVo searchPageVo) {
        Page<Brand> pageBrands = brandRepository.findByNameLikeAndDeleteFlag("%" + searchPageVo.getSearchValue() + "%",
                Boolean.FALSE, new PageRequest(searchPageVo.getPageIndex(), searchPageVo.getPageSize()));
        List<IdNameVo> resultList = brandList2IdNameList(pageBrands.getContent());
        return new PageResult<>(searchPageVo.getPageIndex(), searchPageVo.getPageSize(), (int) pageBrands.getTotalElements(), resultList);
    }

    @Override
    public List<IdNameVo> listBrandByCategory(Long categoryId) {
        List<Brand> brands = brandRepository.findBycategoryIdAndDeleteFlag(categoryId, Boolean.FALSE);
        return Lists.transform(brands, new Brand2IdNameVo());
    }

    /**
     * 获取当前分类下的所有子分类set
     */
    Set<Category> getAllChildCategory(Category category) {
        Set<Category> categorySet = Sets.newHashSet();
        if (CollectionUtils.isEmpty(category.getChildren()) && category.getDeleteFlag() == Boolean.TRUE) {
            return categorySet;
        }
        for (Category index : category.getChildren()) {
            categorySet.add(index);
            if (CollectionUtils.isNotEmpty(index.getChildren())) {
                categorySet.addAll(getAllChildCategory(index));
            }
        }
        return categorySet;
    }

    /**
     * 获取当前分类和当前分类子分类的所有品牌
     * 1.判断分类下的品牌是否为空
     * 2.遍历品牌集合剔除不符合要求的品牌
     * 3.遍历子分类并递归调用原方法,获取所有符合条件的品牌
     *
     * @param category 分类
     * @param searchValue 搜索值
     * @return 品牌列表页列表项集合
     */
    private Page<Brand> buildCategoryNotDeleteBrandsCascadeChildren(Category category, String searchValue, SearchPageVo searchPageVo) {
        List<BrandListItemVo> vos = Lists.newArrayList();
        logger.debug("获取选中分类的子分类");
        Set<Category> categorySet = getAllChildCategory(category);
        categorySet.add(category);
        List<Long> categoryIds = Lists.newArrayList();
        for (Category index : categorySet) {
            categoryIds.add(index.getId());
        }
        logger.debug("需要查询的分类ID集合[{}]");
        Page<Brand> brands = brandRepository.findByCategoriesIdInAndNameLikeAndDeleteFlag(categoryIds,
                "%" + searchValue + "%", Boolean.FALSE,
                new PageRequest(searchPageVo.getPageIndex() - 1, searchPageVo.getPageSize()));
        return brands;
    }

    /**
     * 根据随机数量查询随机数量的品牌
     */
    @Override
    public List<BrandAndVendorInfoVo> findBrandRandom(Long categoryId, Integer RandomNumber) {
        //      List<Brand> list=brandRepository.findByDeleteFlag(categoryId, new PageRequest(0,RandomNumber));
        //      return Lists.transform(list, new Brand2BrandAndVendorInfoVo());
        return Collections.emptyList();
    }

    @Override
    public List<BrandListItemVo> findBrands() {

        //TODO
        return null;
    }
}
