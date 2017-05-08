package com.biz.service.partner.interfaces;

import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.vo.partner.PartnerRegisterReqVo;

/**
 * Created by haibin.tang on 2017/5/8.
 */
public interface PartnerService {

    void createPartner(PartnerRegisterReqVo reqVo) throws PartnerExceptions;
}
