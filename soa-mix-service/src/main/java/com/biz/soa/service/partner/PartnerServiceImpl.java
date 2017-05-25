package com.biz.soa.service.partner;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.gbck.vo.platform.PartnerRespVo;
import com.biz.service.partner.interfaces.PartnerService;
import com.biz.vo.partner.PartnerDetailRespVo;
import com.biz.vo.partner.PartnerRegisterReqVo;
import com.biz.vo.partner.PartnerReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PartnerPo> listByName(String name) {
        return partnerRepository.getPartnerPosByName(name);
    }

    @Override
    public List<PartnerPo> findByIds(Iterable<Long> iterable) {
        return partnerRepository.findAll(iterable);
    }

    @Override
    public List<PartnerRespVo> getNotDuplicatePartnerName() {
        List<String> list = partnerRepository.removeDuplicatedName();
        List<PartnerRespVo> voList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(list)){
            for(String name : list){
                PartnerRespVo vo = new PartnerRespVo();
                vo.setPartnerName(name);
                voList.add(vo);
            }
        }
        return voList;
    }


}
