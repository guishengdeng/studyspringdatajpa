package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.meta.ProductFilterItem;
import com.biz.gbck.dao.mysql.repository.productFilter.ProductFilterRepository;
import com.biz.gbck.dao.mysql.repository.productFilterItem.ProductFilterItemRepository;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.exceptions.product.ProductFilterItemNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.product.backend.ProductFilterItemService;
import com.biz.gbck.transform.product.CreateProductFilterItemVo2ProductFilterItem;
import com.biz.gbck.transform.product.ProductFilterItem2ProductFilterItemListVo;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 搜索过滤条件ServiceImpl
 *
 * @author wangyumin
 * @date 2017年1月5日
 */

@Service
public class ProductFilterItemServiceImpl extends AbstractProductFilterItemService implements ProductFilterItemService {

    @Autowired
    private ProductFilterItemRepository productFilterItemRepository;

    @Autowired
    private ProductFilterRepository productFilterRepository;

    /**
     * 根据过滤条件id获取所有的过滤条件值
     * 1.根据过滤id获取该过滤条件下的所有未删除的条件值list
     * 2.将该条件值list转换为BootstrapTablePageResult包装对象
     * 3.返回所有条件值的包装对象BootstrapTablePageResult
     *
     * @param productFilterId 条件id，必传
     * @return 所有过滤值的包装对象
     * @author wangyumin
     */
    @Override
    public BootstrapTablePageResult<ProductFilterItemListVo> listProductFilterItems(Long productFilterId)
            throws IllegalParameterException, ProductFilterItemNotFoundException {
        List<ProductFilterItem> list = productFilterItemRepository.findByProductFilterIdAndDeleteFlag(productFilterId, Boolean.FALSE);
        BootstrapTablePageResult<ProductFilterItemListVo> pageResult = new BootstrapTablePageResult<>();
        pageResult.setRows(Lists.transform(list, new ProductFilterItem2ProductFilterItemListVo()));
        pageResult.setTotal((int) list.size());
        return pageResult;
    }

    /**
     * 根据条件值id获取修改条件值vo
     * 1.根据条件值id获取条件值po
     * 2.将条件值po转换为修改条件值vo
     * 3.返回转换后的修改条件值vo
     *
     * @return UpdateProductFilterItemVo修改条件值vo
     * @author wangyumin
     */
    @Override
    public UpdateProductFilterItemVo getUpdateProductFilterItemVo(Long id) throws ProductFilterItemNotFoundException {
        ProductFilterItem productFilterItem = productFilterItemRepository.findOne(id);
        UpdateProductFilterItemVo updateProductFilterItemVo = new UpdateProductFilterItemVo();
        updateProductFilterItemVo.setId(productFilterItem.getId());
        updateProductFilterItemVo.setHighlightShow(productFilterItem.getHighlightShow());
        updateProductFilterItemVo.setImage(productFilterItem.getImage());
        updateProductFilterItemVo.setLabel(productFilterItem.getLabel());
        updateProductFilterItemVo.setPrefix(productFilterItem.getPrefix());
        updateProductFilterItemVo.setStatus(productFilterItem.getStatus());
        updateProductFilterItemVo.setValue(productFilterItem.getValue());
        return updateProductFilterItemVo;
    }

    /**
     * 新建条件值
     * 1.将传入的修改条件值vo转换为条件值po
     * 2.为条件值po构建id
     * 3.获取该条件值所属条件下的最大排序值
     * 4.如果排序值为空，则设置该po的排序值为1
     * 5.如果排序值不为空，则设置该po的排序值为最大加1
     * 6.保存该条件值po
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void createProductFilterItem(CreateProductFilterItemVo createVo) throws ProductFilterItemNotFoundException {
        CreateProductFilterItemVo2ProductFilterItem c2p = new CreateProductFilterItemVo2ProductFilterItem();
        ProductFilterItem productFilterItem = c2p.apply(createVo);
        productFilterItem.setId(idService.nextId());
        Integer idx = productFilterItemRepository.findMaxIdx(createVo.getProductFilterId());
        if (idx == null) {
            idx = 1;
        } else {
            idx += 1;
        }
        productFilterItem.setIdx(idx);
        productFilterItem.setProductFilter(productFilterRepository.findOne(createVo.getProductFilterId()));
        this.save(productFilterItem);
    }

    /**
     * 修改条件值
     * 1.根据修改条件值vo的id获得该条件值po
     * 2.如果该条件值po为空，抛出异常
     * 3.将修改条件值vo的值设置给po
     * 4.保存条件值po
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void updateProductFilterItem(UpdateProductFilterItemVo updateVo) throws ProductFilterItemNotFoundException {
        ProductFilterItem productFilterItem = productFilterItemRepository.findOne(updateVo.getId());
        if (productFilterItem == null) {
            throw new ProductFilterItemNotFoundException("被编辑的分类搜索值不存在");
        }
        productFilterItem.setHighlightShow(updateVo.getHighlightShow());
        productFilterItem.setImage(updateVo.getImage());
        productFilterItem.setLabel(updateVo.getLabel());
        productFilterItem.setPrefix(updateVo.getPrefix());
        productFilterItem.setStatus(updateVo.getStatus());
        productFilterItem.setValue(updateVo.getValue());
        this.save(productFilterItem);
    }

    /**
     * 根据条件值id删除条件值
     * 1.根据条件值id获取条件值po
     * 2.如果条件值po为空，抛出异常
     * 3.如果条件值po的删除标识为真，抛出异常
     * 4，如果条件值po不为空且删除标识不为真，则将该条件值po的删除标识设为真
     * 5.保存修改后的po
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void deleteProductFilterItem(Long id) throws ProductFilterItemNotFoundException {
        ProductFilterItem productFilterItem = productFilterItemRepository.findOne(id);
        /**
         * 删除的搜索条件值不存在，抛异常
         */
        if (null == productFilterItem) {
            throw new ProductFilterItemNotFoundException("被删除的搜索条件值不存在");
        }
        if (productFilterItem.getDeleteFlag() == Boolean.TRUE) {
            throw new ProductFilterItemNotFoundException("该条件值已被删除");
        }
        productFilterItem.setDeleteFlag(Boolean.TRUE);
        this.save(productFilterItem);
    }

    @Override
    @Transactional
    public void saveOrUpdateSort(ProductFilterItemSortVo vo) {
        if (vo != null && vo.getProductFilterItemSortListVos() != null) {
            List<ProductFilterItem> productFilterItems = Lists.newArrayList();
            List<Long> productFilterItemIds = Lists.newArrayList();
            Map<Long, Integer> productFilterItemIdAndIdx = new HashMap<>();
            for (ProductFilterItemSortListVo index : vo.getProductFilterItemSortListVos()) {
                productFilterItemIds.add(index.getId());
                productFilterItemIdAndIdx.put(index.getId(), index.getIdx());
            }
            productFilterItems = productFilterItemRepository.findAll(productFilterItemIds);
            for (ProductFilterItem productFilterItem : productFilterItems) {
                productFilterItem.setIdx(productFilterItemIdAndIdx.get(productFilterItem.getId()));
            }
            this.save(productFilterItems);
        }
    }

}
