package com.biz.service;

import com.biz.gbck.dao.redis.repository.upgrade.UpgradeRedisDao;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.gbck.vo.upgrade.*;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class UpgradeService extends CommonService {

    private final Logger logger = LoggerFactory.getLogger(UpgradeService.class);

    @Autowired
    private UpgradeRedisDao upgradeRedisDao;



    public void save(AddUpgradeVo vo) {
        UpgradeRo androidRo=new UpgradeRo();
        androidRo.setForce(vo.isForce());
        androidRo.setVersion(vo.getVersion());
        androidRo.setOs(vo.getOs());
        androidRo.setInfo(vo.getInfo());
        androidRo.setUrl(vo.getUrl());
        androidRo.setMd5(vo.getMd5());
        androidRo.setInhourse(vo.getInhourse());
        androidRo.generateId();
        upgradeRedisDao.save(androidRo);
    }


    public void delete(String id) {
        upgradeRedisDao.delete(id);
    }

    public List<UpgradeRo> findAll(String os){
        List<UpgradeRo> resUpgradeRos=newArrayList();
        List<UpgradeRo> upgradeRos=upgradeRedisDao.findAll();
        if(CollectionUtils.isNotEmpty(upgradeRos)){
            for (UpgradeRo ro:upgradeRos){
                if(ro.getOs() != null && ro.getOs().equals(os)){
                    resUpgradeRos.add(ro);
                }
            }
        }
        return resUpgradeRos;

    }



  /*  public UpgradeRo needUpgrade(String os, String version, boolean inhourse) {
        return upgraderDao.needUpgrade(os, version, inhourse);
    }*/

}
