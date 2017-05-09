package com.biz.service.product.impl;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.dao.mysql.po.product.meta.ProductExtend;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.productExtend.ProductExtendRepository;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.exceptions.product.ProductExtendNotFoundException;
import com.biz.gbck.transform.product.CreateCategoryPropertyVo2ProductExtend;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.IdService;
import com.biz.service.product.backend.ProductExtendService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProductExtendServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月04日
 * @reviewer
 * @description
 * @see
 */
@Service
public class ProductExtendServiceImpl extends AbstractBaseService implements ProductExtendService{

    @Autowired
    private ProductExtendRepository productExtendRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BootstrapTablePageResult<CategoryPropertyListItemVo> listCategoryProperties(SearchPageVo searchPageVo, Long categoryId) throws IllegalParameterException, ProductExtendNotFoundException {
        return null;
    }
    /**
     * 增加一个分类下的属性
     * 或者修改一个分类的属性
     */
    @Override
    @Transactional
    public Boolean createCategoryProperty(CreateCategoryPropertyVo createCategoryPropertyVo )  {
        if(createCategoryPropertyVo.getId() == null){
            createCategoryPropertyVo.setId(idService.nextId());
            Integer idx = productExtendRepository.findMaxIdx(createCategoryPropertyVo.getCategoryId());
            if (idx == null) {
                idx = 1;
            } else {
                idx += 1;
            }
            createCategoryPropertyVo.setIdx(idx);
        }
        //将vo转化成po
        CreateCategoryPropertyVo2ProductExtend c2p = new CreateCategoryPropertyVo2ProductExtend();
        List<ProductExtend> currProductExtends = productExtendRepository.findByCategoryId(createCategoryPropertyVo.getCategoryId());
        //扩展属性,做重复校验
        if(currProductExtends != null){
            for(ProductExtend item : currProductExtends ){
                if(item.getName().equals(createCategoryPropertyVo.getName()) && item.getId() == createCategoryPropertyVo.getId() ){
                    //说明此时用户要修改的是除属性值以外的字段,在这里即修改的是：是否启用那个字段
                    ProductExtend productExtend = c2p.apply(createCategoryPropertyVo);
                    Category category = categoryRepository.findOne(createCategoryPropertyVo.getCategoryId());
                    productExtend.setCategory(category);
                    productExtendRepository.save(productExtend);
                    return Boolean.TRUE;
                }
            }
        }
        ProductExtend productExtend = c2p.apply(createCategoryPropertyVo);
        Category category = categoryRepository.findOne(createCategoryPropertyVo.getCategoryId());
        productExtend.setCategory(category);
        productExtendRepository.save(productExtend);
        return Boolean.TRUE;
    }

    @Override
    public EditCategoryPropertyVo getEditCategoryPropertyVo(Long productExtendId) throws ProductExtendNotFoundException {
        return null;
    }

    @Override
    public void editCategoryProperty(EditCategoryPropertyVo editCategoryPropertyVo) throws ProductExtendNotFoundException {

    }

    /**
     * 此删除是逻辑删除而非物理删除
     * @param productExtendId
     * @return
     * @throws ProductExtendNotFoundException
     */
    @Override
    public boolean deleteProductExtend(Long productExtendId) throws ProductExtendNotFoundException {
        return false;
    }

    @Override
    @Transactional
    public void saveOrUpdateSort(PropertySortVo vo) {
        if (vo != null && vo.getPropertySortListVos() != null) {
            List<ProductExtend> productExtends = Lists.newArrayList();
            List<Long> productExtendIds = Lists.newArrayList();
            Map<Long, Integer> productExtendIdAndIdx = new HashMap<>();
            for (PropertySortListVo index : vo.getPropertySortListVos()) {
                productExtendIds.add(index.getId());
                productExtendIdAndIdx.put(index.getId(), index.getIdx());
            }
            productExtends = productExtendRepository.findAll(productExtendIds);
            for (ProductExtend productExtend : productExtends) {
                productExtend.setIdx(productExtendIdAndIdx.get(productExtend.getId()));
            }
            productExtendRepository.save(productExtends);
        }
    }

    @Override
    public List<AllProductExtendVo> findAllProductExtend() {
        return null;
    }

    @Override
    public List<ProductExtend> findByCategoryId(Long id) {
        return productExtendRepository.findByCategoryId(id);
    }

    /**
     * 根据id查询扩展属性
     * @param id
     * @return
     */
    @Override
    public ProductExtend findOne(Long id) {
        return productExtendRepository.findOne(id);
    }

    @Override
    public List<ProductExtend> findAll() {
        return productExtendRepository.findAll();
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public List<ProductExtendVo> productExtend2ProductExtendVo(List<ProductExtend> list){
        List<ProductExtendVo> voLists = Lists.newArrayList();
        for( ProductExtend item : list){
            ProductExtendVo vo = new  ProductExtendVo();
            vo.setIdx(item.getIdx());
            vo.setId(item.getId());
            vo.setName(item.getName());
            vo.setStatus(item.getStatus());
            voLists.add(vo);
        }
         return voLists ;
    }
}