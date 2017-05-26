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
            app.setTel(vo.getTel().trim());
            app.setPictureUrl(vo.getPictureUrl().trim());
            app.setUrl(vo.getUrl().trim());
            app.setHotKeyWord(vo.getHotKeyWord().trim());
            app.setContent(vo.getContent().trim());
            app.setTel(vo.getTel().trim());
            app.setTitle(vo.getTitle().trim());
            app.setIcon(vo.getIcon().trim());
            app.setShareUrl(vo.getShareUrl().trim());
            app.setRecommedUrl(vo.getRecommedUrl().trim());
            app.setAppDownloadUrl(vo.getAppDownloadUrl().trim());
            app.setAmount(vo.getAmount());
            app.setTabOne(vo.getTabOne().trim());
            app.setTabTwo(vo.getTabTwo().trim());
        }
        return app;
    }
}
