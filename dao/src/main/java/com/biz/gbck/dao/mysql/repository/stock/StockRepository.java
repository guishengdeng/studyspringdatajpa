package com.biz.gbck.dao.mysql.repository.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lzz on 2017/5/17.
 */
public interface StockRepository extends JpaSpecificationExecutor<Stock> ,CommonJpaRepository<Stock,Long> {
}
