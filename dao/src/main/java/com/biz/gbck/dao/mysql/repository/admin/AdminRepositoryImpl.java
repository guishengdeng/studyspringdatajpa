package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;


public class AdminRepositoryImpl extends CommonJpaRepositoryBean<Admin, String> implements AdminDao {

    @Autowired
    public AdminRepositoryImpl(EntityManager em) {
        super(Admin.class, em);
    }


}