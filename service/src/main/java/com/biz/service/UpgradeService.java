package com.biz.service;

import com.biz.gbck.common.ro.upgrade.UpgradeRo;
import com.biz.gbck.common.vo.upgrade.AddUpgradeVo;
import com.biz.gbck.dao.redis.upgrade.UpgradeRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpgradeService extends CommonService {

    private final Logger logger = LoggerFactory.getLogger(UpgradeService.class);

    @Autowired
    private UpgradeRedisDao upgraderDao;


    public void save(AddUpgradeVo vo) {
        UpgradeRo ro = new UpgradeRo();
        ro.setForce(vo.isForce());
        ro.setVersion(vo.getVersion());
        ro.setOs(vo.getOs());
        ro.setInfo(vo.getInfo());
        ro.setUrl(vo.getUrl());
        ro.setMd5(vo.getMd5());
        ro.setInhourse(vo.getInhourse());
        ro.generateId();
        upgraderDao.save(ro);
    }

    public UpgradeRo get(String id) {
        return upgraderDao.getUpgradeRo(id);
    }

    public void delete(String id) {
        upgraderDao.delete(id);
    }

    public List<UpgradeRo> findAll(String os) {
        return upgraderDao.findAll(os);
    }

    public UpgradeRo needUpgrade(String os, String version, boolean inhourse) {
        return upgraderDao.needUpgrade(os, version, inhourse);
    }

}
