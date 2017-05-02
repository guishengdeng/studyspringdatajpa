package com.biz.service.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.dao.mysql.repository.app.AppRepository;
import com.biz.gbck.transform.app.AppVoToAppPo;
import com.biz.gbck.vo.app.AppVo;
import com.biz.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Objects;
import static org.codelogger.utils.ExceptionUtils.iae;


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
    public App findById(Long id) {
        return id != null ? appRepository.findOne(id) : null;
    }

    @Override
    @Transactional
    public void addOrUpdate(AppVo appVo) {
        iae.throwIfNull(appVo, "参数不能为空");
        AppVoToAppPo appVoToAppPo = new AppVoToAppPo();
        App app = findById(appVo.getId());
        if (appVo.getId() == null) {
            appVo.setId(idService.nextId());
            appRepository.save(appVoToAppPo.apply(appVo));
        } else if (appVo.getId() != null && Objects.equals(appVo.getId(), app.getId())) {
            app.setId(appVo.getId());
            app.setTel(appVo.getTel());
            app.setPictureUrl(appVo.getPictureUrl());
            app.setUrl(appVo.getUrl());
            app.setHotKeyWord(appVo.getHotKeyWord());
            app.setContent(appVo.getContent());
            app.setTel(appVo.getTel());
            app.setTitle(appVo.getTitle());
            app.setIcon(appVo.getIcon());
            app.setShareUrl(appVo.getShareUrl());
            app.setRecommedUrl(appVo.getRecommedUrl());
            app.setAppDownloadUrl(appVo.getAppDownloadUrl());
            app.setAmount(appVo.getAmount());
            app.setTabOne(appVo.getTabOne());
            app.setTabTwo(appVo.getTabTwo());
            app.setMinNum(appVo.getMinNum());
            app.setMaxNum(appVo.getMaxNum());
            app.setType(appVo.getType());
            app.setPoint(appVo.isPoint());
            appRepository.save(app);
        } else {
            //id不为空且数据库中没有找到相对应的数据（异常处理）
        }

    }
}
