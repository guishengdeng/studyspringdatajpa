package com.biz.service.product.backend;


import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.exceptions.product.ExtendPropertyNotFoundException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.product.backend.*;
import java.util.List;

/**
 * @author wangyumin
 * @date 2016年12月29日
 */
public interface ExtendPropertyService {

    /**
     * 当前属性的所有值
     *
     * @param productExtendId 属性ID
     * @return 属性值分类列表
     */
    BootstrapTablePageResult<ExtendPropertyListItemVo> listExtendProperties(Long productExtendId) throws IllegalParameterException, ExtendPropertyNotFoundException;

    /**
     * param id 修改的属性值ID
     * 获得要修改的属性值的VO
     */
    UpdateExtendPropertyVo getUpdateExtendPropertyVo(Long id) throws ExtendPropertyNotFoundException;

    /**
     * 添加一个属性值
     *
     * @param createExtendPropertyVo 添加属性值VO
     */
    void createExtendProperty(CreateExtendPropertyVo createExtendPropertyVo) throws ExtendPropertyNotFoundException;

    /**
     * 修改一个属性值
     *
     * @param updateExtendPropertyVo 修改属性值VO
     */
    void updateExtendProperty(UpdateExtendPropertyVo updateExtendPropertyVo) throws ExtendPropertyNotFoundException;

    /**
     * 删除一个属性值
     *
     * @param id 删除的属性值的ID
     * @return 成功与否
     */
    boolean deleteExtendProperty(Long id) throws ExtendPropertyNotFoundException;

    /**
     * 属性值保存排序
     */
    void saveOrUpdateSort(ExtendPropertySortVo vo);

    /**
     * 获取对应分类下的扩展属性名称
     */
    List<String> listCategoryExtendPropertyName(Long categoryId);

    /**
     * 商品扩展属性列表
     */
    List<ExtendSelectVo> selectExtend(Long categoryId, String productCode);

    /**
     * 根据商品扩展id,查询扩展属性
     */

    List<ExtendProperty> findByProductExtendId(Long productExtendId);

    /***
     * 根据id查询扩展属性
     */
    ExtendProperty  findById(Long id);

    Boolean  isExistExtendPropertyValue(CreateExtendPropertyVo vo) throws ExtendPropertyNotFoundException;
}
