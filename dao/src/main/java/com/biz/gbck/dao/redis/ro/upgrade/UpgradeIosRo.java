package com.biz.gbck.dao.redis.ro.upgrade;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Ro(key = "upgradeIosRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class UpgradeIosRo  extends UpgradeBaseRo{


}
