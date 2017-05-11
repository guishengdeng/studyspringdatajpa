package com.biz.service.product.backend;


import com.biz.gbck.dao.mysql.po.product.meta.ProductExtend;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.backend.*;

import java.util.List;

/**
 * 商品模块分类属性service
 *
 * @author wangyumin
 * @date 2016年12月27日
 */
public interface ProductExtendService {

    /**
     * 当前分类下返回所有的分类属性
     *
     * @return 商品分类列表
     */
    BootstrapTablePageResult<CategoryPropertyListItemVo> listCategoryProperties(SearchPageVo searchPageVo, Long categoryId) throws DepotNextDoorException;

    /**
     * 增加一个分类下的属性
     */
    void createCategoryProperty(CreateCategoryPropertyVo createCategoryPropertyVo) throws DepotNextDoorException;


    /**
     * 获得修改的Vo
     *
     * EditCategoryPropertyVo 修改分类属性Vo
     */
    EditCategoryPropertyVo getEditCategoryPropertyVo(Long productExtendId) throws DepotNextDoorException;


    /**
     * 修改属性
     */
    void editCategoryProperty(EditCategoryPropertyVo editCategoryPropertyVo) throws DepotNextDoorException;

    /**
     * 删除分类的一个属性
     *
     * @return true:删除成功, false:删除失败
     */
    boolean deleteProductExtend(Long productExtendId) throws DepotNextDoorException;

    /**
     * 保存排序
     */
    void saveOrUpdateSort(PropertySortVo vo);

    /**
     * 返回所有分类属性
     */
    List<AllProductExtendVo> findAllProductExtend();


    /**
     * 根据分类id，返回与之相关的扩展属性
     */
    List<ProductExtend> findByCategoryId(Long id);


    /**
     * 根据id查询扩展属性
     */
    ProductExtend findOne(Long id);

    List<ProductExtend> findAll();

    /**
     * 将po转化成vo
     * @param list
     * @return
     */
    public List<ProductExtendVo> productExtend2ProductExtendVo(List<ProductExtend> list);

    /**
     * 根据用户传入的categoryid和属性名来进行判断。如果用户输入
     * 的属性名和根据categoryid查询出来的扩展属性的属性名相等,则返回fasle
     * 说明用户输入的属性名已存在,否则则返回true,（ajax请求）返回true之后,在发送一次请求。同步请求
     */
    Boolean  isExistProductExtendName(CreateCategoryPropertyVo vo) throws DepotNextDoorException;
}
