package com.biz.gbck.dao.mysql.repository.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by lzz on 2017/5/17.
 */
@Repository
public class StockRepositoryImpl extends CommonJpaRepositoryBean<Stock,Long> implements StockDao{

    @Autowired
    public StockRepositoryImpl(EntityManager em) {
        super(Stock.class, em);
    }

    List<StockShowVo> findList(){
        Query query = getEntityManager().createNativeQuery(
                "SELECT op.id AS id, op.name AS name, op.product_code AS productCode, op.brand_id AS brand,"
                        + "op.standard AS standard, op.category AS category FROM pro_product op LEFT JOIN sto_stock sto ON sto.product_id = op.id",
                "StockBackenkBinding");

        return query.getResultList();
    }
}
