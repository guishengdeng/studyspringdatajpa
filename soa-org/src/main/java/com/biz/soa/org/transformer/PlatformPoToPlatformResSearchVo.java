package com.biz.soa.org.transformer;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.vo.platform.PlatformResSearchVo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial") public class PlatformPoToPlatformResSearchVo
    implements Function<PlatformPo, PlatformResSearchVo>, Serializable {

    @Override
    public PlatformResSearchVo apply(PlatformPo input) {
        PlatformResSearchVo vo=null;
        if(input != null){
            vo=new PlatformResSearchVo();
            vo.setId(input.getId());
            vo.setName(input.getName());
            vo.setCorporateName(input.getCorporateName());
            vo.setMobile(input.getMobile());
            vo.setStatus(input.getStatus());
        }
        return vo;
    }
}
