package com.biz.search.es.repository;

import com.biz.search.es.entity.ProductEsEntity;
import com.biz.support.es.esrepository.common.CommonESRepository;

/**
 * 商品 Es Repository
 *
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
public interface ProductEsRepository extends CommonESRepository<ProductEsEntity, String> {
}
