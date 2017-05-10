package com.biz.service.org;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.dao.mysql.repository.org.PlatformRepository;
import com.biz.gbck.dao.mysql.specification.platform.PartnerSearchSpecification;
import com.biz.gbck.dao.mysql.specification.platform.PlatformSearchSpecification;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.org.interfaces.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liubin
 * @date 5/2/17 10:10
 */
@Service
public class PlatformServiceImpl extends AbstractBaseService implements PlatformService{

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private PartnerRepository partnerRepository;


    @Override
    public List<PlatformPo> findAll() {
        return platformRepository.findAll();
    }

    @Override
    public PlatformPo findOne(Long id) {
        return platformRepository.findOne(id);
    }

    @Override
    public Page<PlatformPo> findPlatformList(PlatformSearchVo reqVo) {
        return platformRepository.findAll(new PlatformSearchSpecification(reqVo), new PageRequest(reqVo.getPage()-1, reqVo.getPageSize()));
    }

    @Override
    public Page<PartnerPo> findPartnerList(PartnerSearchVo reqVo) {
        return partnerRepository.findAll(new PartnerSearchSpecification(reqVo), new PageRequest(reqVo.getPage()-1, reqVo.getPageSize()));
    }
}
