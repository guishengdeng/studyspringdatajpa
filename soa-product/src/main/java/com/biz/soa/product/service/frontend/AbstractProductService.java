package com.biz.soa.product.service.frontend;

import com.biz.gbck.dao.redis.repository.product.ProductRedisDao;
import com.biz.gbck.dao.redis.repository.product.SimpleSpecialOfferPromotionRedisDao;
import com.biz.gbck.dao.redis.ro.product.master.ProductRO;
import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.dao.redis.ro.product.promotion.SimpleSpecialOfferPromotionRO;
import com.biz.gbck.vo.price.PriceGroupProductCodesPriceReqVO;
import com.biz.gbck.vo.stock.ProductCodesSellerIdStockReqVO;
import com.biz.gbck.vo.stock.ProductStockVO;
import com.biz.service.AbstractBaseService;
import com.biz.soa.product.service.interfaces.IProductPriceService;
import com.biz.soa.product.service.interfaces.IProductStockService;
import com.biz.soa.product.vo.ProductPrototype;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.codelogger.utils.CollectionUtils;

/**
 * 商品Service抽象类
 * Created by david-liu on 2017/05/02 14:59.
 */
public abstract class AbstractProductService extends AbstractBaseService {

    protected IProductStockService stockService;

    protected IProductPriceService priceService;

    protected ProductRedisDao productRedisDao;

    protected SimpleSpecialOfferPromotionRedisDao simpleSpecialOfferPromotionRedisDao;

    protected List<ProductPrototype> getProductPrototype(List<String> productCodes, Long priceGroupId, Long sellerId) {
        Preconditions.checkArgument(priceGroupId != null, "商品价格组ID不能为空");
        Preconditions.checkArgument(sellerId != null, "上级采购单位ID不能为空");
        List<String> productSimpleSpecialOfferIds = productCodes.stream().map(productCode -> String.format("%s%s", priceGroupId, productCode)).collect(Collectors.toList());
        List<ProductRO> productRos = productRedisDao.findByIdsWithNull(productCodes);
        List<PriceRO> priceRos = priceService.productPrices(new PriceGroupProductCodesPriceReqVO(priceGroupId, productCodes));
        List<ProductStockVO> stockVOS = stockService.productStocks(new ProductCodesSellerIdStockReqVO(sellerId, productCodes));
        List<SimpleSpecialOfferPromotionRO> specialOfferPromotionROS = simpleSpecialOfferPromotionRedisDao.findByIdsWithNull(productSimpleSpecialOfferIds);
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(productRos) && productRos.size() == productCodes.size());
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(priceRos) && productCodes.size() == priceRos.size());
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(stockVOS) && productCodes.size() == stockVOS.size());
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(specialOfferPromotionROS) && productCodes.size() == specialOfferPromotionROS.size());
        List<ProductPrototype> productPrototypes = Lists.newArrayList();
        IntStream.range(0, productCodes.size()).forEach(i -> {
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

}
