package com.biz.service.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.vo.app.AppVo;


/**
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */
public interface AppService {

    App findById(Long id);

    void addOrUpdate(AppVo appVo);

}
