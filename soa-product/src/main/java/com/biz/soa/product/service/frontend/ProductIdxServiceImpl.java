package com.biz.soa.product.service.frontend;

import com.biz.gbck.dao.redis.repository.product.bbc.PriceRedisDao;
import com.biz.gbck.dao.redis.repository.product.bbc.ProductRedisDao;
import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.depot.DepotPromotionReqVo;
import com.biz.gbck.vo.depot.DepotPromotionRespVo;
import com.biz.gbck.vo.depot.DepotPromotionVo;
import com.biz.gbck.vo.depot.DepotResponseVo;
import com.biz.gbck.vo.product.frontend.ProductStockReqProductVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.gbck.vo.search.*;
import com.biz.service.depot.DepotPromotionService;
import com.biz.service.depot.DepotService;
import com.biz.service.search.ProductIdxService;
import com.biz.soa.product.service.interfaces.impl.TypeAProductPrototype;
import com.biz.soa.product.service.interfaces.impl.TypeBProductPrototype;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品索引ServiceImpl
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
//@Service("productIdxServiceImpl")
public class ProductIdxServiceImpl extends AbstractProductService implements ProductIdxService {
    private static final long serialVersionUID = 1687087661899158754L;

    private static final Logger logger = LoggerFactory.getLogger(ProductIdxServiceImpl.class);

    @Autowired
    private ProductRedisDao productRedisDao;

    @Autowired
    private PriceRedisDao priceRedisDao;

    @Autowired
    private DepotService depotService;

    @Autowired
    private IProductStockService productStockService;

    @Autowired
    private DepotPromotionService depotPromotionService;

    /**
     * 构建商品增量索引
     *
     * @param identityVo 商品增量标识vo
     * @return 商品增量vo
     */
    @Override
    public ProductIdxVo getSearchIdx(ProductIdxIdentityVo identityVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("getSearchIdx identityVo: {}", identityVo);
        }

        if (identityVo == null || StringUtils.isBlank(identityVo.getProductCode())) {
            return null;
        }

        ProductRo productRo = productRedisDao.findOne(identityVo.getProductCode());
        if (productRo == null) {
            return null;
        }

        PriceRo priceRo;
        ProductPrototype productPrototype;
        if (productRo.isTypeA()) {
            priceRo = priceRedisDao.getTypeAPrice(productRo.getProductCode());
            Integer stock = productStockService.getTypeAProductStock(productRo.getProductCode());
            if (priceRo == null) {
                logger.debug("priceRo = null！");
                return null;
            }
            productPrototype = new TypeAProductPrototype(productRo, stock, priceRo, null, null);
        } else {
            String depotCode = identityVo.getDepotCode();
            String warehouseDepotCode = identityVo.getWarehouseDepotCode();
            if (StringUtils.isBlank(depotCode) || StringUtils.isBlank(warehouseDepotCode)) {
                return null;
            }
            List<DepotResponseVo> depotResponseVos = depotService.findDepotsByDepotCodes(Lists.newArrayList(depotCode, warehouseDepotCode));
            DepotResponseVo depotVo = depotResponseVos.get(0);
            DepotResponseVo warehouseDepotVo = depotResponseVos.get(1);
            List<String> typeBPriceIds = Lists.newArrayList(String.format("%s%s", productRo.getProductCode(), depotVo.getAreaNo()), String.format("%s%s", productRo.getProductCode(), warehouseDepotVo.getAreaNo()));
            List<PriceRo> priceRos = priceRedisDao.findByIdsWithNull(typeBPriceIds);
            priceRo = priceRos.get(0);
            PriceRo warehouseDepotPriceRo = priceRos.get(1);
            ProductStockReqProductVo productStockReqProductVo = new ProductStockReqProductVo();
            productStockReqProductVo.setProductCode(productRo.getProductCode());
            productStockReqProductVo.setRapidProduct(productRo.getRapidProduct());
            productStockReqProductVo.setRapidProductItems(productRo.getRapidProductInfoVo());
            TypeBProductStockVo stock = productStockService.getTypeBProductStock(productStockReqProductVo, depotCode, warehouseDepotCode);
            productPrototype = this.buildTypeBProductPrototype(depotVo.getDepotCode(), warehouseDepotVo.getDepotCode(), productRo, stock, priceRo, warehouseDepotPriceRo, null, null);
        }

        return productPrototype == null ? null : productPrototype.toProductIdx();
    }

    @Override
    @Deprecated
    public IdxPageResultVo<ProductIdxVo> getSearchIndices(IdxPageRequestVo vo) {
        return null;
    }

    /**
     * 批量构建商品增量索引(仅提供给B类商品用)
     *
     * @param identityVos 商品增量标识vo vo.productCode必填
     * @return 商品索引Vo集合
     */
    @Override
    public List<ProductIdxVo> getSearchIndices(List<ProductIdxIdentityVo> identityVos) {
        if (CollectionUtils.isEmpty(identityVos)) {
            return Lists.newArrayList();
        }

        List<ProductIdxIdentityVo> vos = Lists.newArrayList();
        for (ProductIdxIdentityVo vo : identityVos) {
            if (vo.getProductType() == VendorTypeEnum.TYPE_B.getValue()) {
                vos.add(vo);
            }
        }

        List<ProductIdxVo> idxVos = Lists.newArrayList();
        for (ProductIdxIdentityVo vo : vos) {
            idxVos.add(this.getSearchIdx(vo));
        }

        return idxVos;
    }

    @Override
    public ProductIdxRespVo getProductIndices(ProductIdxReqVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("getProductIndices reqVo: {}", reqVo);
        }

        if (reqVo.getProductType() == null) {
            logger.warn("no product type parameter, no indices building action, return null directly");
            return null;
        }

        VendorTypeEnum vendorType = reqVo.getProductType() == VendorTypeEnum.TYPE_A.getValue() ? VendorTypeEnum.TYPE_A : VendorTypeEnum.TYPE_B;
        List<String> productCodes = Optional.fromNullable(productRedisDao.getTypeProductCodes(vendorType)).or(Lists.<String>newArrayList());
        List<ProductRo> productRos = Optional.fromNullable(productRedisDao.findByIdsWithNull(productCodes)).or(Lists.<ProductRo>newArrayList());
        logger.info("productCodes size: {}, productRos size: {}", productCodes.size(), productRos.size());

        List<ProductPrototype> prototypes = Lists.newArrayList();
        if (vendorType == VendorTypeEnum.TYPE_A) {
            logger.info("build product indices, vendorType Type_A");
            List<PriceRo> priceRos = Optional.fromNullable(priceRedisDao.getTypeAPrices(productCodes)).or(Lists.<PriceRo>newArrayList());
            List<Integer> stocks = Optional.fromNullable(productStockService.getTypeAProductStock(productCodes)).or(Lists.<Integer>newArrayList());
            Preconditions.checkArgument(productRos.size() == priceRos.size() && priceRos.size() == stocks.size());
            for (int i = 0; i < productRos.size(); i++) {
                PriceRo priceRo = priceRos.get(i);
                Integer stock = stocks.get(i);
                ProductRo productRo = productRos.get(i);
                if (productRo != null && ValueUtils.getValue(stock) > 0 && priceRo != null) {
                    prototypes.add(new TypeAProductPrototype(productRo, stock, priceRo, null, null));
                }
            }
        } else {
            logger.info("build product indices, vendorType Type_B");
            List<DepotResponseVo> depotResponseVos = Optional.fromNullable(depotService.findDepotsByDepotCodes(Lists.newArrayList(reqVo.getDepotCode(), reqVo.getWarehouseDepotCode()))).or(Lists.<DepotResponseVo>newArrayList());
            logger.info("depotResponseVos size: {}", depotResponseVos.size());
            Preconditions.checkArgument(depotResponseVos.size() == 2);
            DepotResponseVo depotVo = depotResponseVos.get(0);
            DepotResponseVo warehouseDepotVo = depotResponseVos.get(1);
            Preconditions.checkArgument(depotVo != null && warehouseDepotVo != null);
            List<String> depotPriceIds = Lists.newArrayList();
            List<String> warehouseDepotPriceIds = Lists.newArrayList();
            List<DepotPromotionReqVo> depotPromotionReqVos = Lists.newArrayList();
            for (String productCode : productCodes) {
                depotPriceIds.add(priceRedisDao.getPriceRoId(productCode, depotVo.getAreaNo()));
                warehouseDepotPriceIds.add(priceRedisDao.getPriceRoId(productCode, warehouseDepotVo.getAreaNo()));
                depotPromotionReqVos.add(new DepotPromotionReqVo(productCode, reqVo.getDepotCode(), reqVo.getWarehouseDepotCode()));
            }

            if (logger.isDebugEnabled()) {
                logger.debug("depotPriceIds size: {}, warehouseDepotPriceIds size: {}", depotPriceIds.size(), warehouseDepotPriceIds.size());
            }
            List<PriceRo> depotPriceRos = Optional.fromNullable(priceRedisDao.findByIdsWithNull(depotPriceIds)).or(Lists.<PriceRo>newArrayList());
            List<PriceRo> warehousePriceRos = Optional.fromNullable(priceRedisDao.findByIdsWithNull(warehouseDepotPriceIds)).or(Lists.<PriceRo>newArrayList());
            logger.info("depot priceRos size: {}, warehouse depot priceRos size: {}", depotPriceRos.size(), warehousePriceRos.size());

            List<ProductStockReqProductVo> productStockReqProductVos = this.getProductStockReqVos(productRos);
            List<TypeBProductStockVo> stockVos = Optional.fromNullable(productStockService.getTypeBProductStock(productStockReqProductVos, depotVo.getDepotCode(), warehouseDepotVo.getWarehouseCode())).or(Lists.<TypeBProductStockVo>newArrayList());
            logger.info("stockVos size: {}", stockVos.size());
            List<DepotPromotionRespVo> depotPromotionRespVos = Optional.fromNullable(depotPromotionService.depotPromotions(depotPromotionReqVos)).or(Lists.<DepotPromotionRespVo>newArrayList());
            logger.info("depotPromotionRespVos size: {}", depotPromotionRespVos.size());
            Preconditions.checkArgument(productCodes.size() == depotPriceRos.size() &&
                    depotPriceRos.size() == warehousePriceRos.size() &&
                    warehousePriceRos.size() == stockVos.size() &&
                    stockVos.size() == depotPromotionRespVos.size());
            for (int i = 0; i < productRos.size(); i++) {
                ProductRo productRo = productRos.get(i);
                PriceRo depotPriceRo = depotPriceRos.get(i);
                PriceRo warehousePriceRo = warehousePriceRos.get(i);
                TypeBProductStockVo stockVo = stockVos.get(i);
                boolean isStockValid = stockVo != null && (ValueUtils.getValue(stockVo.getDepotStock()) > 0 || ValueUtils.getValue(stockVo.getProvinceStock()) > 0);
                if (productRo != null && depotPriceRo != null && warehousePriceRo != null && isStockValid) {
                    DepotPromotionRespVo depotPromotionRespVo = depotPromotionRespVos.get(i);
                    DepotPromotionVo depotPromotionVo = depotPromotionRespVo.getDepotPromotionVo();
                    DepotPromotionVo warehouseDepotPromotionVo = depotPromotionRespVo.getWarehouseDepotPromotionVo();
                    prototypes.add(new TypeBProductPrototype(reqVo.getDepotCode(), reqVo.getWarehouseDepotCode(), productRo, depotPriceRo, warehousePriceRo, stockVo, depotPromotionVo, warehouseDepotPromotionVo, null, null));
                }
            }
        }

        ProductIdxRespVo respVo = new ProductIdxRespVo();
        respVo.setProductType(reqVo.getProductType());
        List<ProductIdxVo> idxVos = Lists.newArrayList();
        logger.info("product prototype size: {}", prototypes.size());
        for (ProductPrototype productPrototype : prototypes) {
            if (productPrototype != null) {
                idxVos.add(productPrototype.toProductIdx());
            }
        }

        respVo.setDepotCode(reqVo.getDepotCode());
        respVo.setIndices(idxVos);
        return respVo;
    }
}
