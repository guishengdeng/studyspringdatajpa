package com.biz.service.partner;

import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.dao.mysql.repository.admin.AdminRepository;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.service.partner.interfaces.PartnerService;
import com.biz.transformer.partner.PartnerRegisterReqVo2PartnerPo;
import com.biz.vo.partner.PartnerRegisterReqVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    @Override
    public void createPartner(PartnerRegisterReqVo reqVo) throws PartnerExceptions {
        if(reqVo == null) {
            throw new PartnerExceptions(ExceptionCode.Global.PARAMETER_ERROR, "注册信息不完整");
        }
        if(validAccountIsExist(reqVo.getUsername())) {
            throw new PartnerExceptions(ExceptionCode.Partner.IS_EXISTS, "账户已存在");
        }
        partnerRepository.save(new PartnerRegisterReqVo2PartnerPo().apply(reqVo));
    }

    @Override
    public boolean validAccountIsExist(String username) {
        Assert.isTrue(StringUtils.isNoneBlank(username), "用户名不能为空");
        return adminRepository.findOne(username) != null;
    }
}
