package com.biz.gbck.dao.mysql.repository.voucher;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.support.jpa.repository.CommonJpaRepository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface VoucherTypeRepository extends CommonJpaRepository<VoucherTypePo, Long>, JpaSpecificationExecutor<VoucherTypePo>,VoucherTypeDao{

    @Query(value = "FROM VoucherTypePo v WHERE v.deletedAt = null ORDER BY v.startTime DESC")
    List<VoucherTypePo> allByStartTimeDesc();

    @Query(value = "FROM VoucherTypePo v WHERE v.id = ?1")
    VoucherTypePo getDataById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE vou_type SET deletedAt = :time WHERE id = :id", nativeQuery = true)
    void deleteDataById(@Param("id") Long id,
                               @Param("time") Timestamp time);

//    List<VoucherTypePo> findByPaymentType(String paymentType);
}
