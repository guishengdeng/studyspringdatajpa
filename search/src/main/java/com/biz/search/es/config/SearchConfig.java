package com.biz.search.es.config;

import com.biz.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment environment;

    @Bean
    public IdService idService() {
        Integer soaIdx = Integer.valueOf(environment.getProperty("biz.soa.idx"));
        String soaIdxZooNodePath = environment.getProperty("biz.zookeeper.soaIdx.path");
        String zookeeperUrl = environment.getProperty("biz.zookeeper.url");
        return new IdService(soaIdx, soaIdxZooNodePath, zookeeperUrl);
    }

}
