package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.dao.redis.repository.product.PriceRedisDao;
import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.vo.price.PriceGroupProductCodePriceReqVO;
import com.biz.gbck.vo.price.PriceGroupProductCodesPriceReqVO;
import com.biz.gbck.vo.price.PriceGroupsProductCodePriceReqVO;
import com.biz.soa.product.service.interfaces.IProductPriceService;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * 商品价格ServiceImpl
 *
 * Created by david-liu on 2017/05/04 09:11.
 */
@Service
public class ProductPriceServiceImpl implements IProductPriceService {

    private static final Logger logger = LoggerFactory.getLogger(ProductPriceServiceImpl.class);

    @Autowired
    private PriceRedisDao priceRedisDao;

    @Override
    public List<PriceRO> productPrices(PriceGroupProductCodesPriceReqVO reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("product prices reqVo: {}", reqVo);
        }

        Preconditions.checkArgument(reqVo != null, "请求参数不能为空");
        Preconditions.checkArgument(reqVo.getPriceGroupId() != null, "价格组ID不能为空");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(reqVo.getProductIds()), "商品ID集合不能为空");
        List<String> priceROIds = reqVo.getProductIds().stream().map(productId -> String.format("%s%s", productId, reqVo.getPriceGroupId())).collect(Collectors.toList());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("fetch price ros");
        List<PriceRO> priceROS = priceRedisDao.findByIdsWithNull(priceROIds);
        stopWatch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug("price ros: {}", priceROS);
            logger.debug(stopWatch.prettyPrint());
        }
        return priceROS;
    }

    @Override
    public PriceRO productPrice(PriceGroupProductCodePriceReqVO reqVO) {
        if (logger.isDebugEnabled()) {
            logger.debug("product prices reqVo: {}", reqVO);
        }

        Preconditions.checkArgument(reqVO != null, "请求参数不能为空");
        Preconditions.checkArgument(reqVO.getPriceGroupId() != null, "价格组ID不能为空");
        Preconditions.checkArgument(reqVO.getProductId() != null, "商品ID不能为空");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("fetch price ro");
        PriceRO priceRO = priceRedisDao.findOne(String.format("%s%s", reqVO.getProductId(), reqVO.getPriceGroupId()));
        stopWatch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug("price ro: {}", priceRO);
            logger.debug(stopWatch.prettyPrint());
        }
        return priceRO;
    }

    @Override
    public List<PriceRO> productPrices(PriceGroupsProductCodePriceReqVO reqVO) {
        if (logger.isDebugEnabled()) {
            logger.debug("product prices reqVO: {}", reqVO);
        }

        Preconditions.checkArgument(CollectionUtils.isNotEmpty(reqVO.getPriceGroupIds()), "价格组ID集合不能为空");
        Preconditions.checkArgument(reqVO.getProductId() != null, "商品ID不能为空");

        List<String> priceROIds = reqVO.getPriceGroupIds().stream().map(priceGroupId -> String.format("%s%s", reqVO.getProductId(), priceGroupId)).collect(Collectors.toList());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("fetch price ros");
        List<PriceRO> priceROS = priceRedisDao.findByIdsWithNull(priceROIds);
        if (logger.isDebugEnabled()) {
            logger.debug("price ros: {}", priceROS);
            logger.debug(stopWatch.prettyPrint());
        }
        return priceROS;
    }
}
