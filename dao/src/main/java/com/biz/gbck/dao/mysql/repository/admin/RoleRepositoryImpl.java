package com.biz.gbck.dao.mysql.repository.admin;


import com.biz.gbck.dao.mysql.po.security.Role;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;


public class RoleRepositoryImpl extends CommonJpaRepositoryBean<Role, String> implements RoleDao {

    @Autowired
    public RoleRepositoryImpl(EntityManager em) {
        super(Role.class, em);
    }


}
