package com.biz.gbck.transform.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopStatus;
import com.google.common.base.Function;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashSet;

public class UserPoToShopPo implements Function<UserPo, ShopPo>, Serializable {

    @Override
    public ShopPo apply(UserPo po) {
        ShopPo shopPo = null;
        if (po != null) {
            shopPo = new ShopPo();
            shopPo.setMobile(po.getMobile());
            shopPo.setDetailAuditStatus(AuditStatus.NEED_INFO.getValue());
            shopPo.setQualificationAuditStatus(AuditStatus.NEED_INFO.getValue());
            shopPo.setStatus(CommonStatusEnum.ENABLE);
            shopPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            Set<UserPo> users = newHashSet();
            users.add(po);
            shopPo.setUsers(users);
        }
        return shopPo;
    }

}
