package com.biz.service.upgrade;

import com.biz.gbck.dao.redis.repository.upgrade.UpgradeRedisDao;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.gbck.vo.upgrade.*;
import com.biz.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpgradeService extends CommonService {

    private final Logger logger = LoggerFactory.getLogger(UpgradeService.class);


    @Autowired
    private UpgradeRedisDao upgradeRedisDao;



    public void save(AddUpgradeVo vo) {
        UpgradeRo ro=new UpgradeRo();
        ro.setForce(vo.isForce());
        ro.setVersion(vo.getVersion());
        ro.setOs(vo.getOs());
        ro.setInfo(vo.getInfo());
        ro.setUrl(vo.getUrl());
        ro.setMd5(vo.getMd5());
        ro.setInhourse(vo.getInhourse());
        ro.generateId();
       upgradeRedisDao.save(ro);
    }


    public void delete(String id) {
        upgradeRedisDao.delete(id);
    }


    public List<UpgradeRo> findAll(String os){
        return upgradeRedisDao.findAll(os);
    }



    public UpgradeRo needUpgrade(String os, String version, boolean inhourse) {
     return upgradeRedisDao.needUpgrade(os,version,inhourse);
    }

    /**
     * 根据版本号码和手机型号查询对应数据
     * @param version
     * @param os
     * @return
     */
    public boolean verifyVersion(String version, String os) {
        return upgradeRedisDao.findByOsAndVersion(version,os);
    }


}
