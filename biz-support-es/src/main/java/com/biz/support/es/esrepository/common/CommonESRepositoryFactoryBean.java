package com.biz.support.es.esrepository.common;

import java.io.Serializable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

public class CommonESRepositoryFactoryBean<T extends ElasticsearchRepository<Object, Serializable>> extends ElasticsearchRepositoryFactoryBean<T, Object, Serializable> {

    private ElasticsearchOperations operations;

    public CommonESRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    public void setElasticsearchOperations(ElasticsearchOperations operations) {
        Assert.notNull(operations, "Elasticsearch Operations can not be null.");
        setMappingContext(operations.getElasticsearchConverter().getMappingContext());
        this.operations = operations;
        super.setElasticsearchOperations(operations);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        return new CommonESRepositoryFactory(operations);
    }

}
