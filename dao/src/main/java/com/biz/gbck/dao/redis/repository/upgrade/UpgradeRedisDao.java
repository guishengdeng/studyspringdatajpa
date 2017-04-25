package com.biz.gbck.dao.redis.repository.upgrade;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class UpgradeRedisDao extends CrudRedisDao<UpgradeRo,String> {

}
