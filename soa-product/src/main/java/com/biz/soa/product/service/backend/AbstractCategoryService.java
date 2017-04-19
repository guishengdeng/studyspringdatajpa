package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.Category;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.redis.repository.product.CategoryRedisDao;
import com.biz.service.AbstractBaseService;
import com.biz.transform.product.Category2CategoryRo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 分类抽象Service
 *
 * @author david-liu
 * @date 2017年01月24日
 * @reviewer
 */
public class AbstractCategoryService extends AbstractBaseService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRedisDao categoryRedisDao;

    protected void saveCategory(Category category) {
        this.saveOrUpdateUsingPo(categoryRepository, categoryRedisDao, category, new Category2CategoryRo());
    }
}
