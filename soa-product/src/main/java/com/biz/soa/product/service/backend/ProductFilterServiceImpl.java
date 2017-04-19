package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.ProductFilter;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.productFilter.ProductFilterRepository;
import com.biz.gbck.exceptions.product.ProductFilterNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.product.backend.ProductFilterService;
import com.biz.gbck.transform.product.CreateProductFilterVo2ProductFilter;
import com.biz.gbck.transform.product.ProductFilter2ProductFilterListItemVo;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品搜索过滤条件ServiceImpl
 *
 * @author wangyumin
 * @date 2016年12月27日
 */
@Service
public class ProductFilterServiceImpl extends AbstractProductFilterService implements ProductFilterService {

    @Autowired
    private ProductFilterRepository productFilterRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 根据分类id返回该分类下的商品过滤条件
     * 1.根据分类id获取当前分类下符合搜索条件的所有搜索条件list
     * 2.将搜索条件list转换为BootstrapTablePageResult对象
     * 3.返回BootstrapTablePageResult对象
     *
     * @return BootstrapTablePageResult 搜索条件list的包装对象
     * @author wangyumin
     */
    @Override
    public BootstrapTablePageResult<ProductFilterListItemVo> listProductFilters(SearchPageVo searchPageVo,
                                                                                Long categoryId) throws ProductFilterNotFoundException {
        List<ProductFilter> list = productFilterRepository.findByCategoryIdAndLabelLikeAndDeleteFlag(categoryId, searchPageVo.getSearchValue(), Boolean.FALSE);
        BootstrapTablePageResult<ProductFilterListItemVo> pageResult = new BootstrapTablePageResult<>();
        pageResult.setRows(Lists.transform(list, new ProductFilter2ProductFilterListItemVo()));
        pageResult.setTotal((int) list.size());
        return pageResult;
    }

    /**
     * 获取修改搜索条件vo
     * 1.根据搜索条件id获取该搜索条件po
     * 2.将获取的po转换为修改搜索条件vo
     * 3.返回该搜索条件vo
     *
     * @return UpdateProductFilterVo修改搜索条件vo
     * @author wangyumin
     */
    @Override
    public UpdateProductFilterVo getUpdateProductFilterVo(Long id) throws ProductFilterNotFoundException {
        ProductFilter productFilter = productFilterRepository.findOne(id);
        UpdateProductFilterVo vo = new UpdateProductFilterVo();
        vo.setId(productFilter.getId().toString());
        vo.setField(productFilter.getField());
        vo.setHasMore(productFilter.getHasMore());
        vo.setLabel(productFilter.getLabel());
        vo.setShowImage(productFilter.getShowImage());
        vo.setStatus(productFilter.getStatus());
        vo.setUsePrefix(productFilter.getUsePrefix());
        return vo;
    }

    /**
     * 新建搜索条件
     * 1.将搜索条件vo转换为po
     * 2.为po构造id
     * 3.获取该搜索条件所属分类下的最大排序值
     * 4.如果排序值为空，则设置排序值为1
     * 5.如果排序值不为空，则设置排序值为最大加1
     * 6.保存该搜索条件po
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void createProductFilter(CreateProductFilterVo createVo) throws ProductFilterNotFoundException {
        CreateProductFilterVo2ProductFilter c2p = new CreateProductFilterVo2ProductFilter();
        ProductFilter productFilter = c2p.apply(createVo);
        productFilter.setId(idService.nextId());
        Integer idx = productFilterRepository.findMaxIdx(createVo.getCategoryId());
        if (idx == null) {
            idx = 1;
        } else {
            idx += 1;
        }
        productFilter.setIdx(idx);
        productFilter.setCategory(categoryRepository.findOne(createVo.getCategoryId()));
        this.saveProductFilter(productFilter);
    }

    /**
     * 修改搜索条件
     * 1.根据搜索条件id获取该搜索条件po
     * 2.如果搜索条件po为空，则抛出异常
     * 3.将修改搜索条件vo的值设置给po
     * 4.保存搜索条件po
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void updateProductFilter(UpdateProductFilterVo updateVo) throws ProductFilterNotFoundException {
        ProductFilter productFilter = productFilterRepository.findByIdAndDeleteFlag(Long.parseLong(updateVo.getId()), Boolean.FALSE);
        if (productFilter == null) {
            throw new ProductFilterNotFoundException("被编辑的过滤条件不存在");
        }
        productFilter.setField(updateVo.getField());
        productFilter.setHasMore(updateVo.getHasMore());
        productFilter.setLabel(updateVo.getLabel());
        productFilter.setShowImage(updateVo.getShowImage());
        productFilter.setStatus(updateVo.getStatus());
        productFilter.setUsePrefix(updateVo.getUsePrefix());
        this.saveProductFilter(productFilter);
    }

    /**
     * 根据搜索条件id删除搜索条件
     * 1.根据id获取搜索条件po
     * 2.如果获取的po为空，抛出异常
     * 3.如果该搜索条件po的删除标识为真，则抛出异常
     * 4.如果该po既不为空，删除标识也不为真，则设置该po的删除标识为真
     * 5.保存该搜索条件po的修改
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void deleteProductFilter(Long id) throws ProductFilterNotFoundException {
        ProductFilter productFilter = productFilterRepository.findOne(id);
        /*删除的过滤条件不存在，抛异常*/
        if (null == productFilter) {
            throw new ProductFilterNotFoundException("被删除的过滤条件不存在");
        }
        if (productFilter.getDeleteFlag() == Boolean.TRUE) {
            throw new ProductFilterNotFoundException("已被删除的过滤条件");
        }
        productFilter.setDeleteFlag(Boolean.TRUE);
        this.saveProductFilter(productFilter);
    }

    @Override
    @Transactional
    public void saveOrUpdateSort(ProductFilterSortVo vo) {
        if (vo != null && vo.getProductFilterSortListVos() != null) {
            List<ProductFilter> productFilters = Lists.newArrayList();
            List<Long> proudctFilterIds = Lists.newArrayList();
            Map<Long, Integer> productFilterIdAndIdx = new HashMap<>();
            for (ProductFilterSortListVo index : vo.getProductFilterSortListVos()) {
                proudctFilterIds.add(index.getId());
                productFilterIdAndIdx.put(index.getId(), index.getIdx());
            }
            productFilters = productFilterRepository.findAll(proudctFilterIds);
            for (ProductFilter productFilter : productFilters) {
                productFilter.setIdx(productFilterIdAndIdx.get(productFilter.getId()));
            }
            this.saveProductFilter(productFilters);
        }
    }

}
