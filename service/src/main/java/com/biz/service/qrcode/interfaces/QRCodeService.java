package com.biz.service.qrcode.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.qrcode.QRCodeSearchVO;

/**
 * @author xs
 * @date 2017年04月25日
 */
public interface QRCodeService {
    
    QRCodePO save(QRCodePO po);
    
    void remove(String bcno);
    
    Page<QRCodePO> searchQRCode(QRCodeSearchVO searchVO);
    
    List<QRCodePO> listByStatus(CommonStatusEnum status);
}
