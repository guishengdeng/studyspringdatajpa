package com.biz.soa.org.transformer;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial") public class PartnerPoToPartnerSearchResVo
    implements Function<PartnerPo, PartnerSearchResVo>, Serializable {

    @Override
    public PartnerSearchResVo apply(PartnerPo input) {
        PartnerSearchResVo vo=null;
        if(input != null){
            vo=new PartnerSearchResVo();
            vo.setId(input.getId());
            vo.setName(input.getName());
            vo.setCorporateName(input.getCorporateName());
            vo.setMobile(input.getMobile());
            vo.setStatus(input.getStatus());
            if(input.getProvince() != null){
                vo.setProvinceName(input.getProvince().getName());
            }
            if(input.getCity() != null){
                vo.setCityName(input.getCity().getName());
            }
            if(input.getPlatform() != null){
                vo.setPlatformId(input.getPlatform().getId());
                vo.setPlatformName(input.getPlatform().getName());
            }
        }
        return vo;
    }
}
