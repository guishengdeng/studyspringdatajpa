package com.biz.search.es.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Search Configuration
 *
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.biz.search")
public class SearchConfig {

}
