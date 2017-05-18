package com.biz.service.product.impl;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.meta.ProductExtend;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.productExtend.ProductExtendRepository;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.ProductExtendNotFoundException;
import com.biz.gbck.transform.product.CreateCategoryPropertyVo2ProductExtend;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ProductExtendService;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductExtendServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月04日
 * @reviewer
 * @description
 * @see
 */
//@Service
public class ProductExtendServiceImpl extends AbstractBaseService implements ProductExtendService{

    @Autowired
    private ProductExtendRepository productExtendRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BootstrapTablePageResult<CategoryPropertyListItemVo> listCategoryProperties(SearchPageVo searchPageVo, Long categoryId) throws DepotNextDoorException {
        return null;
    }
    /**
     * 增加一个分类下的属性
     * 或者修改一个分类的属性
     */
    @Override
    @Transactional
    public  void createCategoryProperty(CreateCategoryPropertyVo vo )  {
        if(vo.getId() == null){
            vo.setId(idService.nextId());
            Integer idx = productExtendRepository.findMaxIdx(vo.getCategoryId());
            if (idx == null) {
                idx = 1;
            } else {
                idx += 1;
            }
            vo.setIdx(idx);
        }
        //将vo转化成po
        CreateCategoryPropertyVo2ProductExtend c2p = new CreateCategoryPropertyVo2ProductExtend();
        ProductExtend productExtend = c2p.apply(vo);
        Category category = categoryRepository.findOne(vo.getCategoryId());
        productExtend.setCategory(category);
        productExtendRepository.save(productExtend);

    }

    @Override
    public EditCategoryPropertyVo getEditCategoryPropertyVo(Long productExtendId) throws DepotNextDoorException {
        return null;
    }

    @Override
    public void editCategoryProperty(EditCategoryPropertyVo editCategoryPropertyVo) throws DepotNextDoorException {

    }

    /**
     * 此删除是逻辑删除而非物理删除
     * @param productExtendId
     * @return
     * @throws ProductExtendNotFoundException
     */
    @Override
    public boolean deleteProductExtend(Long productExtendId) throws DepotNextDoorException {
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
    /**
     * 根据用户传入的categoryid和属性名来进行判断。如果用户输入
     * 的属性名和根据categoryid查询出来的扩展属性的属性名相等,则返回fasle
     * 说明用户输入的属性名已存在,否则则返回true,（ajax请求）返回true之后,在发送一次请求。同步请求
     */
    @Override
    public Boolean isExistProductExtendName(CreateCategoryPropertyVo vo) throws DepotNextDoorException {
         ProductExtend productExtend = productExtendRepository.existProductExtend(vo.getCategoryId(),vo.getName().trim());
         if(productExtend != null){
               if(vo.getId() != null){
                   if(vo.getId().equals(productExtend.getId())){
                       return Boolean.TRUE;
                   }
               }
             return Boolean.FALSE;
         }
        return Boolean.TRUE;
    }
}