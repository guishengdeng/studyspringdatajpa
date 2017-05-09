package com.biz.service.upgrade;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.redis.repository.config.AppConfigRedisDao;
import com.biz.gbck.dao.redis.ro.config.AppConfigRo;
import com.biz.gbck.transform.config.AppConfigRoToAppConfigVo;
import com.biz.gbck.vo.config.AppConfigVo;
import com.biz.service.CommonService;
import com.biz.util.SingleTimerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


/**
 *
 */
@Service
public class CacheService extends CommonService {


  private final static Logger logger = LoggerFactory.getLogger(CacheService.class);

  @Autowired
  private AppConfigRedisDao appConfigRedisDao;

  private AppConfigVo appConfigVo;

  @PostConstruct
  public void setup(){
    SingleTimerFactory.getTimer().schedule(new TimerTask() {
      @Override public void run() {
        appConfigVo = null;
      }
    }, 0, TimeUnit.SECONDS.toMillis(5));
  }

  public AppConfigVo getAppConfigVo() {
    if (appConfigVo == null) {
      reloadAppConfig();
    }
    return appConfigVo;
  }

  private void reloadAppConfig() {
    AppConfigRo ro = appConfigRedisDao.get();
    if(ro == null) {
      ro = new AppConfigRo();
      appConfigRedisDao.add(ro);
    }
    appConfigVo = new AppConfigRoToAppConfigVo().apply(ro);
  }

  public void saveAppConfig(AppConfigRo ro) throws CommonException {
    appConfigRedisDao.add(ro);
    reloadAppConfig();
    /*sendCacheChangeMessage(CacheChangeMessageType.CONFIG);*/
  }
  

}
