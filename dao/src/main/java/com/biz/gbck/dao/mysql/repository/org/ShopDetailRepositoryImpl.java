package com.biz.gbck.dao.mysql.repository.org;


import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


/**
 * Created by J on 2016/3/28.
 */
@Repository("ShopDetailRepositoryImpl")
public class ShopDetailRepositoryImpl extends CommonJpaRepositoryBean<ShopDetailPo, Long>  {


    @Autowired
    ShopTypeRepository shopTypeRepository;

    @Autowired
    ShopPoRepository shopPoRepository;



    @Autowired
    public ShopDetailRepositoryImpl(EntityManager entityManager) {
        super(ShopDetailPo.class, entityManager);
    }



}

