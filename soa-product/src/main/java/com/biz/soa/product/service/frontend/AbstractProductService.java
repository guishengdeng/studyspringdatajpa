package com.biz.soa.product.service.frontend;

import com.biz.gbck.dao.redis.repository.product.PriceGroupRedisDao;
import com.biz.gbck.dao.redis.repository.product.ProductRedisDao;
import com.biz.gbck.dao.redis.repository.product.SimpleSpecialOfferPromotionRedisDao;
import com.biz.gbck.dao.redis.ro.product.master.ProductRO;
import com.biz.gbck.dao.redis.ro.product.price.PriceGroupRO;
import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.dao.redis.ro.product.promotion.SimpleSpecialOfferPromotionRO;
import com.biz.gbck.vo.price.PriceGroupProductCodePriceReqVO;
import com.biz.gbck.vo.price.PriceGroupProductCodesPriceReqVO;
import com.biz.gbck.vo.price.PriceGroupsProductCodePriceReqVO;
import com.biz.gbck.vo.stock.ProductCodeSellerIdStockReqVO;
import com.biz.gbck.vo.stock.ProductCodesSellerIdStockReqVO;
import com.biz.gbck.vo.stock.ProductStockVO;
import com.biz.service.AbstractBaseService;
import com.biz.soa.product.service.interfaces.IProductPriceService;
import com.biz.soa.product.service.interfaces.IProductStockService;
import com.biz.soa.product.vo.ProductPrototype;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.codelogger.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

/**
 * 商品Service抽象类
 * Created by david-liu on 2017/05/02 14:59.
 */
public abstract class AbstractProductService extends AbstractBaseService {

    @Autowired
    protected IProductStockService stockService;

    @Autowired
    protected IProductPriceService priceService;

    @Autowired
    protected ProductRedisDao productRedisDao;

    @Autowired
    protected SimpleSpecialOfferPromotionRedisDao simpleSpecialOfferPromotionRedisDao;

    @Autowired
    protected PriceGroupRedisDao priceGroupRedisDao;

    protected List<ProductPrototype> getProductPrototype(List<Long> productIds, Long priceGroupId, Long sellerId) {
        Preconditions.checkArgument(priceGroupId != null, "商品价格组ID不能为空");
        Preconditions.checkArgument(sellerId != null, "上级采购单位ID不能为空");
        List<String> productSimpleSpecialOfferIds = productIds.stream().map(productCode -> String.format("%s%s", priceGroupId, productCode)).collect(Collectors.toList());
        List<ProductRO> productRos = productRedisDao.findByIdsWithNull(productIds);
        List<PriceRO> priceRos = priceService.productPrices(new PriceGroupProductCodesPriceReqVO(priceGroupId, productIds));
        List<ProductStockVO> stockVOS = stockService.productStocks(new ProductCodesSellerIdStockReqVO(sellerId, productIds));
        List<SimpleSpecialOfferPromotionRO> specialOfferPromotionROS = simpleSpecialOfferPromotionRedisDao.findByIdsWithNull(productSimpleSpecialOfferIds);
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(productRos) && productRos.size() == productIds.size());
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(priceRos) && productIds.size() == priceRos.size());
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(stockVOS) && productIds.size() == stockVOS.size());
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(specialOfferPromotionROS) && productIds.size() == specialOfferPromotionROS.size());
        List<ProductPrototype> productPrototypes = Lists.newArrayList();
        IntStream.range(0, productIds.size()).forEach(i -> {
            ProductRO productRO = productRos.get(i);
            PriceRO priceRO = priceRos.get(i);
            ProductStockVO stockVO = stockVOS.get(i);
            if (productRO != null && priceRO != null && stockVO != null) {
                SimpleSpecialOfferPromotionRO simpleSpecialOfferPromotionRO = specialOfferPromotionROS.get(i);
                productPrototypes.add(new ProductPrototype(priceGroupId, productRO, priceRO, stockVO, simpleSpecialOfferPromotionRO));
            }
        });
        return productPrototypes;
    }

    protected ProductPrototype getProductPrototype(Long productId, Long priceGroupId, Long sellerId) {
        Preconditions.checkArgument(productId != null, "商品ID不能为空");
        Preconditions.checkArgument(priceGroupId != null, "商品价格组ID不能为空");
        Preconditions.checkArgument(sellerId != null, "上级采购单位ID不能为空");
        ProductRO productRO = productRedisDao.findOne(productId);
        PriceRO priceRO = priceService.productPrice(new PriceGroupProductCodePriceReqVO(priceGroupId, productId, sellerId));
        ProductStockVO stockVO = stockService.productStock(new ProductCodeSellerIdStockReqVO(productId, sellerId));
        SimpleSpecialOfferPromotionRO simpleSpecialOfferPromotionRO = simpleSpecialOfferPromotionRedisDao.findOne(String.format("%s%s", priceGroupId, productId));
        return new ProductPrototype(priceGroupId, productRO, priceRO, stockVO, simpleSpecialOfferPromotionRO);
    }

    protected List<ProductPrototype> getProductPrototype(Long productId) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("fetch product ro");
        ProductRO productRO = productRedisDao.findOne(productId);
        stopWatch.stop();
        stopWatch.start("fetch price group ids");
        List<PriceGroupRO> priceGroupROS = Optional.ofNullable(priceGroupRedisDao.findAll())
                .orElse(Lists.newArrayList())
                .stream().filter(Objects::nonNull).collect(Collectors.toList());
        stopWatch.stop();
        List<Long> priceGroupIds = Lists.newArrayList();
        List<ProductCodeSellerIdStockReqVO> stockReqVOS = Lists.newArrayList();
        priceGroupROS.forEach(priceGroupRO -> {
            priceGroupIds.add(priceGroupRO.getPriceGroupId());
            stockReqVOS.add(new ProductCodeSellerIdStockReqVO(productId, priceGroupRO.getSellerId()));
        });
        stopWatch.start("fetch product stocks");
        List<ProductStockVO> stockVOS = stockService.productStocks(stockReqVOS);
        stopWatch.stop();
        stopWatch.start("fetch product prices");
        List<PriceRO> priceROS = priceService.productPrices(new PriceGroupsProductCodePriceReqVO(priceGroupIds, productId));
        stopWatch.stop();
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(priceROS) && CollectionUtils.isNotEmpty(stockVOS) && priceROS.size() == stockVOS.size() && stockVOS.size() == priceGroupIds.size());
        List<ProductPrototype> prototypes = Lists.newArrayList();
        IntStream.range(0, priceROS.size()).forEach(i -> prototypes.add(new ProductPrototype(priceGroupIds.get(i), productRO, priceROS.get(i), stockVOS.get(i), null)));
        logger.info(stopWatch.prettyPrint());
        return prototypes.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

}
