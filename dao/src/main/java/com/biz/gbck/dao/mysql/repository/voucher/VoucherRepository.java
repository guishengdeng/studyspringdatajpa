package com.biz.gbck.dao.mysql.repository.voucher;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.support.jpa.repository.CommonJpaRepository;

@Repository
public interface VoucherRepository extends CommonJpaRepository<VoucherPo, Long>, JpaSpecificationExecutor<VoucherPo> {

	@Modifying 
	@Query(value = "UPDATE vou_voucher p SET p.orderId = :orderId ,useTime=:useTime,useAmount=:useAmount WHERE id = :id",nativeQuery = true)
	void useVoucher(@Param("id") Long id, @Param("orderId") Long orderId,@Param("useAmount") Integer useAmount,@Param("useTime") Timestamp useTime);

		List<VoucherPo> findByBindingUserId(Long userId);
}
