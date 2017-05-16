package com.biz.soa.org.service.upgrade;

import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.gbck.vo.upgrade.AddUpgradeVo;
import java.util.List;

public interface UpgradeSoaService {


    void save(AddUpgradeVo vo);

    void delete(String id);


    List<UpgradeRo> findAll(String os);


    UpgradeRo needUpgrade(String os, String version, boolean inhourse);

    /**
     * 根据版本号码和手机型号查询对应数据
     */
    boolean verifyVersion(String version, String os);


}
