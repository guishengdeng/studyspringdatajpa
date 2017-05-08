package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by xys on 2017/5/8.
 */
public class RegionRepositoryImpl extends CommonJpaRepositoryBean<RegionPo, Integer> implements RegionDao {

    @Autowired
    public RegionRepositoryImpl(EntityManager em) {
        super(RegionPo.class, em);
    }
}
