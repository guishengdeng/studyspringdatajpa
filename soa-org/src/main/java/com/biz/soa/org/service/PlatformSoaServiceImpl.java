package com.biz.soa.org.service;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.dao.mysql.repository.org.PlatformRepository;
import com.biz.gbck.dao.mysql.specification.platform.PartnerSearchSpecification;
import com.biz.gbck.dao.mysql.specification.platform.PlatformSearchSpecification;
import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatformResSearchVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.service.AbstractBaseService;
import com.biz.service.org.interfaces.PlatformService;
import com.biz.soa.org.service.interfaces.PlatformSoaService;
import com.biz.soa.org.transformer.PartnerPoPageToPartnerSearchResVoPage;
import com.biz.soa.org.transformer.PartnerPoToPartnerSearchResVo;
import com.biz.soa.org.transformer.PlatformPoPageToPlatformResSearchVoPage;
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
public class PlatformSoaServiceImpl extends AbstractBaseService implements PlatformSoaService {

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
    public PageVO<PlatformResSearchVo> findPlatformList(PlatformSearchVo reqVo) {
        Page<PlatformPo> pagePo =platformRepository.findAll(new PlatformSearchSpecification(reqVo), new PageRequest(reqVo.getPage()-1, reqVo.getPageSize()));
        return new PlatformPoPageToPlatformResSearchVoPage().apply(pagePo);
    }

    @Override
    public PageVO<PartnerSearchResVo>  findPartnerList(PartnerSearchVo reqVo) {
        Page<PartnerPo> pagePo=partnerRepository.findAll(new PartnerSearchSpecification(reqVo), new PageRequest(reqVo.getPage()-1, reqVo.getPageSize()));
        return new PartnerPoPageToPartnerSearchResVoPage().apply(pagePo);
    }

    @Override
    public PartnerSearchResVo findPartnerById(Long id) {
        PartnerPo partnerPo=partnerRepository.findOne(id);
        if(partnerPo != null){
            return new PartnerPoToPartnerSearchResVo().apply(partnerPo);
        }
       return new PartnerSearchResVo();
    }
}
