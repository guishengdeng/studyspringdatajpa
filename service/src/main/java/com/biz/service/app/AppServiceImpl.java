package com.biz.service.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.repository.app.AppRepository;
import com.biz.gbck.transform.app.AppVoToAppPo;
import com.biz.gbck.vo.app.AppVo;
import com.biz.gbck.vo.config.AppConfigVo;
import com.biz.gbck.vo.config.CategoryResVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.CategoryService;
import com.biz.transformer.config.AppToAppConfigVo;
import com.biz.transformer.config.CategoryToCategoryResVo;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.codelogger.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired(required = false)
    private CategoryService categoryService;


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
            app.setTel(appVo.getTel().trim());
            app.setPictureUrl(appVo.getPictureUrl().trim());
            app.setUrl(appVo.getUrl().trim());
            app.setHotKeyWord(appVo.getHotKeyWord().trim());
            app.setContent(appVo.getContent().trim());
            app.setTel(appVo.getTel().trim());
            app.setTitle(appVo.getTitle().trim());
            app.setIcon(appVo.getIcon().trim());
            app.setShareUrl(appVo.getShareUrl().trim());
            app.setRecommedUrl(appVo.getRecommedUrl().trim());
            app.setAppDownloadUrl(appVo.getAppDownloadUrl().trim());
            app.setAmount(appVo.getAmount());
            app.setTabOne(appVo.getTabOne().trim());
            app.setTabTwo(appVo.getTabTwo().trim());
            appRepository.save(app);
        } else {
            //id不为空且数据库中没有找到相对应的数据（异常处理）
        }

    }

    @Override
    public AppVo findLastData() {
        Long id = null;
        AppVo appVo = new AppVo();
        if (appRepository.findLastData() != null && appRepository.findLastData().size() != 0) {
            id = Long.valueOf(String.valueOf(appRepository.findLastData().get(0)));
            App app = appRepository.findOne(id);
            appVo.setId(app.getId());
            appVo.setTel(app.getTel());
            appVo.setPictureUrl(app.getPictureUrl());
            appVo.setUrl(app.getUrl());
            appVo.setHotKeyWord(app.getHotKeyWord());
            appVo.setContent(app.getContent());
            appVo.setTel(app.getTel());
            appVo.setTitle(app.getTitle());
            appVo.setIcon(app.getIcon());
            appVo.setShareUrl(app.getShareUrl());
            appVo.setRecommedUrl(app.getRecommedUrl());
            appVo.setAppDownloadUrl(app.getAppDownloadUrl());
            appVo.setAmount(app.getAmount());
            appVo.setTabOne(app.getTabOne());
            appVo.setTabTwo(app.getTabTwo());
        }
        return id != null ? appVo : null;
    }
    public AppConfigVo getAppConfigVo() {
        App app= CollectionUtils.getFirstNotNullValue(appRepository.findAll());
        return new AppToAppConfigVo().apply(app);
    }

    @Override
    public List<CategoryResVo> getCategories() {
        List<Category> categories=categoryService.findCategoryByStatus();
        return Lists.transform(categories,new CategoryToCategoryResVo());
    }

}
