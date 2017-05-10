package com.biz.transformer.partner;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.vo.partner.PartnerRegisterReqVo;
import com.google.common.base.Function;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by haibin.tang on 2017/5/8.
 */
public class PartnerRegisterReqVo2PartnerPo implements Function<PartnerRegisterReqVo, PartnerPo> {

    @Override
    public PartnerPo apply(PartnerRegisterReqVo input) {
        if(input == null) {
            return null;
        }
        PartnerPo result = new PartnerPo();
        BeanUtils.copyProperties(input, result);
        Admin admin = new Admin();
        admin.setUsername(input.getUsername());
        admin.setPassword(input.getPassword());
        admin.setName(input.getUsername());
        admin.setStatus(CommonStatusEnum.DISABLE);
        Set<Admin> admins = new HashSet<>();
        admins.add(admin);
        result.setAdmins(admins);
        return result;
    }
}
