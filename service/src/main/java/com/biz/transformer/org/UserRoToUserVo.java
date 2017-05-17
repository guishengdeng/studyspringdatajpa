package com.biz.transformer.org;


import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.org.UserLoginResVo;
import com.google.common.base.Function;

import java.io.Serializable;

public class UserRoToUserVo implements Function<UserRo, UserLoginResVo>, Serializable {

    @Override public UserLoginResVo apply(UserRo ro) {

        UserLoginResVo vo = new UserLoginResVo();
        vo.setUserId(ro.getId());
        vo.setShopId(String.valueOf(ro.getShopId()));
        vo.setName(ro.getName());
        vo.setMobile(ro.getMobile());
        vo.setIsAdmin(ro.getIsAdmin());
        vo.setAvatar(ro.getAvatar());
        vo.setUserStatus(ro.getStatus());
        return vo;
    }

}
