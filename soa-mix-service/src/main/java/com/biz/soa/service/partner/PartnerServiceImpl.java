package com.biz.soa.service.partner;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.service.partner.interfaces.PartnerService;
import com.biz.vo.partner.PartnerDetailRespVo;
import com.biz.vo.partner.PartnerRegisterReqVo;
import com.biz.vo.partner.PartnerReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PartnerServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月18日
 * @reviewer
 * @description
 * @see
 */
@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Override
    public void createPartner(PartnerRegisterReqVo reqVo) throws PartnerExceptions {

    }

    @Override
    public boolean validAccountIsExist(String username) {
        return false;
    }

    @Override
    public List<PartnerDetailRespVo> findAllByCondition(PartnerReqVo reqVo) {
        return null;
    }

    @Override
    public PartnerDetailRespVo findById(Long id) {
        return null;
    }

    @Override
    public void updatePartnerStatus(PartnerReqVo partnerReqVo) throws CommonException {

    }

    @Override
    public List<Long> getIdList(String name) {
        return partnerRepository.getIdsByNameLike(name);
    }
}