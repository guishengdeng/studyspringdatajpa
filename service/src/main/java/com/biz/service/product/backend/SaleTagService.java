package com.biz.service.product.backend;

import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.exceptions.product.SaleTagNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import java.util.List;

/**
 * 商品销售标签 Service 接口定义
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
public interface SaleTagService {

    /**
     * 创建销售标签
     *
     * @param createSaleTagVo 销售标签 Vo
     * @return true: 创建销售标签成功, false: 创建销售标签失败
     */
    CreateSaleTagVo createSaleTag(CreateSaleTagVo createSaleTagVo) throws CategoryNotFoundException;

    /**
     * 编辑销售标签
     *
     * @param updateSaleTagVo 销售标签 Vo
     * @return true: 编辑销售标签成功, false: 编辑销售标签失败
     */
    void updateSaleTag(UpdateSaleTagVo updateSaleTagVo) throws SaleTagNotFoundException, CategoryNotFoundException;

    /**
     * 删除销售标签
     *
     * @param id 销售标签 ID
     * @return true: 删除销售标签成功, false: 删除销售标签失败
     */
    Boolean deleteSaleTag(Long id) throws SaleTagNotFoundException;

    /**
     * 列出所有的销售标签,不分页
     *
     * @return 商品分类列表
     */
    List<SaleTagListItemVo> listSaleTagItem(Long categoryId, SearchPageVo searchPageVo);


    /**
     * 编辑商品销售标签
     */
    UpdateSaleTagVo getUpdateSaleTagVo(Long saletagId);

    /**
     * 保存分类下的销售标签排序
     */
    void saveOrUpdateSort(SaletagSortListVo saletagSortListVos);

    /**
     * 列表展示销售标签下的商品(不分页)
     */
    List<SaleTagProductVo> listSaleTagProductVo(Long saletagId);


    /**
     * 向销售标签中添加商品
     */
    void addProduct2SaleTag(SaleTagProductVo reqVo);


    /**
     * 从销售标签中移除商品
     */
    void removeProduct2Saletag(SaleTagProductVo reqVo);
}
