package com.biz.gbck.dao.mysql.repository.qrcode;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;

@Repository
public interface QRCodeRepository extends CommonJpaRepository<QRCodePO, String>, QRCodeDao, JpaSpecificationExecutor<QRCodePO>{
    List<QRCodePO> findByStatus(CommonStatusEnum status);
    
    @Transactional
    @Modifying
    @Query("UPDATE QRCodePO po SET po.status = :status WHERE po.bcno = :bcno")
    Integer updateStatus(@Param("bcno") String bcno, @Param("status") CommonStatusEnum status);
}
