package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.dao.mysql.po.security.Resource;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ResourceRepositoryImpl extends CommonJpaRepositoryBean<Resource, Long> implements MainMenuDao {

	@Autowired
	public ResourceRepositoryImpl(EntityManager em) {

		super(Resource.class, em);
	}

}