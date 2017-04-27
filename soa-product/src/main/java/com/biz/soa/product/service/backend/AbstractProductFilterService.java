package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.meta.ProductFilter;
import com.biz.gbck.dao.mysql.repository.productFilter.ProductFilterRepository;
import com.biz.gbck.dao.redis.repository.product.ProductFilterRedisDao;
import com.biz.gbck.transform.product.ProductFilter2ProductFilterRo;
import com.biz.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品过滤条件Service抽象类
 *
 * @author david-liu
 * @date 2017年01月24日
 * @reviewer
 */
public class AbstractProductFilterService extends AbstractBaseService {

    @Autowired
    protected ProductFilterRepository productFilterRepository;

    @Autowired
    protected ProductFilterRedisDao productFilterRedisDao;

    protected void saveProductFilter(ProductFilter po) {
        this.saveOrUpdateUsingPo(productFilterRepository, productFilterRedisDao, po, new ProductFilter2ProductFilterRo());
    }

    protected void saveProductFilter(Iterable<ProductFilter> pos) {
        pos.forEach(this::saveProductFilter);
    }
}
