package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.dao.mysql.po.product.meta.ProductExtend;
import com.biz.gbck.dao.mysql.repository.extendProperty.ExtendPropertyRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.mysql.repository.productExtend.ProductExtendRepository;
import com.biz.gbck.exceptions.product.ExtendPropertyNotFoundException;
import com.biz.gbck.transform.product.CreateExtendPropertyVo2ExtendProperty;
import com.biz.gbck.transform.product.ExtendProperty2CategoryPropertyListItemVo;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ExtendPropertyService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangyumin
 * @date 2016年12月29日
 */
@Service
public class ExtendPropertyServiceImpl extends AbstractBaseService implements ExtendPropertyService {

    @Autowired
    private ExtendPropertyRepository extendPropertyRepository;

    @Autowired
    private ProductExtendRepository productExtendRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * 根据分类属性id获取该属性下的所有属性值
     * 1.根据分类属性id获得该属性下的所有属性值list
     * 2.将该list转换为BootstrapTablePageResult对象
     * 3.返回BootstrapTablePageResult对象
     *
     * @return 所有属性值的BootStrapTablePageResult
     * @author wangyumin
     */
    @Override
    public BootstrapTablePageResult<ExtendPropertyListItemVo> listExtendProperties(Long productExtendId) throws ExtendPropertyNotFoundException {
        List<ExtendProperty> list = extendPropertyRepository.findByProductExtendIdAndDeleteFlagOrderByIdx(productExtendId, Boolean.FALSE);
        BootstrapTablePageResult<ExtendPropertyListItemVo> pageResult = new BootstrapTablePageResult<>();
        pageResult.setRows(Lists.transform(list, new ExtendProperty2CategoryPropertyListItemVo()));
        pageResult.setTotal(list.size());
        return pageResult;
    }

    /**
     * 获得修改属性值vo
     * 1.根据属性值id获得属性值po
     * 2.将获得的属性值po转换成修改属性值vo
     * 3.返回修改属性值vo
     *
     * @return UpdateExtendPropertyVo修改属性值vo
     * @author wangyumin
     */
    @Override
    @Transactional
    public UpdateExtendPropertyVo getUpdateExtendPropertyVo(Long id) throws ExtendPropertyNotFoundException {
        ExtendProperty extendProperty = extendPropertyRepository.findOne(id);
        UpdateExtendPropertyVo updateExtendPropertyVo = new UpdateExtendPropertyVo();
        updateExtendPropertyVo.setId(extendProperty.getId());
        updateExtendPropertyVo.setValue(extendProperty.getValue());
        updateExtendPropertyVo.setStatus(extendProperty.getStatus());
        return updateExtendPropertyVo;
    }

    /**
     * 新建属性值
     * 1.为新建属性值vo构造id
     * 2.将新建属性值vo转换为属性值po
     * 3.获得当前属性下的属性值得最大排序值
     * 4.如果排序值为空，则设置属性值po为1
     * 5.如果不为空，则设置属性值po为最大排序值加1
     * 6.保存该属性值po
     *
     * @param createExtendPropertyVo 新建属性值vo
     * @author wangyumin
     */
    @Override
    @Transactional
    public void createExtendProperty(CreateExtendPropertyVo createExtendPropertyVo) {
        createExtendPropertyVo.setId(idService.nextId());
        CreateExtendPropertyVo2ExtendProperty c2e = new CreateExtendPropertyVo2ExtendProperty();
        ExtendProperty extendProperty = c2e.apply(createExtendPropertyVo);
        Preconditions.checkArgument(extendProperty != null);
        extendProperty.setProductExtend(productExtendRepository.findOne(createExtendPropertyVo.getProductExtendId()));
        Integer idx = extendPropertyRepository.findMaxIdx(createExtendPropertyVo.getProductExtendId());
        if (idx == null) {
            idx = 1;
        } else {
            idx += 1;
        }
        extendProperty.setIdx(idx);
        extendPropertyRepository.save(extendProperty);
    }

    /**
     * 修改属性值
     * 1.根据属性值id获取属性值po
     * 2.如果获取的属性值po为空，抛出异常
     * 3.设置该属性值po的值及状态
     * 4.保存该属性值po的修改
     * 5.记录日志
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void updateExtendProperty(UpdateExtendPropertyVo updateExtendPropertyVo) throws ExtendPropertyNotFoundException {
        ExtendProperty extendProperty = extendPropertyRepository.findByIdAndDeleteFlag(updateExtendPropertyVo.getId(), Boolean.FALSE);
        if (extendProperty == null) {
            throw new ExtendPropertyNotFoundException("被编辑的属性值不存在");
        }
        extendProperty.setValue(updateExtendPropertyVo.getValue());
        extendProperty.setStatus(updateExtendPropertyVo.getStatus());
        extendPropertyRepository.save(extendProperty);
        if (logger.isDebugEnabled()) {
            logger.debug("po after update: {}", extendProperty.toString());
        }
    }

    /**
     * 删除属性值
     * 1.根据属性值id获得需要删除的属性值po
     * 2.如果属性值po不存在，抛出异常
     * 3.如果属性值po的删除标识为真，则返回假
     * 4.如果既不为空且删除标识为假，则将该po的删除标识设置为假
     * 5.保存该修改并返回真
     *
     * @return 是否删除成功的Boolean
     */
    @Override
    @Transactional
    public boolean deleteExtendProperty(Long id) throws ExtendPropertyNotFoundException {
        ExtendProperty extendProperty = extendPropertyRepository.findOne(id);
        /*删除的属性值不存在，抛异常*/
        if (null == extendProperty) {
            throw new ExtendPropertyNotFoundException("被删除的属性值不存在");
        }
        if (extendProperty.getDeleteFlag() == Boolean.TRUE) {
            return Boolean.FALSE;
        }
        extendProperty.setDeleteFlag(Boolean.TRUE);
        extendPropertyRepository.save(extendProperty);
        return Boolean.TRUE;
    }

    @Override
    public void saveOrUpdateSort(ExtendPropertySortVo vo) {
        if (vo != null && vo.getExtendPropertySortListVos() != null) {
            List<ExtendProperty> extendPropertys = Lists.newArrayList();
            List<Long> extendPropertyIds = Lists.newArrayList();
            Map<Long, Integer> extendPropertyIdAndIdx = new HashMap<>();
            for (ExtendPropertySortListVo index : vo.getExtendPropertySortListVos()) {
                extendPropertyIds.add(index.getId());
                extendPropertyIdAndIdx.put(index.getId(), index.getIdx());
            }
            extendPropertys = extendPropertyRepository.findAll(extendPropertyIds);
            for (ExtendProperty extendProperty : extendPropertys) {
                extendProperty.setIdx(extendPropertyIdAndIdx.get(extendProperty.getId()));
            }
            extendPropertyRepository.save(extendPropertys);
        } else {
            logger.warn("排序 vo 或排序 list 为 null,排序失败");
        }
    }

    @Override
    public List<String> listCategoryExtendPropertyName(Long categoryId) {
        List<ProductExtend> productExtends;
        if (categoryId == 0) {
            productExtends = productExtendRepository.findAll();
        } else {
            productExtends = productExtendRepository.findByCategoryId(categoryId);
        }
        Set<String> resultList = Sets.newHashSet();
        for (ProductExtend productExtend : productExtends) {
            resultList.add(productExtend.getName());
        }
        return Lists.newArrayList(resultList);
    }

    @Override
    public List<ExtendSelectVo> selectExtend(Long categoryId, String productCode) {
        List<Long> selectPropertyIds = Lists.newArrayList();
        if (StringUtils.isNotBlank(productCode)) {
            Product product = productRepository.findByProductCode(productCode);
            List<ExtendProperty> selectProperty = product.getProperties();
            for (ExtendProperty extendProperty : selectProperty) {
                selectPropertyIds.add(extendProperty.getId());
            }
        }


        List<ExtendSelectVo> extendSelectVos = Lists.newArrayList();
        List<ProductExtend> productExtends = productExtendRepository.findByCategoryId(categoryId);
        if (CollectionUtils.isNotEmpty(productExtends)) {
            for (ProductExtend productExtend : productExtends) {
                ExtendSelectVo extendSelectVo = new ExtendSelectVo();
                extendSelectVo.setExtendId(String.valueOf(productExtend.getId()));
                extendSelectVo.setExtendName(productExtend.getName());
                List<ExtendPropertyVo> extendPropertyVos = Lists.newArrayList();
                for (ExtendProperty extendProperty : productExtend.getProperties()) {
                    ExtendPropertyVo extendPropertyVo = new ExtendPropertyVo();
                    extendPropertyVo.setId(String.valueOf(extendProperty.getId()));
                    extendPropertyVo.setValue(extendProperty.getValue());
                    if (selectPropertyIds.contains(extendProperty.getId())) {
                        extendPropertyVo.setSelected(true);
                    } else {
                        extendPropertyVo.setSelected(false);
                    }
                    extendPropertyVos.add(extendPropertyVo);
                }
                extendSelectVo.setExtendPropertyVos(extendPropertyVos);
                extendSelectVos.add(extendSelectVo);
            }
        }
        return extendSelectVos;
    }

	@Override
	public List<ExtendProperty> findByProductExtendId(Long productExtendId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtendProperty findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isExistExtendPropertyValue(CreateExtendPropertyVo vo) throws ExtendPropertyNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
