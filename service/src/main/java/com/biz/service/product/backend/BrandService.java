package com.biz.service.product.backend;

import com.biz.core.page.PageResult;
import com.biz.gbck.exceptions.product.BrandNotFoundException;
import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.product.backend.*;

import java.util.List;

/**
 * 品牌 Service
 *
 * @author david-liu
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public interface BrandService {

    /**
     * 创建品牌
     *
     * @param vo 创建品牌 Vo
     * @return 创建品牌 Vo(带 ID)
     */
    CreateBrandVo createBrand(CreateBrandVo vo) throws CategoryNotFoundException;

    /**
     * 编辑品牌
     *
     * @param brandVo 品牌 Vo
     * @return true: 编辑成功, false: 编辑失败
     */
    void updateBrand(UpdateBrandVo brandVo) throws BrandNotFoundException, CategoryNotFoundException;

    /**
     * 删除品牌
     *
     * @param id 品牌 ID
     * @return true: 删除品牌成功, false: 删除品牌失败
     */
    boolean deleteBrand(Long id) throws BrandNotFoundException;

    /**
     * 获取品牌列表
     *
     * @param searchPageVo 品牌分页查询Vo
     * @return 品牌列表
     */
    BootstrapTablePageResult<BrandListItemVo> listBrands(SearchPageVo searchPageVo, Long categoryId, Boolean cascadeChildCategory) throws IllegalParameterException, CategoryNotFoundException;

    /**
     * 编辑品牌时,获取品牌信息
     */
    UpdateBrandVo getUpdateBrandVo(Long brandId);

    /**
     * 保存排序
     */
    void saveOrUpdateSort(BrandSortVo vo);

    /**
     * 根据品牌id集合查询品牌
     */
    List<IdNameVo> findBrandByIds(List<Long> ids);

    /**
     * 根据品牌名称分页查询品牌
     */
    PageResult<IdNameVo> findBrandsByName(SearchPageVo searchPageVo);

    /**
     * 根据分类ID查询所有没被删除的品牌的ID 名称
     */
    List<IdNameVo> listBrandByCategory(Long categoryId);

    /**
     * 根据随机数量，返回随机数量品牌信息
     */
    List<BrandAndVendorInfoVo> findBrandRandom(Long categoryId, Integer RandomNumber);

    /**
     * 查询所有品牌(拼页面测试)
     * @return
     */
    List<BrandListItemVo> findBrands();
}
