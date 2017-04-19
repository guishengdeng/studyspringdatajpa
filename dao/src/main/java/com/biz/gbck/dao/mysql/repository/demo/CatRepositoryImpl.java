package com.biz.gbck.dao.mysql.repository.demo;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class CatRepositoryImpl extends CommonJpaRepositoryBean<CatPO, Long> implements CatDao {

	@Autowired
	public CatRepositoryImpl(EntityManager em) {

		super(CatPO.class, em);
	}

}
