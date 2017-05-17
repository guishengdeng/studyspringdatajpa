package com.biz.service.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.vo.app.AppVo;
import com.biz.gbck.vo.config.AppConfigVo;
import com.biz.gbck.vo.config.CategoryResVo;

import java.util.List;



/**
 * @author lzz
 * @date 2017年04月18日
 * @reviewer
 */
public interface AppService {

    App findById(Long id);

    void addOrUpdate(AppVo appVo);

    AppVo findLastData();

    /**
     * 返回启动上报后台配置需要参数
     * @return
     */
    AppConfigVo getAppConfigVo();

    /**
     * 获取启动上报商品类型集合
     * @return
     */
    List<CategoryResVo> getCategories();
}
