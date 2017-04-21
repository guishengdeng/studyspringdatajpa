package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.meta.ProductFilterItem;
import com.biz.gbck.dao.mysql.repository.productFilterItem.ProductFilterItemRepository;
import com.biz.gbck.dao.redis.repository.product.ProductFilterItemRedisDao;
import com.biz.service.AbstractBaseService;
import com.biz.gbck.transform.product.ProductFilterItem2ProductFilterItemRo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品过滤属性值Service抽象类
 *
 * @author david-liu
 * @date 2017年01月24日
 * @reviewer
 */
public class AbstractProductFilterItemService extends AbstractBaseService {

    @Autowired
    protected ProductFilterItemRepository productFilterItemRepository;

    @Autowired
    protected ProductFilterItemRedisDao productFilterItemRedisDao;

    protected void save(ProductFilterItem item) {
        this.saveOrUpdateUsingPo(productFilterItemRepository, productFilterItemRedisDao, item, new ProductFilterItem2ProductFilterItemRo());
    }

    protected void save(Iterable<ProductFilterItem> pos) {
        for (ProductFilterItem po : pos) {
            this.save(po);
        }
    }
}
