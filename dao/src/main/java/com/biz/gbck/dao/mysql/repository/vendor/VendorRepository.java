package com.biz.gbck.dao.mysql.repository.vendor;


import com.biz.gbck.enums.vendor.AuditStatus;
import com.biz.gbck.enums.vendor.VendorStatus;
import com.biz.gbck.dao.mysql.po.product.Vendor;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 店铺Repository
 *
 * @author LGJ
 * @date 2016年12月20日
 */
@Repository
public interface VendorRepository extends CommonJpaRepository<Vendor, Long>, JpaSpecificationExecutor<Vendor>, VendorDao {
    List<Vendor> findByAuditStatusAndStatus(AuditStatus auditStatus, VendorStatus status);

    Vendor findByVendorName(String vendorName);

    List<Vendor> findByVendorType(VendorTypeEnum type);

    Vendor findByUserId(Long userId);

    @Query("select id from Vendor where id=?1")
    Long findByVendorId(Long id);

    List<Vendor> findByIdIn(List<Long> ids);

    @Query("select id from Vendor where vendorCode=?1")
    Long findIdByVendorCode(String code);

    Vendor findByVendorCode(String code);

}
