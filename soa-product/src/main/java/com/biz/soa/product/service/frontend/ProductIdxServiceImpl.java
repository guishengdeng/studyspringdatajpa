package com.biz.soa.product.service.frontend;

import com.biz.gbck.vo.search.IncrProductIdxReqVo;
import com.biz.gbck.vo.search.ProductIdxVO;
import com.biz.gbck.vo.search.TotalProductIdxReqVo;
import com.biz.service.ProductIdxService;
import com.biz.soa.product.vo.ProductPrototype;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * ProductIdxServiceImpl
 *
 * Created by david-liu on 2017/05/03 12:11.
 */
public class ProductIdxServiceImpl extends AbstractProductService implements ProductIdxService {

    private static final Logger logger = LoggerFactory.getLogger(ProductIdxServiceImpl.class);

    @Override
    public List<ProductIdxVO> getProductIndices(TotalProductIdxReqVo reqVo) {
        StopWatch stopWatch = new StopWatch("获取全量索引VO");
        stopWatch.start("getProductIndices");
        if (logger.isDebugEnabled()) {
            logger.debug("get product indices reqVo: {}", reqVo);
        }
        Long priceGroupId = reqVo.getPriceGroupId(), sellerId = reqVo.getSellerId();
        List<ProductPrototype> prototypes = this.getProductPrototype(reqVo.getProductIds(), priceGroupId, sellerId);
        List<ProductIdxVO> idxVOS = prototypes.stream().filter(Objects::nonNull).map(ProductPrototype::toProductIdxVO).collect(Collectors.toList());
        logger.info("after build product indices, count: {}", idxVOS.size());
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return idxVOS;
    }

    @Override
    public List<ProductIdxVO> getProductIndices(IncrProductIdxReqVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("get increment product indices reqVo: {}", reqVo);
        }
        Long priceGroupId = reqVo.getPriceGroupId(), sellerId = reqVo.getSellerId();
        StopWatch stopWatch = new StopWatch("获取增量索引VO");
        stopWatch.start();
        List<ProductPrototype> prototypes;
        if (priceGroupId != null) {
            Preconditions.checkArgument(sellerId != null, "上级采购方ID不能为空");
            ProductPrototype prototype = this.getProductPrototype(reqVo.getProductId(), priceGroupId, sellerId);
            prototypes = Lists.newArrayList();
            prototypes.add(prototype);
        } else {
            prototypes = this.getProductPrototype(reqVo.getProductId());
        }
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return prototypes.stream().map(ProductPrototype::toProductIdxVO).collect(Collectors.toList());
    }

}
