package com.biz.support.es.esrepository.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.elasticsearch.ElasticsearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.MappingElasticsearchEntityInformation;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;

@NoRepositoryBean
public class CommonESRepositoryBean<T, ID extends Serializable> extends AbstractElasticsearchRepository<T, ID> {

    static final Logger logger = LoggerFactory.getLogger(CommonESRepositoryBean.class);

    public CommonESRepositoryBean(ElasticsearchEntityInformation<T, ID> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    public CommonESRepositoryBean(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.setEntityClass(entityClass);
        this.entityInformation = getEntityInformation(elasticsearchOperations, entityClass);
        try {
            if (createIndexAndMapping()) {
                createIndex();
                putMapping();
            }
        } catch (ElasticsearchException exception) {
            logger.error("failed to load elasticsearch nodes : " + exception.getDetailedMessage());
        }
    }

    public <T, ID extends Serializable> ElasticsearchEntityInformation<T, ID> getEntityInformation(ElasticsearchOperations elasticsearchOperations, Class<T> domainClass) {
        ElasticsearchPersistentEntity<T> persistentEntity = (ElasticsearchPersistentEntity<T>) elasticsearchOperations.getElasticsearchConverter().getMappingContext().getPersistentEntity(domainClass);
        Assert.notNull(persistentEntity, String.format("Unable to obtain mapping metadata for %s!", domainClass));
        Assert.notNull(persistentEntity.getIdProperty(), String.format("No id property found for %s!", domainClass));
        return new MappingElasticsearchEntityInformation<>(persistentEntity);
    }

    private void createIndex() {
        elasticsearchOperations.createIndex(getEntityClass());
    }

    private void putMapping() {
        elasticsearchOperations.putMapping(getEntityClass());
    }

    private boolean createIndexAndMapping() {
        return elasticsearchOperations.getPersistentEntityFor(getEntityClass()).isCreateIndexAndMapping();
    }

    @Override
    protected String stringIdRepresentation(ID id) {
        return id.toString();
    }

    public String getIndexName() {
        return this.entityInformation.getIndexName();
    }

    public String getType() {
        return this.entityInformation.getType();
    }

}
