package com.biz.gbck.dao.mysql.repository.stock;

import com.biz.gbck.dao.mysql.po.stock.CompanyStock;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

/**
 * Created by lzz on 2017/5/17.
 */
public interface StockRepository extends JpaSpecificationExecutor<CompanyStock>, CommonJpaRepository<CompanyStock, Long>, StockDao {
    Page<CompanyStock> findByCompanyIdAndProductIdIn(Long companyId, Set<Long> productIds, Pageable pageable);
}
