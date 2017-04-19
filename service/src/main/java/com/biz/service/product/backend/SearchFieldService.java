package com.biz.service.product.backend;

import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.FieldListItemVo;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/21
 */
public interface SearchFieldService {

    /**
     * 列出当前分类所有field
     */
    BootstrapTablePageResult<FieldListItemVo> listField(Long categoryId);

    /**
     * 更新或添加field到指定分类
     */
    FieldListItemVo saveOrUpdateField(FieldListItemVo fieldListItemVo);

    /**
     * 删除所选分类中的field
     */
    void deleteField(Long categoryId, String fieldName);

    /**
     * 获取可选的field select
     */
    List<String> getSelectField(Long categoryId);

    List<String> getFields(Long categoryId);
}
