package com.biz.service.qrcode.interfaces;

import java.util.List;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.enums.CommonStatusEnum;

/**
 * @author xs
 * @date 2017年04月25日
 */
public interface QRCodeService {
    
    QRCodePO save(QRCodePO po);
    
    List<QRCodePO> listByStatus(CommonStatusEnum status);
}
