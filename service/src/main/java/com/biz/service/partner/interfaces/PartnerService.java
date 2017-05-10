package com.biz.service.partner.interfaces;

import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.vo.partner.PartnerDetailRespVo;
import com.biz.vo.partner.PartnerRegisterReqVo;
import com.biz.vo.partner.PartnerSearchReqVo;

import java.util.List;

/**
 * Created by haibin.tang on 2017/5/8.
 */
public interface PartnerService {

    void createPartner(PartnerRegisterReqVo reqVo) throws PartnerExceptions;

    boolean validAccountIsExist(String username);

    List<PartnerDetailRespVo> findAllByCondition(PartnerSearchReqVo reqVo);
}
