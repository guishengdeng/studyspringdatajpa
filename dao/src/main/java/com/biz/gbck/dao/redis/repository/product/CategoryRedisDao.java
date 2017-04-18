package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.CategoryRo;
import com.biz.gbck.vo.product.backend.FieldListItemVo;
import com.biz.gbck.vo.product.backend.ProductSalesVo;
import com.biz.gbck.vo.product.frontend.CategoryProductSalesTopVo;
import com.biz.redis.util.RedisUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Tuple;

/**
 * 分类 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class CategoryRedisDao extends CrudRedisDao<CategoryRo, Long> implements Serializable {

    private static final long serialVersionUID = 1009646058514234622L;


    /**
     * 获取category 配置field的 SortSetKey
     *
     * @param categoryId 分类Id
     * @return String Key
     */
    private String getcategoryFiledSortSetKey(Long categoryId) {
        return getKeyByParams("category", categoryId, "field");
    }

    public Map<String, Integer> listCategoryFieldsWithIdx(Long categoryId) {
        Set<Tuple> resultSet = zrangeWithScores(getcategoryFiledSortSetKey(categoryId), 0, -1);
        Map<String, Integer> mapResult = new HashMap<>();
        for (Tuple tuple : resultSet) {
            mapResult.put(RedisUtil.byteArrayToStr(tuple.getBinaryElement()), (int) tuple.getScore());
        }
        return mapResult;
    }

    public List<String> listCategoryFields(Long categoryId) {
        Set<byte[]> resultSets = zRange(getcategoryFiledSortSetKey(categoryId), 0, -1);
        return RedisUtil.bytesSetToStringList(resultSets);
    }

    public void saveOrUpdateFild(FieldListItemVo fieldListItemVo) {
        zadd(getcategoryFiledSortSetKey(Long.valueOf(fieldListItemVo.getCategoryId())), fieldListItemVo.getIdx(), RedisUtil.toByteArray(fieldListItemVo.getFieldName()));
    }

    public void deleteField(Long categoryId, String fieldName) {
        zrem(getcategoryFiledSortSetKey(categoryId), RedisUtil.toByteArray(fieldName));
    }

    /**
     * 获取分类TopN sortSet Key
     */
    public String getCategorySalesTopSortSetKey(Long categoryId) {
        return this.getKeyByParams(categoryId, "TopProduct");
    }


    /**
     * 设置topN分类排行商品
     */
    public void setCategorySalesTopN(List<CategoryProductSalesTopVo> lists) {
        for (CategoryProductSalesTopVo index : lists) {
            for (ProductSalesVo productSalesVo : index.getProductSalesVos()) {
                zincrby(getCategorySalesTopSortSetKey(index.getCategoryId()), productSalesVo.getQuantity(),
                        RedisUtil.toByteArray(productSalesVo.getProductCode()));
            }
        }
    }

    public List<String> getCategorySalesTopN(Long categoryId, Integer size) {
        Set<byte[]> set = zrevrange(getCategorySalesTopSortSetKey(categoryId), 0, -1);
        List<String> resultList = RedisUtil.bytesSetToStringList(set);
        if (resultList != null && resultList.size() > size * 2) {
            resultList = resultList.subList(0, size * 2);
        }
        return resultList;
    }

    public static void main(String[] args) {
        CategoryRedisDao categoryRedisDao = new CategoryRedisDao();
        String string = categoryRedisDao.getCategorySalesTopSortSetKey(323651698197401600L);
        System.out.println(string);
    }

}
