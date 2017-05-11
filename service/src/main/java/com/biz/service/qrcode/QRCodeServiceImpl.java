package com.biz.service.qrcode;

import com.biz.gbck.dao.mysql.po.qrcode.QRCodePO;
import com.biz.gbck.dao.mysql.repository.qrcode.QRCodeRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.qrcode.QRCodeSearchVO;
import com.biz.service.AbstractBaseService;
import com.biz.service.qrcode.interfaces.QRCodeService;
import com.google.common.collect.Lists;
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

@Service
public class QRCodeServiceImpl extends AbstractBaseService implements QRCodeService {

    @Autowired
    private QRCodeRepository qrcodeRepository;
    
    @Override
    public QRCodePO save(QRCodePO po) {
        return qrcodeRepository.save(po);
    }
    
    @Override
    public void remove(String bcno) {
        qrcodeRepository.updateStatus(bcno, CommonStatusEnum.DISABLE);
    }

    @Override
    public List<QRCodePO> listByStatus(CommonStatusEnum status) {
        return qrcodeRepository.findByStatus(status);
    }

    @Override
    public Page<QRCodePO> searchQRCode(final QRCodeSearchVO reqVo) {
        return qrcodeRepository.findAll(new Specification<QRCodePO>() {
            @Override
            public Predicate toPredicate(Root<QRCodePO> root, CriteriaQuery<?> criteriaQuery,
                    CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();

                if (reqVo.getStatus() != null) {
                    // 删除状态
                    Predicate predicate = criteriaBuilder.equal(root.get("status"), reqVo.getStatus());
                    predicates.add(predicate);
                }

                if (reqVo.getBusStatus() != null) {
                    // 业务状态
                    Predicate predicate = criteriaBuilder.equal(root.get("businessStatus"), reqVo.getBusStatus());
                    predicates.add(predicate);
                }

                if(StringUtils.isNotBlank(reqVo.getBcno())){
                    Predicate predicate = criteriaBuilder.equal(root.get("bcno").as(String.class), reqVo.getBcno());
                    predicate =criteriaBuilder.or(predicate,criteriaBuilder.equal(root.get("boxno").as(String.class), reqVo.getBcno()));
                    predicates.add(predicate);
                }

                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return criteriaQuery.getRestriction();
            }
        }, new PageRequest(reqVo.getPage()-1, reqVo.getPageSize()));
    }
}
