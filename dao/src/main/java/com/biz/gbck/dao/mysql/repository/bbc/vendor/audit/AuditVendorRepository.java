package com.biz.gbck.dao.mysql.repository.bbc.vendor.audit;

import com.biz.gbck.dao.mysql.po.vendor.bbc.AuditVendor;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author mounan
 * @Description: 商铺审核数据库操作类
 * @time:2017年2月9日 下午5:09:51
 */
@Repository
public interface AuditVendorRepository extends CommonJpaRepository<AuditVendor, Long>, JpaSpecificationExecutor<AuditVendor> {

    List<AuditVendor> findByUserId(Long userId);

    @Query("from AuditVendor  where userId = ?1")
    List<AuditVendor> findByUserIdAndStatus(Long userId);

    AuditVendor findByVendorCode(String vendorCode);

    @Query("select vo.createTimestamp from AuditVendor vo where id = ?1")
    Timestamp findCreateTimeById(Long id);
}
