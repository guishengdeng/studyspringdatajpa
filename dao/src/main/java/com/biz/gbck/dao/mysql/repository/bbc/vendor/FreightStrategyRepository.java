package com.biz.gbck.dao.mysql.repository.bbc.vendor;

import com.biz.gbck.dao.mysql.po.vendor.bbc.FreightStrategy;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author mounan
 * @Description: 运费策略dao
 * @time:2017年1月12日 下午12:06:47
 */
@Repository
public interface FreightStrategyRepository extends CommonJpaRepository<FreightStrategy, Long>, JpaSpecificationExecutor<FreightStrategy> {

    List<FreightStrategy> findFreightStrategyByVendorIdAndDeleted(Long vendorId, Boolean status);
}
