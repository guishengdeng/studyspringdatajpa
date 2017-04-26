package com.biz.gbck.dao.mysql.repository.org;



import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ShopTypeRepositoryImpl extends CommonJpaRepositoryBean<ShopTypePo, Long> implements ShopTypeDao {

    @Autowired
    public ShopTypeRepositoryImpl(EntityManager em) {
        super(ShopTypePo.class, em);
    }



}
