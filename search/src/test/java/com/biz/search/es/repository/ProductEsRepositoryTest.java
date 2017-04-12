package com.biz.search.es.repository;

import com.biz.search.es.entity.ProductEsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
@RunWith(SpringRunner.class)
@EnableElasticsearchRepositories(basePackages = "com.biz.search")
@SpringBootTest
public class ProductEsRepositoryTest {

    @Autowired
    private ProductEsRepository productEsRepository;

    @Test
    public void testCreateEsEntityAndSave() {
        ProductEsEntity productEsEntity = new ProductEsEntity();
        productEsEntity.setId("this is the first");
        productEsEntity.setName("i am foo");
        this.productEsRepository.save(productEsEntity);
    }

}
