package com.biz.service;

import com.biz.gbck.dao.redis.repository.upgrade.UpgradeAndroidRedisDao;
import com.biz.gbck.dao.redis.repository.upgrade.UpgradeIosRedisDao;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeAndroidRo;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeBaseRo;
import com.biz.gbck.dao.redis.ro.upgrade.UpgradeIosRo;
import com.biz.gbck.vo.upgrade.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpgradeService extends CommonService {

    private final Logger logger = LoggerFactory.getLogger(UpgradeService.class);

    @Autowired
    private UpgradeAndroidRedisDao androidRedisDao;

    @Autowired
    private UpgradeIosRedisDao iosRedisDao;


    public void save(AddUpgradeVo vo) {
        if(vo != null && vo.getOs() != null){
            if("android".equals(vo.getOs())){
                UpgradeAndroidRo androidRo=new UpgradeAndroidRo();
                androidRo.setForce(vo.isForce());
                androidRo.setVersion(vo.getVersion());
                androidRo.setOs(vo.getOs());
                androidRo.setInfo(vo.getInfo());
                androidRo.setUrl(vo.getUrl());
                androidRo.setMd5(vo.getMd5());
                androidRo.setInhourse(vo.getInhourse());
                androidRo.generateId();
                androidRedisDao.save(androidRo);
            }
            if("ios".equals(vo.getOs())){
                UpgradeIosRo iosRo=new UpgradeIosRo();
                iosRo.setForce(vo.isForce());
                iosRo.setVersion(vo.getVersion());
                iosRo.setOs(vo.getOs());
                iosRo.setInfo(vo.getInfo());
                iosRo.setUrl(vo.getUrl());
                iosRo.setMd5(vo.getMd5());
                iosRo.setInhourse(vo.getInhourse());
                iosRo.generateId();
                iosRedisDao.save(iosRo);
            }
        }
    }

    public UpgradeAndroidRo getUpgradeAndroidRo(String id) {
        return androidRedisDao.getUpgradeAndroidRo(id);
    }
    public UpgradeIosRo getUpgradeIosRo(String id) {
        return iosRedisDao.getUpgradeIosRo(id);
    }

    public void delete(String os,String id) {
        if(StringUtils.isNotBlank(os)){
            if("android".equals(os)){
                androidRedisDao.delete(id);
            }
            if("ios".equals(os)){
                iosRedisDao.delete(id);
            }
        }
    }

    public List<UpgradeAndroidRo> findAllUpgradeAndroidRo(){
        return androidRedisDao.findAll();
    }

    public List<UpgradeIosRo> findAllUpgradeIosRo() {
        return iosRedisDao.findAll();
    }

  /*  public UpgradeRo needUpgrade(String os, String version, boolean inhourse) {
        return upgraderDao.needUpgrade(os, version, inhourse);
    }*/

}
