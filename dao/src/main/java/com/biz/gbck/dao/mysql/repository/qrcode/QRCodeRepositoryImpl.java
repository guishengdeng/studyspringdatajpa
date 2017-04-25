package com.biz.gbck.dao.mysql.repository.qrcode;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;

public class QRCodeRepositoryImpl extends CommonJpaRepositoryBean<QRCodePO, String> implements QRCodeDao{
    @Autowired
    public QRCodeRepositoryImpl(EntityManager em) {

        super(QRCodePO.class, em);
    }
}
