package com.biz.service.advertisement;


import com.alibaba.fastjson.JSON;
import com.biz.core.page.PageResult;
import com.biz.gbck.advertisement.frontend.AdvertisementQueryParamVo;
import com.biz.gbck.advertisement.frontend.AdvertisementVo;
import com.biz.gbck.dao.redis.repository.advertisement.AdvertisementRedisDao;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.biz.service.AbstractBaseService;
import com.biz.service.advertisementtransformer.AdvertisementRo2AdvertisementVo;
import com.biz.service.advertisementtransformer.AdvertisementVo2AdvertisementRo;
import com.biz.service.advertisement.interfaces.AdvertisementService;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */
@Service
public class AdvertisementServiceImpl extends AbstractBaseService implements AdvertisementService{

    @Autowired
    private AdvertisementRedisDao advertisementRedisDao;

    @Override
    public void saveOrUpdateAdvertisement(AdvertisementVo req) {
        //TODO 定义异常信息
        String advertisementId = req.getId();
        AdvertisementRo ro;
        //如果没有id 则进行保存操作
        if (StringUtils.isBlank(advertisementId)) {
            req.setId(String.valueOf(idService.nextId()));
            ro = new AdvertisementVo2AdvertisementRo().apply(req);
        }else {
            ro = advertisementRedisDao.findOne(req.getId());
            buildAdvertisementRo(ro,req);
        }
        advertisementRedisDao.save(ro);
    }

    @Override
    public PageResult<AdvertisementVo> findAdvertisement(AdvertisementQueryParamVo req) {
        logger.debug("find advertisement by param [{}]", JSON.toJSONString(req));
        String picturesLink = req.getPicturesLink();
        //TODO 分页对象
        //PageRequest page = new PageRequest(req.getPage() - 1, req.getSize(), new Sort(Sort.Direction.DESC, "createTimestamp"));
        return null;
    }

    @Override
    public List<AdvertisementVo> findAllAdvertisements() {
        List<AdvertisementRo> ros = advertisementRedisDao.findAll();
        return Lists.transform(ros, new AdvertisementRo2AdvertisementVo());
    }

    public AdvertisementRo buildAdvertisementRo(AdvertisementRo ro,AdvertisementVo vo) {
        if (vo == null){
            return null;
        }
        BeanUtils.copyProperties(ro, vo);
        return ro;
    }
}
