package com.biz.service.org;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.dao.mysql.repository.org.PlatformRepository;
import com.biz.gbck.dao.mysql.specification.platform.PartnerSearchSpecification;
import com.biz.gbck.dao.mysql.specification.platform.PlatformSearchSpecification;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatFormRespVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.org.interfaces.PlatformService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
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

    @Override
    public PartnerPo findPartnerById(Long id) {
        return partnerRepository.findOne(id);
    }

    /**
     *
     * 将集合po转化成集合vo,用户前端页面在用户查询订单时
     * ，前端做模糊查询
     */
    @Override
    public List<PlatFormRespVo> poList2VoList(List<PlatformPo> platformPos) {
        List<PlatFormRespVo> voList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(platformPos)){
            for(PlatformPo po: platformPos){
                PlatFormRespVo vo = new PlatFormRespVo();
                vo.setId(po.getId());
                vo.setPlatFormName(po.getName());
                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public List<PlatformPo> listByName(String name) {
        return platformRepository.getIdsByNameLike(name);
    }

    @Override
    public List<PlatformPo> findByIds(Iterable<Long> iterable) {
        return platformRepository.findAll(iterable);
    }
}
