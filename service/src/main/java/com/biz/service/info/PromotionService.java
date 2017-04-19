package com.biz.service.info;

import com.biz.gbck.common.spring.DepotnearbyTransactionManager;
import com.biz.gbck.dao.mysql.po.info.PromotionPo;
import com.biz.gbck.dao.mysql.repository.info.PromotionRepository;
import com.biz.gbck.dao.redis.info.PromotionRedisDao;
import com.biz.gbck.dao.redis.ro.info.PromotionRo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.transform.PromotionPoToPromotionRo;
import com.biz.service.CommonService;
import org.codelogger.utils.CollectionUtils;
import org.codelogger.utils.JudgeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * 活动中心
 *
 * @author gongshutao
 */
@Service
public class PromotionService extends CommonService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionRedisDao promotionRedisDao;


    @Transactional
    public void save(final PromotionPo po) {
        if (po == null) {
            throw new IllegalArgumentException("PromotionPo is null");
        }
        if (po.getId() == null)
            po.setId(idService.nextId());

        final PromotionPo savedPo = promotionRepository.save(po);
        DepotnearbyTransactionManager.doWhenTransactionalSuccess(new DepotnearbyTransactionManager.Task() {
            @Override public void justDoIt() {
                if (savedPo.getShowInApp() != null && savedPo.getShowInApp()) {
                    promotionRedisDao.save(new PromotionPoToPromotionRo().apply(savedPo));
                } else {
                    promotionRedisDao.delete(savedPo.getId());
                }
            }
        });

    }

    public List<PromotionPo> findNormal(Pageable pageable) {
        return promotionRepository.findAllNormal(CommonStatusEnum.ENABLE.getValue());
    }

    public PromotionPo get(Long id) {
        return promotionRepository.getOne(id);
    }

    @Transactional
    public void delete(Long id) {
        PromotionPo po = promotionRepository.getOne(id);
        if (po != null) {
            po.setStatus(CommonStatusEnum.DISABLE.getValue());
            po.setShowInApp(false);
            save(po);
        }
    }

    public List<PromotionRo> findAllShowInApp() {
        return promotionRedisDao.findAll();
    }

    /**
     * 用户活动中心是否有更新
     */
    public Boolean showRedPoint(Long id) {
        if (id == null) {
            return false;
        }
        Date userLatestUpdateTime = promotionRedisDao.getUserLatestUpdateTime(id);
        if (userLatestUpdateTime == null) {
            return CollectionUtils.isNotEmpty(findAllShowInApp());
        }
        Date systemLatestUpdateTime = promotionRedisDao.getSystemLatestUpdateTime();
        if (systemLatestUpdateTime == null) {
            return CollectionUtils.isNotEmpty(findAllShowInApp());
        }
        return JudgeUtils.lessThan(userLatestUpdateTime, systemLatestUpdateTime) && CollectionUtils
            .isNotEmpty(findAllShowInApp());
    }

    /**
     * 更新用户活动获取时间为当前时间
     */
    public void updateUserLatestFetchTime(Long userId){
        promotionRedisDao.updateUserLatestFetchTime(userId);
    }
}
