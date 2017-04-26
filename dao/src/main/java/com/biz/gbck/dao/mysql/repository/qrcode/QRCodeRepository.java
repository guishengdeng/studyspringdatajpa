package com.biz.gbck.dao.mysql.repository.qrcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.dao.mysql.repository.demo.CatDao;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;

@Repository
public interface QRCodeRepository extends CommonJpaRepository<QRCodePO, String>, CatDao, JpaSpecificationExecutor<QRCodePO>{
    List<QRCodePO> findByStatus(CommonStatusEnum status);
}