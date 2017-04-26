package com.biz.service.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.dao.mysql.repository.app.AppRepository;
import com.biz.service.AbstractBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */
@Service
public class AppServiceImpl extends AbstractBaseService implements AppService {

    @Autowired
    private AppRepository appRepository;
    @Override
    public App findById(String id) {
        if (StringUtils.isNotBlank(id)) {
            return appRepository.findOne(id);
        } else {
            return null;
        }
    }
    @Override
    public void addOrUpdate(App app) {
        if (StringUtils.isBlank(String.valueOf(app.getId()))){
             app.setId(idService.nextId());
        }
        appRepository.save(app);
    }
}
