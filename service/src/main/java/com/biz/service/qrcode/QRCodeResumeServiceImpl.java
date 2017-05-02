package com.biz.service.qrcode;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.qrcode.QRCodeResumePO;
import com.biz.gbck.dao.mysql.repository.qrcode.QRCodeResumeRepository;
import com.biz.gbck.vo.qrcode.QRCodeSearchVO;
import com.biz.service.AbstractBaseService;
import com.biz.service.qrcode.interfaces.QRCodeResumeService;
import com.google.common.collect.Lists;
/**
 * 二维码履历
 * @author xs
 *
 */
@Service
public class QRCodeResumeServiceImpl extends AbstractBaseService implements QRCodeResumeService {

    @Autowired
    private QRCodeResumeRepository qrcodeResumeRepository;
    
    @Override
    public QRCodeResumePO save(QRCodeResumePO po) {
        if(po.getId()==null){
            po.setId(idService.nextId());
            po.setOptDate(DateUtil.now());
        }
        return qrcodeResumeRepository.save(po);
    }

    @Override
    public List<QRCodeResumePO> listAll() {
        return qrcodeResumeRepository.findAll();
    }

    @Override
    public Page<QRCodeResumePO> searchQRCodeResume(QRCodeSearchVO reqVo) {
        return qrcodeResumeRepository.findAll(new Specification<QRCodeResumePO>() {
            @Override
            public Predicate toPredicate(Root<QRCodeResumePO> root, CriteriaQuery<?> criteriaQuery,
                    CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();

                if (reqVo.getBusStatus() != null) {
                    // 业务状态
                    Predicate predicate = criteriaBuilder.equal(root.get("businessStatus"), reqVo.getBusStatus());
                    predicates.add(predicate);
                }

                if(StringUtils.isNotBlank(reqVo.getBcno())){
                    predicates.add(criteriaBuilder.equal(root.get("bcno").as(String.class), reqVo.getBcno()));
                }

                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return criteriaQuery.getRestriction();
            }
        }, new PageRequest(reqVo.getPage()-1, reqVo.getPageSize()));
    }
}
