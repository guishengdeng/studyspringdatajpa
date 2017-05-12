package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.meta.ProductExtend;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.productExtend.ProductExtendRepository;
import com.biz.gbck.exceptions.product.ProductExtendNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ProductExtendService;
import com.biz.gbck.transform.product.CreateCategoryPropertyVo2ProductExtend;
import com.biz.gbck.transform.product.ProductExtend2CategoryPropertyListItemVo;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分类属性 ServiceImpl(后台用)
 *
 * @author wangyumin
 * @date 2016年12月27日
 */
@Service
public class ProductExtendServiceImpl extends AbstractBaseService implements ProductExtendService {

    @Autowired
    private ProductExtendRepository productExtendRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 根据分类id，属性名，获取该分类下的含有该属性名的所有属性
     * 1.从数据库获取该分类下的含有该属性名的所有属性list
     * 2.将获取到的list转换为BootstrapTableResult并返回
     *
     * @return 该分类下含有该属性名的所有属性
     * @author wangyumin
     */
    @Override
    public BootstrapTablePageResult<CategoryPropertyListItemVo> listCategoryProperties(SearchPageVo searchPageVo,
                                                                                       Long categoryId) throws ProductExtendNotFoundException {
        List<ProductExtend> list = productExtendRepository.findByCategoryIdAndNameLikeAndDeleteFlag(categoryId, searchPageVo.getSearchValue(), Boolean.FALSE);
        BootstrapTablePageResult<CategoryPropertyListItemVo> pageResult = new BootstrapTablePageResult<>();
        pageResult.setRows(Lists.transform(list, new ProductExtend2CategoryPropertyListItemVo()));
        pageResult.setTotal((int) list.size());
        return pageResult;
    }

    /**
     * 根据属性id删除分类属性
     * 1.从数据库中获取该属性id的对象
     * 2.判断该对象是否为空，若为空，抛出异常后返回真
     * 3.判断该对象的删除标识，若已被删除，返回真
     * 4.如果不为空且未被删除，则将该对象的删除标识设为真
     * 5.保存该对象并返回真
     *
     * @param productExtendid 属性id，必须传入
     * @return 删除是否成功布尔
     * @author wangyumin
     */
    @Override
    @Transactional
    public boolean deleteProductExtend(Long productExtendid) throws ProductExtendNotFoundException {
        ProductExtend productExtend = productExtendRepository.findOne(productExtendid);
        /*删除的属性不存在，抛异常*/
        if (null == productExtend) {
            throw new ProductExtendNotFoundException("被删除的属性不存在");
        }
        if (productExtend.getDeleteFlag() == Boolean.TRUE) {
            return Boolean.TRUE;
        }
        productExtend.setDeleteFlag(Boolean.TRUE);
        productExtendRepository.save(productExtend);
        return Boolean.TRUE;
    }

    /**
     * 新建分类属性
     * 1.为新建属性vo构建id
     * 2.从数据库中获取该分类下的属性排序的最大值
     * 3.如果最大值为空，则设置新建属性vo的排序值为1
     * 4.如果最大值不为空，则设置新建属性vo的排序值为最大值加1
     * 5.将新建属性vo转换为po
     * 6.将转换后的po保存
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void createCategoryProperty(CreateCategoryPropertyVo createCategoryPropertyVo) {
        createCategoryPropertyVo.setId(idService.nextId());
        Integer idx = productExtendRepository.findMaxIdx(createCategoryPropertyVo.getCategoryId());
        if (idx == null) {
            idx = 1;
        } else {
            idx += 1;
        }
        createCategoryPropertyVo.setIdx(idx);
        CreateCategoryPropertyVo2ProductExtend c2p = new CreateCategoryPropertyVo2ProductExtend();
        ProductExtend productExtend = c2p.apply(createCategoryPropertyVo);
        Category category = categoryRepository.findOne(createCategoryPropertyVo.getCategoryId());
        productExtend.setCategory(category);
        productExtendRepository.save(productExtend);
    }

    /**
     * 根据分类属性id获取修改分类属性vo
     * 1.由分类属性id获取该分类属性po
     * 2.将po转换为修改分类属性vo
     * 3.将转换后的分类属性vo返回
     *
     * @return 修改分类属性vo
     * @author wangyumin
     */
    @Override
    public EditCategoryPropertyVo getEditCategoryPropertyVo(Long id) throws ProductExtendNotFoundException {
        ProductExtend editProductExtend = productExtendRepository.findOne(id);
        EditCategoryPropertyVo result = new EditCategoryPropertyVo();
        result.setId(editProductExtend.getId());
        result.setName(editProductExtend.getName());
        result.setStatus(editProductExtend.getStatus());
        return result;
    }

    /**
     * 修改分类属性
     * 1.根据传入的修改分类属性vo的id获取该分类属性的po
     * 2.如果获取得po为空，则抛出异常
     * 3.如果po不为空，则修改此分类属性po的名称和状态
     * 4.将该修改过的分类属性po存入数据库
     * 5.记录日志
     *
     * @author wangyumin
     */
    @Override
    @Transactional
    public void editCategoryProperty(EditCategoryPropertyVo editCategoryPropertyVo) throws ProductExtendNotFoundException {
        //根据vo的id获得productExtend(未删除的)
        ProductExtend productExtend = productExtendRepository.findByIdAndDeleteFlag(editCategoryPropertyVo.getId(), Boolean.FALSE);
        if (productExtend == null) {
            throw new ProductExtendNotFoundException("被编辑的属性不存在");
        }
        productExtend.setName(editCategoryPropertyVo.getName());
        productExtend.setStatus(editCategoryPropertyVo.getStatus());
        productExtend = productExtendRepository.save(productExtend);
        if (logger.isDebugEnabled()) {
            logger.debug("po after save: {}", productExtend.toString());
        }
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
        List<ProductExtend> productExtends = productExtendRepository.findAll();
        List<AllProductExtendVo> vos = Lists.newArrayList();
        for (int i = 0; i < productExtends.size(); ++i) {
            AllProductExtendVo vo = new AllProductExtendVo();
            vo.setCategoryId(productExtends.get(i).getCategory().getId());
            vo.setProductExtendId(productExtends.get(i).getId());
            vo.setName(productExtends.get(i).getName());
            vos.add(vo);
        }
        return vos;
    }

	@Override
	public List<ProductExtend> findByCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductExtend findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductExtend> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductExtendVo> productExtend2ProductExtendVo(List<ProductExtend> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isExistProductExtendName(CreateCategoryPropertyVo vo) throws ProductExtendNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
