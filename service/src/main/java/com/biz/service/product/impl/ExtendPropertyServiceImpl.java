package com.biz.service.product.impl;

import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.dao.mysql.repository.extendProperty.ExtendPropertyRepository;
import com.biz.gbck.dao.mysql.repository.productExtend.ProductExtendRepository;
import com.biz.gbck.exceptions.product.ExtendPropertyNotFoundException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.transform.product.CreateExtendPropertyVo2ExtendProperty;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.IdService;
import com.biz.service.product.backend.ExtendPropertyService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExtendPropertyServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月04日
 * @reviewer
 * @description
 * @see
 */
@Service
public class ExtendPropertyServiceImpl extends AbstractBaseService  implements ExtendPropertyService {
    @Autowired
    private ExtendPropertyRepository extendPropertyRepository;
    @Autowired
    private ProductExtendRepository productExtendRepository;

    @Override
    public BootstrapTablePageResult<ExtendPropertyListItemVo> listExtendProperties(Long productExtendId) throws IllegalParameterException, ExtendPropertyNotFoundException {
        return null;
    }

    @Override
    public UpdateExtendPropertyVo getUpdateExtendPropertyVo(Long id) throws ExtendPropertyNotFoundException {
        return null;
    }

    /**
     *  添加或修改扩展属性值
     * @param
     * @throws
     */
    @Override
    @Transactional
    public Boolean createExtendProperty(CreateExtendPropertyVo vo) throws ExtendPropertyNotFoundException {
           if(vo.getId() == null){
               vo.setId(idService.nextId());
               Integer idx = extendPropertyRepository.findMaxIdx(vo.getProductExtendId());
               if(idx == null){
                   idx = 1;
               }else {
                   idx += 1;
               }
               vo.setIdx(idx);
           }
           //将vo转化成po
           CreateExtendPropertyVo2ExtendProperty  c2p = new CreateExtendPropertyVo2ExtendProperty();
           List<ExtendProperty> currExtendProperties = extendPropertyRepository.findByProductExtendId(vo.getProductExtendId());
           //扩展属性值,做重复校验
           if(currExtendProperties != null){
               for(ExtendProperty item : currExtendProperties ){
                    if(item.getValue().equals(vo.getValue()) && item.getId() == vo.getId() ){
                             //说明此时用户要修改的是除属性值以外的字段,在这里即修改的是：是否启用那个字段
                             ExtendProperty extendProperty = c2p.apply(vo);
                             extendProperty.setProductExtend(productExtendRepository.findOne(vo.getProductExtendId()));
                             extendPropertyRepository.save(extendProperty);
                             return Boolean.TRUE;
                    }
               }
           }
            ExtendProperty extendProperty = c2p.apply(vo);
            extendProperty.setProductExtend(productExtendRepository.findOne(vo.getProductExtendId()));
            extendPropertyRepository.save(extendProperty);
            return Boolean.TRUE;
    }

    @Override
    public void updateExtendProperty(UpdateExtendPropertyVo updateExtendPropertyVo) throws ExtendPropertyNotFoundException {

    }

    @Override
    public boolean deleteExtendProperty(Long id) throws ExtendPropertyNotFoundException {
        return false;
    }

    @Override
    public void saveOrUpdateSort(ExtendPropertySortVo vo) {

            if(vo != null && vo.getExtendPropertySortListVos() != null){
                  List<ExtendProperty> extendProperties = Lists.newArrayList();
                  List<Long> extendPropertyIds = Lists.newArrayList();
                  Map<Long,Integer> extendPropertyIdsAndIdxs = new HashMap();
                  for(ExtendPropertySortListVo item:vo.getExtendPropertySortListVos()){
                      extendPropertyIds.add(item.getId());
                      extendPropertyIdsAndIdxs.put(item.getId(),item.getIdx());
                  }
                  extendProperties = extendPropertyRepository.findAll(extendPropertyIds);
                  if(extendProperties != null){
                      for(ExtendProperty extendProperty : extendProperties){
                          extendProperty.setIdx(extendPropertyIdsAndIdxs.get(extendProperty.getId()));
                      }
                  }
                extendPropertyRepository.save(extendProperties);
            }
    }

    @Override
    public List<String> listCategoryExtendPropertyName(Long categoryId) {
        return null;
    }

    @Override
    public List<ExtendSelectVo> selectExtend(Long categoryId, String productCode) {
        return null;
    }

    @Override
    public List<ExtendProperty> findByProductExtendId(Long productExtendId) {
        return extendPropertyRepository.findByProductExtendId(productExtendId);
    }

    @Override
    public ExtendProperty findById(Long id) {
        return extendPropertyRepository.findOne(id);
    }
}