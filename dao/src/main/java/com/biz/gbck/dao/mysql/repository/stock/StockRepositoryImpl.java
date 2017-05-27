package com.biz.gbck.dao.mysql.repository.stock;

import com.biz.gbck.dao.mysql.po.stock.CompanyStock;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

/**
 * Created by lzz on 2017/5/17.
 */
@Repository
public class StockRepositoryImpl extends CommonJpaRepositoryBean<CompanyStock,Long> implements StockDao{
    @Autowired
    public StockRepositoryImpl(EntityManager em) {
        super(CompanyStock.class, em);
    }
}
