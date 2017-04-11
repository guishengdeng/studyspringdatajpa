package com.biz.support.es.esrepository.common;

import java.io.Serializable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonESRepository<T, ID extends Serializable> extends ElasticsearchRepository<T, ID> {
}

