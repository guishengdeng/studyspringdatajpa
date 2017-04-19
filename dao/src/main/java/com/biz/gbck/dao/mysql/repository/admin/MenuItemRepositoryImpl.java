package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class MenuItemRepositoryImpl extends CommonJpaRepositoryBean<MenuItem, Long> implements MainMenuDao {

	@Autowired
	public MenuItemRepositoryImpl(EntityManager em) {

		super(MenuItem.class, em);
	}

}