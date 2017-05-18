package com.biz.transformer.partner;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.vo.partner.PartnerDetailRespVo;
import com.google.common.base.Function;
import org.springframework.beans.BeanUtils;

/**
 * Created by haibin.tang on 2017/5/10.
 */
public class PartnerPo2PartnerDetailRespVo implements Function<PartnerPo, PartnerDetailRespVo>{

    @Override
    public PartnerDetailRespVo apply(PartnerPo input) {
        if(input == null) {
            return null;
        }
        PartnerDetailRespVo result = new PartnerDetailRespVo();
        BeanUtils.copyProperties(input, result);
        return result;
    }
}
