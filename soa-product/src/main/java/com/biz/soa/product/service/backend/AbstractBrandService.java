package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.repository.brand.BrandRepository;
import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.gbck.dao.redis.repository.product.BrandRedisDao;
import com.biz.service.AbstractBaseService;
import com.biz.transform.product.Brand2BrandRo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌Service抽象类
 *
 * @author david-liu
 * @date 2017年01月24日
 * @reviewer
 */
public class AbstractBrandService extends AbstractBaseService {

    @Autowired
    protected BrandRepository brandRepository;

    @Autowired
    private BrandRedisDao brandRedisDao;

    protected void saveBrand(Brand brand) {
        this.saveOrUpdateUsingPo(brandRepository, brandRedisDao, brand, new Brand2BrandRo());
    }

    protected void saveBrands(Iterable<Brand> brands) {
        for (Brand brand : brands) {
            this.saveBrand(brand);
        }
    }

}
