package com.biz.gbck.common.com.transformer;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.google.common.base.Function;

import java.io.Serializable;

public class UserPoToUserRo implements Function<UserPo, UserRo>, Serializable {

    @Override public UserRo apply(UserPo po) {
        UserRo ro = null;
        if (po != null) {
            ro = new UserRo();
            ro.setId(po.getId().toString());
            ro.setAccount(po.getAccount());
            ro.setName(po.getName());
            ro.setMobile(po.getMobile());
            ro.setPassword(po.getPassword());
            ro.setOriginalPassword(po.getOriginalPassword());
            ro.setAvatar(po.getAvatar());
            ShopPo shop = po.getShop();
            ro.setShopId(shop == null ? null : shop.getId());
            ro.setCreateTime(po.getCreateTime());
            ro.setRegisterDeviceId(ro.getRegisterDeviceId());
            ro.setRegisterIP(po.getRegisterIP());
            ro.setLastLoginDeviceToken(po.getLastLoginDeviceToken());
            ro.setLastLoginTime(po.getLastLoginTime());
            ro.setLastLoginIP(po.getLastLoginIP());
            ro.setIsAdmin(po.getIsAdmin());
            ro.setStatus(po.getStatus());
        }
        return ro;
    }

}
