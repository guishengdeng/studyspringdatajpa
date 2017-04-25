package com.biz.service.advertisement;


import com.alibaba.fastjson.JSON;
import com.biz.core.asserts.SystemAsserts;
import com.biz.core.page.PageResult;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.advertisement.frontend.AdvertisementQueryParamVo;
import com.biz.gbck.vo.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.vo.advertisement.frontend.request.AdvertisementRequestVo;
import com.biz.gbck.dao.redis.repository.advertisement.AdvertisementRedisDao;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.biz.service.AbstractBaseService;
import com.biz.gbck.transform.advertisement.AdvertisementRo2AdvertisementVo;
import com.biz.gbck.transform.advertisement.AdvertisementRequestVo2AdvertisementRo;
import com.biz.service.advertisement.interfaces.AdvertisementService;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Collections;
import java.util.List;

import static com.biz.gbck.transform.advertisement.AdvertisementRequestVo2AdvertisementRo.StringToTimestamp;
import static com.biz.gbck.transform.advertisement.AdvertisementRequestVo2AdvertisementRo.StringToTimestamp2;

/**
 * Created by xys on 2017/4/18.
 */
@Service
public class AdvertisementServiceImpl extends AbstractBaseService implements AdvertisementService{

    @Autowired
    private AdvertisementRedisDao advertisementRedisDao;

    @Override
    public void saveOrUpdateAdvertisement(AdvertisementRequestVo req) {
        //TODO 定义异常信息
        String advertisementId = req.getId();
        AdvertisementRo ro;
        //如果没有id 则进行保存操作
        if (StringUtils.isBlank(advertisementId)) {
            req.setId(String.valueOf(idService.nextId()));
            //验证是否上传图片
            String icon = req.getPicturesLink();
            SystemAsserts.isTrue(StringUtils.isNotBlank(icon), "请上传广告图片！");
            ro = new AdvertisementRequestVo2AdvertisementRo().apply(req);
        }else {
             ro = advertisementRedisDao.findOne(req.getId());
            buildAdvertisementRo(ro,req);
        }
        advertisementRedisDao.save(ro);

    }

    @Override
    public PageResult<AdvertisementVo> findAdvertisement(AdvertisementQueryParamVo req) {
        logger.debug("find advertisement by param [{}]", JSON.toJSONString(req));
        //TODO 分页对象
        //PageRequest page = new PageRequest(req.getPage() - 1, req.getSize(), new Sort(Sort.Direction.DESC, "createTimestamp"));
        return null;
    }

    @Override
    public List<AdvertisementVo> findAllAdvertisements() {
        List<AdvertisementRo> ros = advertisementRedisDao.findAll();
        return Lists.transform(ros, new AdvertisementRo2AdvertisementVo());
    }

    @Override
    public AdvertisementRo findById(String id) {
        if (StringUtils.isNotBlank(id)){
            return advertisementRedisDao.findOne(id);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        AdvertisementRo ro = advertisementRedisDao.findOne(id);
        advertisementRedisDao.delete(ro);
    }

    @Override
    @Transactional
    public void disable(String id) {
        AdvertisementRo ro = advertisementRedisDao.findOne(id);
        if (ro != null) {
            ro.setStatus(CommonStatusEnum.DISABLE.getValue());
            advertisementRedisDao.save(ro);
        }
    }

    @Override
    public List<AdvertisementVo> findAdvertisementByStatus(Integer status) {
        List<AdvertisementRo> ros = advertisementRedisDao.findAdvertisementByStatus(CommonStatusEnum.ENABLE.getValue());
        Collections.sort(ros);
        return Lists.transform(ros, new AdvertisementRo2AdvertisementVo());
    }

    public AdvertisementRo buildAdvertisementRo(AdvertisementRo ro,AdvertisementRequestVo vo) {
        if (vo == null){
            return null;
        }
        if(vo.getBeginTimestamp() != null){
            ro.setBeginTimestamp(StringToTimestamp(vo.getBeginTimestamp()));
        }
        if(vo.getEndTimestamp()!= null){
            ro.setEndTimestamp(StringToTimestamp2(vo.getEndTimestamp()));
        }
        ro.setClickLink(vo.getClickLink());
        ro.setPicturesLink(vo.getPicturesLink());
        ro.setPriority(vo.getPriority());
        ro.setResidenceTime(vo.getResidenceTime());
        return ro;
    }
}
