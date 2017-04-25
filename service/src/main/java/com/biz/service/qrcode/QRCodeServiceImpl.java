package com.biz.service.qrcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.dao.mysql.repository.qrcode.QRCodeRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.service.qrcode.interfaces.QRCodeService;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Autowired
    private QRCodeRepository qrcodeRepository;
    
    @Override
    public QRCodePO save(QRCodePO po) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<QRCodePO> listByStatus(CommonStatusEnum status) {
        // TODO Auto-generated method stub
        return qrcodeRepository.findByStatus(status);
    }

}
