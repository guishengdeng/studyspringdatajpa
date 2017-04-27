package com.biz.gbck.transform.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.vo.app.AppVo;
import com.google.common.base.Function;

import java.io.Serializable;

/**
 * Created by lzz on 2017/4/26.
 */
public class AppVoToAppPo implements Function<AppVo, App>, Serializable {

    @Override
    public App apply(AppVo vo) {
        App app = null;
        if (null != vo) {
            app = new App();
            app.setId(vo.getId());
            app.setTel(vo.getTel());
            app.setPictureUrl(vo.getPictureUrl());
            app.setUrl(vo.getUrl());
            app.setHotKeyWord(vo.getHotKeyWord());
            app.setContent(vo.getContent());
            app.setTel(vo.getTel());
            app.setTitle(vo.getTitle());
            app.setIcon(vo.getIcon());
            app.setShareUrl(vo.getShareUrl());
            app.setRecommedUrl(vo.getRecommedUrl());
            app.setAppDownloadUrl(vo.getAppDownloadUrl());
            app.setMoney(vo.getMoney());
            app.setTabOne(vo.getTabOne());
            app.setTabTwo(vo.getTabTwo());
            app.setMinNum(vo.getMinNum());
            app.setMaxNum(vo.getMaxNum());
            app.setType(vo.getType());
            app.setPoint(vo.isPoint());
        }
        return app;
    }
}
