package com.biz.gbck.dao.mysql.repository.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */
public class AppRepositoryImpl extends CommonJpaRepositoryBean<App, Long> implements AppDao {
    @Autowired
    public AppRepositoryImpl(EntityManager em) {
        super(App.class, em);
    }
}
