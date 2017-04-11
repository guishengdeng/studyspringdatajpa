package com.biz.support.es.esrepository.common;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactory;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;

public class CommonESRepositoryFactory extends ElasticsearchRepositoryFactory {


    private ElasticsearchOperations elasticsearchOperations;

    public CommonESRepositoryFactory(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
        this.elasticsearchOperations = elasticsearchOperations;
    }


    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Object getTargetRepository(RepositoryInformation metadata) {
        return getTargetRepositoryViaReflection(metadata, getEntityInformation(metadata.getDomainType()), elasticsearchOperations);
    }

    /**
     * copy from super class
     *
     * @author shutao.gong
     * @date 2016年12月23日
     */
    private static boolean isQueryDslRepository(Class<?> repositoryInterface) {
        return QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
    }


    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isQueryDslRepository(metadata.getRepositoryInterface())) {
            throw new IllegalArgumentException("QueryDsl Support has not been implemented yet.");
        }
        if (Integer.class.isAssignableFrom(metadata.getIdType())
                || Long.class.isAssignableFrom(metadata.getIdType())
                || Double.class.isAssignableFrom(metadata.getIdType())
                || metadata.getIdType() == String.class) {
            return CommonESRepositoryBean.class;
        } else {
            throw new IllegalArgumentException("Unsupported ID type " + metadata.getIdType());
        }
    }
}
