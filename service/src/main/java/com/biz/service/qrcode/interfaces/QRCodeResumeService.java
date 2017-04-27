package com.biz.service.qrcode.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodeResumePO;
import com.biz.gbck.vo.qrcode.QRCodeSearchVO;

/**
 * @author xs
 * @date 2017年04月25日
 */
public interface QRCodeResumeService {
    
    QRCodeResumePO save(QRCodeResumePO po);
    
    Page<QRCodeResumePO> searchQRCodeResume(QRCodeSearchVO reqVo);
    
    List<QRCodeResumePO> listAll();
}
