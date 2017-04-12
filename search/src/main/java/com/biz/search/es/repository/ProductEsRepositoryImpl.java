package com.biz.search.es.repository;

import com.biz.search.es.entity.ProductEsEntity;
import com.biz.support.es.esrepository.common.CommonESRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

/**
 * 商品EsRepositoryImpl
 *
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
public class ProductEsRepositoryImpl extends CommonESRepositoryBean<ProductEsEntity, String> implements ProductEsDao {

    @Autowired
    public ProductEsRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
    }
}
