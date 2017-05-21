package com.biz.service.partner.interfaces;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.exceptions.partner.PartnerExceptions;
import com.biz.vo.partner.PartnerDetailRespVo;
import com.biz.vo.partner.PartnerRegisterReqVo;
import com.biz.vo.partner.PartnerReqVo;

import java.util.List;

/**
 * Created by haibin.tang on 2017/5/8.
 */
public interface PartnerService {

    void createPartner(PartnerRegisterReqVo reqVo) throws PartnerExceptions;

    boolean validAccountIsExist(String username);

    List<PartnerDetailRespVo> findAllByCondition(PartnerReqVo reqVo);

    PartnerDetailRespVo findById(Long id);

    void updatePartnerStatus(PartnerReqVo partnerReqVo) throws CommonException;

    List<PartnerPo>  listByName(String name);

    List<PartnerPo>  findByIds(Iterable<Long> iterable);

    List<PartnerPo> findAll();
}
