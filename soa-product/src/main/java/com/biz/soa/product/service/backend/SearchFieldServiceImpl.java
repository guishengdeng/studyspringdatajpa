package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.redis.repository.product.CategoryRedisDao;
import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.FieldListItemVo;
import com.biz.service.product.backend.ExtendPropertyService;
import com.biz.service.product.backend.SearchFieldService;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/21
 */
public class SearchFieldServiceImpl implements SearchFieldService {

    @Autowired
    private CategoryRedisDao categoryRedisDao;

    @Autowired
    private ExtendPropertyService extendPropertyService;

    @Override
    public BootstrapTablePageResult<FieldListItemVo> listField(Long categoryId) {
        if (categoryId == null) {
            categoryId = 0l;
        }
        Map<String, Integer> fieldsWithIdx = categoryRedisDao.listCategoryFieldsWithIdx(categoryId);
        Iterator<Map.Entry<String, Integer>> iterator = fieldsWithIdx.entrySet().iterator();
        List<FieldListItemVo> resultList = Lists.newArrayList();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> fieldNameAndIdxMap = iterator.next();
            FieldListItemVo fieldListItemVo = new FieldListItemVo(String.valueOf(categoryId), fieldNameAndIdxMap.getKey(), fieldNameAndIdxMap.getValue());
            resultList.add(fieldListItemVo);
        }
        BootstrapTablePageResult<FieldListItemVo> pageResult = new BootstrapTablePageResult<>();
        pageResult.setRows(resultList);
        return pageResult;
    }

    @Override
    public FieldListItemVo saveOrUpdateField(FieldListItemVo fieldListItemVo) {
        if (fieldListItemVo.isValid()) {
            categoryRedisDao.saveOrUpdateFild(fieldListItemVo);
            return fieldListItemVo;
        } else {
            throw new RuntimeException("参数不合法");
        }
    }

    @Override
    public void deleteField(Long categoryId, String fieldName) {
        if (categoryId == null) {
            categoryId = 0l;
        }
        categoryRedisDao.deleteField(categoryId, fieldName);
    }

    @Override
    public List<String> getSelectField(Long categoryId) {
        List<String> result = Lists.newArrayList();
        result.add("价格");
        result.add("品牌");
        result.addAll(extendPropertyService.listCategoryExtendPropertyName(categoryId));
        return result;
    }

    @Override
    public List<String> getFields(Long categoryId) {
        if (categoryId == null) {
            //            全部分类
            categoryId = 0l;
        }
        List<String> result = categoryRedisDao.listCategoryFields(categoryId);
        return result;
    }
}
