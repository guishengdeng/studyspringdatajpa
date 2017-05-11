package com.biz.service.partner;

import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.repository.admin.AdminRepository;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.enums.partner.ApprovalStatus;
import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.service.IdService;
import com.biz.service.partner.interfaces.PartnerService;
import com.biz.service.partner.specification.PartnerSpecification;
import com.biz.transformer.partner.PartnerPo2PartnerDetailRespVo;
import com.biz.transformer.partner.PartnerRegisterReqVo2PartnerPo;
import com.biz.vo.partner.PartnerDetailRespVo;
import com.biz.vo.partner.PartnerRegisterReqVo;
import com.biz.vo.partner.PartnerSearchReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Created by haibin.tang on 2017/5/8.
 */
@Service
public class PartnerServiceImpl implements PartnerService{

    private Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private IdService idService;

    @Override
    public void createPartner(PartnerRegisterReqVo reqVo) throws PartnerExceptions {
        if(reqVo == null) {
            throw new PartnerExceptions(ExceptionCode.Global.PARAMETER_ERROR, "注册信息不完整");
        }
        if(validAccountIsExist(reqVo.getUsername())) {
            throw new PartnerExceptions(ExceptionCode.Partner.IS_EXISTS, "账户已存在");
        }
        PartnerPo partnerPo = new PartnerRegisterReqVo2PartnerPo().apply(reqVo);
        partnerPo.setId(idService.nextId());
        partnerPo.setApprovalStatus(ApprovalStatus.UNDER_REVIEW);
        partnerRepository.save(partnerPo);
    }

    @Override
    public boolean validAccountIsExist(String username) {
        Assert.isTrue(StringUtils.isNoneBlank(username), "用户名不能为空");
        return adminRepository.findOne(username) != null;
    }

    @Override
    public List<PartnerDetailRespVo> findAllByCondition(PartnerSearchReqVo reqVo) {
        List<PartnerPo> pos = reqVo == null ? partnerRepository.findAll() : partnerRepository.findAll(new PartnerSpecification(reqVo));
        if(CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }
        return Lists.transform(pos, new PartnerPo2PartnerDetailRespVo());
    }
}
