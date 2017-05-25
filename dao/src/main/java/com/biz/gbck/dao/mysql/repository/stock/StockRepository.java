package com.biz.gbck.dao.mysql.repository.stock;

import com.biz.gbck.dao.mysql.po.stock.CompanyStock;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;;
import java.util.List;
import java.util.Set;

/**
 * Created by lzz on 2017/5/17.
 */
public interface StockRepository extends JpaSpecificationExecutor<CompanyStock>, CommonJpaRepository<CompanyStock, Long>, StockDao {

     List<CompanyStock> findByCompanyId(Long companyId);

//    @Query("SELECT s FROM Stock s WHERE s.productId in (:productIds) AND s.companyId = :companyId")
    Page<CompanyStock> findByCompanyIdAndProductIdIn(@Param("companyId") Long companyId, @Param("productIds") Set<Long> productIds, Pageable pageable);


}
