package com.biz.gbck.dao.redis.ro.upgrade;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;

@Ro(key = "upgradeAndroidRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class UpgradeAndroidRo  extends UpgradeBaseRo{


}
