package com.biz.gbck.dao.mysql.repository.qrcode;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodeResumePO;
import com.biz.support.jpa.repository.CommonJpaRepository;

@Repository
public interface QRCodeResumeRepository extends CommonJpaRepository<QRCodeResumePO, String>, QRCodeResumeDao, JpaSpecificationExecutor<QRCodeResumePO>{
    
}
