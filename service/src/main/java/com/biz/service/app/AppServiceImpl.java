package com.biz.service.app;

import com.biz.core.util.StringUtil;
import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.dao.mysql.repository.app.AppRepository;
import com.biz.service.AbstractBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            App app= appRepository.findOne(id);
            return app;
        } else {
            return null;
        }
    }

    @Override
    public void insert(App apps) {
        if (StringUtils.isBlank(apps.getId())){
         String id =""+idService.nextId();
         apps.setId(id);
        }
        appRepository.save(apps);


    }



}
