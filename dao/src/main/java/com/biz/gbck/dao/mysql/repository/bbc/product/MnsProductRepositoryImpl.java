package com.biz.gbck.dao.mysql.repository.bbc.product;

import com.biz.gbck.dao.mysql.po.product.bbc.MnsProduct;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangcheng
 * @date 2017/1/5
 * @reviewer
 * @see
 */
public class MnsProductRepositoryImpl extends CommonJpaRepositoryBean<MnsProduct, Long> implements MnsProductDao {

    @Autowired
    public MnsProductRepositoryImpl(EntityManager em) {
        super(MnsProduct.class, em);
    }
}
