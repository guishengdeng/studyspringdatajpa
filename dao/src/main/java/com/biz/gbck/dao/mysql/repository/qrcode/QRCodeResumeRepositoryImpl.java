package com.biz.gbck.dao.mysql.repository.qrcode;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodeResumePO;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;

public class QRCodeResumeRepositoryImpl extends CommonJpaRepositoryBean<QRCodeResumePO, String> implements QRCodeResumeDao{
    @Autowired
    public QRCodeResumeRepositoryImpl(EntityManager em) {

        super(QRCodeResumePO.class, em);
    }
}
