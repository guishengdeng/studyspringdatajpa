package com.biz.gbck.dao.mysql.repository.admin;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;



public class MainMenuRepositoryImpl extends CommonJpaRepositoryBean<MainMenu, String> implements MainMenuDao{

	@Autowired
	public MainMenuRepositoryImpl(EntityManager em) {
		super(MainMenu.class, em);
	}
	
	
}