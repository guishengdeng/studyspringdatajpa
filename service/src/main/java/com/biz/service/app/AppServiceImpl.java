package com.biz.service.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.dao.mysql.repository.app.AppRepository;
import com.biz.gbck.transform.app.AppVoToAppPo;
import com.biz.gbck.vo.app.AppVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */
@Service
public class AppServiceImpl extends AbstractBaseService implements AppService {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private IdService idService;

    @Override
    public App findById(Long id) {
        return id != null ? appRepository.findOne(id) : null;
    }

    @Override
    @Transactional
    public void addOrUpdate(AppVo appVo) {
        AppVoToAppPo appVoToAppPo = new AppVoToAppPo();
        if (appVo.getId() == null) {
            appVo.setId(idService.nextId());
            appRepository.save(appVoToAppPo.apply(appVo));
        } else if (appVo.getId() != null && appRepository.findOne(appVo.getId()) == null) {
            //判断id是否存在于数据库中，防止恶意添加数据(抛异常...)
        } else {
            appRepository.save(appVoToAppPo.apply(appVo));
        }
    }
}
