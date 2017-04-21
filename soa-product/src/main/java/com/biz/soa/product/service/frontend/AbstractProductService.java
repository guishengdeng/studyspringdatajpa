package com.biz.soa.product.service.frontend;

import com.biz.core.util.DistanceCalc;
import com.biz.core.util.StringTool;
import com.biz.gbck.dao.redis.repository.product.bbc.PriceRedisDao;
import com.biz.gbck.dao.redis.repository.product.bbc.ProductRedisDao;
import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.enums.product.ProductShowStatus;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.IndexRangeVo;
import com.biz.gbck.vo.depot.DepotPromotionReqVo;
import com.biz.gbck.vo.depot.DepotPromotionRespVo;
import com.biz.gbck.vo.depot.DepotPromotionVo;
import com.biz.gbck.vo.depot.DepotResponseVo;
import com.biz.gbck.vo.product.BatchVo;
import com.biz.gbck.vo.product.ProductTypeWithIndexVo;
import com.biz.gbck.vo.product.TypeProductsGroupVo;
import com.biz.gbck.vo.product.frontend.ProductStockReqProductVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.gbck.vo.search.SearchProductConditionVo;
import com.biz.redis.util.RedisUtil;
import com.biz.service.depot.DepotPromotionService;
import com.biz.service.depot.DepotService;
import com.biz.service.product.frontend.IProductSearchService;
import com.biz.soa.product.service.interfaces.ProductValidator;
import com.biz.soa.product.service.interfaces.impl.TypeAProductPrototype;
import com.biz.soa.product.service.interfaces.impl.TypeBProductPrototype;
import com.biz.soa.product.vo.BuildProductPrototypesVo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.biz.gbck.dao.redis.repository.product.bbc.SaleAreaRedisDao.TYPE_A_PRICE_AREA_NO;

/**
 * 商品搜索抽象方法
 *
 * @author david-liu
 * @date 2017年01月12日
 * @reviewer
 * @see
 */
public abstract class AbstractProductService implements Serializable {

    private static final long serialVersionUID = -4333739581821175934L;

    private static final Logger logger = LoggerFactory.getLogger(AbstractProductService.class);

    @Autowired
    protected IProductSearchService productSearchService;
    @Autowired
    protected DepotPromotionService depotPromotionService;
    @Autowired
    private PriceRedisDao priceRedisDao;
    @Autowired
    private ProductRedisDao productRedisDao;
    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private DepotService depotService;

    /**
     * 通过商品搜索 Vo 执行搜索方法
     *
     * @param vo 商品搜索 Vo
     * @return 商品搜索结果
     */
    protected ProductSearchResultVo<ProductSearchResultEntityVo> doSearch(SearchProductConditionVo vo) {
        return this.productSearchService.searchProduct(vo);
    }

    /**
     * 构造 A 类商品商品数据集合
     *
     * @param ros 商品 Ro 列表
     * @param stocks 商品库存（全国库存）
     * @param priceRos 价格Ro列表
     * @return 商品数据列表
     */
    protected List<ProductPrototype> buildTypeAProductPrototypes(List<ProductRo> ros, List<Integer> stocks, List<PriceRo> priceRos, List<ProductShowStatus> statuses, ProductValidator validator) {
        if (logger.isDebugEnabled()) {
            logger.debug("buildTypeAProductPrototypes ros: {}, stocks: {}, priceRos: {}", ros, stocks, priceRos);
            logger.debug("buildTypeAProductPrototypes ros size: {}, stocks size: {}, priceRos size: {}",
                    ros == null ? 0 : ros.size(), stocks == null ? 0 : stocks.size(), priceRos == null ? 0 : priceRos.size());
        }

        List<ProductPrototype> prototypes = Lists.newArrayList();
        if (CollectionUtils.isEmpty(ros) || CollectionUtils.isEmpty(stocks)
                || CollectionUtils.isEmpty(priceRos)
                || (ros.size() != stocks.size() && ros.size() != priceRos.size())) {
            if (logger.isDebugEnabled()) {
                logger.debug("There're no products");
            }
        } else {
            int i = 0;
            while (i < ros.size()) {
                ProductRo productRo = ros.get(i);
                Integer stock = stocks.get(i);
                PriceRo priceRo = priceRos.get(i);
                ProductShowStatus status = statuses.get(i);
                //todo:如果A类商品不在销售区域内  将库存设置为0
                if (Objects.equals(status, ProductShowStatus.NOT_IN_SALE_AREA)) {
                    stock = 0;
                }
                prototypes.add(this.buildTypeAProductPrototype(productRo, stock, priceRo, validator, status));
                i++;
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("buildTypeAProductPrototypes prototypes: {}", prototypes);
        }

        return prototypes;
    }

    /**
     * 构建B类商品数据集合
     *
     * @param ros 商品Ro集合
     * @param stocks 商品库存集合
     * @param priceRos 商品价格Ro集合(门店价格)
     * @param warehousePriceRos 商品省仓门店价格Ro集合
     * @return 商品数据集合
     */
    protected List<ProductPrototype> buildTypeBProductPrototypes(String depotCode, String warehouseDepotCode, List<ProductRo> ros, List<TypeBProductStockVo> stocks, List<PriceRo> priceRos, List<PriceRo> warehousePriceRos, Integer distance, ProductValidator validator) {
        if (logger.isDebugEnabled()) {
            logger.debug("buildTypeBProductPrototype ros size: {}, stocks size: {}, warehousePriceRos: {}",
                    ros == null ? 0 : ros.size(), stocks == null ? 0 : stocks.size(), priceRos == null ? 0 : priceRos.size(), warehousePriceRos == null ? 0 : warehousePriceRos.size());
        }

        List<ProductPrototype> prototypes = Lists.newArrayList();
        if (CollectionUtils.isEmpty(ros) || CollectionUtils.isEmpty(priceRos) || CollectionUtils.isEmpty(stocks) || CollectionUtils.isEmpty(warehousePriceRos)
                || ros.size() != stocks.size() || ros.size() != priceRos.size() || ros.size() != warehousePriceRos.size()) {
            if (logger.isDebugEnabled()) {
                logger.debug("There're no products");
            }
        } else {
            List<DepotPromotionReqVo> depotPromotionReqVos = Lists.newArrayList();
            for (ProductRo ro : ros) {
                if (ro == null) {
                    depotPromotionReqVos.add(null);
                } else {
                    DepotPromotionReqVo reqVo = new DepotPromotionReqVo(ro.getProductCode(), depotCode, warehouseDepotCode);
                    depotPromotionReqVos.add(reqVo);
                }
            }
            List<DepotPromotionRespVo> depotPromotionRespVos = depotPromotionService.depotPromotions(depotPromotionReqVos);
            assert ros.size() == depotPromotionRespVos.size();
            int i = 0;
            while (i < ros.size()) {
                ProductRo productRo = ros.get(i);
                boolean isProductValid = productRo != null;
                boolean isWarehouseDepotCodeValid = StringUtils.isNotBlank(warehouseDepotCode);
                if (!isProductValid || !isWarehouseDepotCodeValid) {
                    prototypes.add(null);
                } else {
                    assert StringUtils.isNotBlank(productRo.getProductCode());
                    DepotPromotionRespVo depotPromotionRespVo = depotPromotionRespVos.get(i);
                    TypeBProductStockVo stockVo = stocks.get(i);
                    PriceRo priceRo = priceRos.get(i);
                    PriceRo warehousePriceRo = warehousePriceRos.get(i);
                    DepotPromotionVo depotPromotionVo = depotPromotionRespVo == null ? null : depotPromotionRespVo.getDepotPromotionVo();
                    DepotPromotionVo warehouseDepotPromotionVo = depotPromotionRespVo == null ? null : depotPromotionRespVo.getWarehouseDepotPromotionVo();
                    prototypes.add(new TypeBProductPrototype(depotCode, warehouseDepotCode, productRo, priceRo, warehousePriceRo, stockVo, depotPromotionVo, warehouseDepotPromotionVo, distance, validator));
                }
                i++;
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("buildTypeBProductPrototypes prototypes size: {}", prototypes.size());
        }

        return prototypes;
    }

    /**
     * 构造A类商品数据
     *
     * @param ro 商品Ro
     * @param stock 商品库存信息（A类商品）
     * @param priceRo 价格Ro
     * @return A类商品数据
     */
    protected ProductPrototype buildTypeAProductPrototype(ProductRo ro, Integer stock, PriceRo priceRo, ProductValidator validator, ProductShowStatus showStatus) {
        if (logger.isDebugEnabled()) {
            logger.debug("buildTypeAProductPrototype ro: {}, stock: {}, priceRo: {}", ro, stock, priceRo);
        }
        if (ro == null) {
            return null;
        }

        return new TypeAProductPrototype(ro, stock, priceRo, validator, showStatus);
    }

    /**
     * 构造B类商品数据
     *
     * @param ro 商品Ro
     * @param stockVo 库存Vo(包含门店库存和全省库存)
     * @param priceRo 价格Ro(门店价格Ro)
     * @param warehousePriceRo 省仓门店价格Ro
     * @return B类商品数据
     */
    protected ProductPrototype buildTypeBProductPrototype(String depotCode, String warehouseDepotCode, ProductRo ro, TypeBProductStockVo stockVo, PriceRo priceRo, PriceRo warehousePriceRo, Integer distance, ProductValidator validator) {
        if (logger.isDebugEnabled()) {
            logger.debug("buildTypeBProductPrototype ro: {}, stockVo: {}, priceRo: {}, warehousePriceRo: {}",
                    ro, stockVo, priceRo, warehousePriceRo);
        }

        boolean isProductValid = ro != null;
        boolean isDepotCodeValid = StringUtils.isNotBlank(warehouseDepotCode);

        if (!isProductValid || !isDepotCodeValid) {
            return null;
        }

        assert StringUtils.isNotBlank(ro.getProductCode());

        DepotPromotionRespVo depotPromotionRespVo = depotPromotionService.depotPromotion(new DepotPromotionReqVo(ro.getProductCode(), depotCode, warehouseDepotCode));
        DepotPromotionVo depotPromotionVo = depotPromotionRespVo == null ? null : depotPromotionRespVo.getDepotPromotionVo();
        DepotPromotionVo warehouseDepotPromotionVo = depotPromotionRespVo == null ? null : depotPromotionRespVo.getWarehouseDepotPromotionVo();

        return new TypeBProductPrototype(depotCode, warehouseDepotCode,
                ro, priceRo, warehousePriceRo, stockVo,
                depotPromotionVo, warehouseDepotPromotionVo, distance, validator);
    }

    /**
     * 获取商品价格
     *
     * @param collections 商品价格ID集合
     * @return 商品价格
     */
    protected BatchVo<PriceRo> getProductPrices(List<List<String>> collections) {
        BatchVo<PriceRo> vo = new BatchVo<>();
        List<String> priceRoIds = Lists.newArrayList();
        List<Integer> sizePieces = Lists.newArrayList();
        if (CollectionUtils.isEmpty(collections)) {
            return vo;
        }
        for (Collection<String> collection : collections) {
            if (CollectionUtils.isEmpty(collection)) {
                sizePieces.add(0);
            } else {
                sizePieces.add(collection.size());
                for (String roId : collection) {
                    priceRoIds.add(roId);
                }
            }
        }
        List<PriceRo> ros = priceRedisDao.findByIdsWithNull(priceRoIds);
        vo.setData(ros);
        vo.setPieces(sizePieces);
        return vo;
    }

    protected TypeProductsGroupVo buildTypeProductsGroup(List<String> productCodes) {
        if (logger.isDebugEnabled()) {
            logger.debug("buildProductPrototypes productCodes: {}", productCodes);
        }
        TypeProductsGroupVo vo = new TypeProductsGroupVo();
        if (CollectionUtils.isEmpty(productCodes)) {
            return vo;
        }

        List<String> allValidProductCodes = Lists.newArrayList();
        List<String> allTypeAProductCodes = productRedisDao.getTypeProductCodes(VendorTypeEnum.TYPE_A);
        List<String> allTypeBProductCodes = productRedisDao.getTypeProductCodes(VendorTypeEnum.TYPE_B);
        List<ProductTypeWithIndexVo> indexVos = Lists.newArrayList();

        int i = 0;
        for (String productCode : productCodes) {
            VendorTypeEnum vendorType = null;
            if (allTypeAProductCodes.contains(productCode)) {
                vendorType = VendorTypeEnum.TYPE_A;
            }
            if (allTypeBProductCodes.contains(productCode)) {
                vendorType = VendorTypeEnum.TYPE_B;
            }

            if (vendorType != null) {
                allValidProductCodes.add(productCode);
                indexVos.add(new ProductTypeWithIndexVo(i++, vendorType));
            }
        }

        Collection<String> typeAProductCodes = CollectionUtils.retainAll(productCodes, allTypeAProductCodes);
        Collection<String> typeBProductCodes = CollectionUtils.retainAll(productCodes, allTypeBProductCodes);
        vo.setIndexVos(indexVos);
        vo.setTypeAProductCodes(Lists.newArrayList(typeAProductCodes));
        vo.setTypeBProductCodes(Lists.newArrayList(typeBProductCodes));
        return vo;
    }

    protected List<ProductPrototype> buildProductPrototypes(BuildProductPrototypesVo vo) {
        // 通过 GeoId 取到 A 类商品的销售区域
        //        String saleAreaNo4A = saleAreaRedisDao.getTypeASaleAreaCodeByGeoId(vo.getGeoId());
        // 获取 B 类商品的销售区域, 如果没有开启快喝模式(未传入参数 depotId)取省仓门店(通过 geoId 产找对应的省仓门店)的销售区域
        // 如果开启了快喝模式(传入参数 depotId), 先取门店对应的销售区域的价格, 如果门店没货, 取门店对应的省仓门店的价格

        List<DepotResponseVo> depotResponseVos = depotService.findDepotsByDepotCodes(Lists.newArrayList(vo.getDepotCode(), vo.getWarehouseDepotCode()));
        Preconditions.checkArgument(depotResponseVos != null && depotResponseVos.size() == 2);
        DepotResponseVo depotVo = depotResponseVos.get(0);
        DepotResponseVo warehouseDepotVo = depotResponseVos.get(1);
        Preconditions.checkArgument(warehouseDepotVo != null);

        // 通过销售区域ID过滤 A 类商品
        List<Object> typeAProductGeoIdsMappings = productRedisDao.pipeGetProductSaleGeoIdsMapping(vo.getTypeAProductCodes());
        List<String> typeAProductCodes = Lists.newArrayList();
        List<ProductShowStatus> typeAProductShowStatues = Lists.newArrayList();
        for (int i = 0; i < vo.getTypeAProductCodes().size(); i++) {
            String productCode = vo.getTypeAProductCodes().get(i);
            String productGeoIdMapping = RedisUtil.byteArrayToStr((byte[]) typeAProductGeoIdsMappings.get(i));
            List<String> geoIds = StringTool.split(productGeoIdMapping, ",");
            typeAProductCodes.add(productCode);
            if (CollectionUtils.isNotEmpty(geoIds) && geoIds.contains(String.valueOf(vo.getGeoId()))) {
                typeAProductShowStatues.add(null);
            } else {
                typeAProductShowStatues.add(ProductShowStatus.NOT_IN_SALE_AREA);
            }
        }

        // 构造 A 类商品的价格 ID
        List<String> typeAPriceIds = Lists.newArrayList();
        for (String typeAProductCode : typeAProductCodes) {
            typeAPriceIds.add(String.format("%s%s", typeAProductCode, TYPE_A_PRICE_AREA_NO));
        }
        // 构造 B 类商品的价格 ID
        List<String> depotTypeBPriceIds = Lists.newArrayList();
        List<String> warehouseTypeBPriceIds = Lists.newArrayList();
        for (String typeBProductCode : vo.getTypeBProductCodes()) {
            if (depotVo == null) {
                depotTypeBPriceIds.add(null);
            } else {
                depotTypeBPriceIds.add(String.format("%s%s", typeBProductCode, depotVo.getAreaNo()));
            }
            warehouseTypeBPriceIds.add(String.format("%s%s", typeBProductCode, warehouseDepotVo.getAreaNo()));
        }

        List<List<String>> priceIds = Lists.newArrayList();
        priceIds.add(typeAPriceIds);
        priceIds.add(depotTypeBPriceIds);
        priceIds.add(warehouseTypeBPriceIds);
        BatchVo<PriceRo> prices = this.getProductPrices(priceIds);
        List<PriceRo> priceRoList = prices.getData();
        List<PriceRo> typeAPrices;
        List<PriceRo> typeBDepotPrices;
        List<PriceRo> typeBWarehousePrices;
        List<Integer> pieces = prices.getPieces();
        int startIdx = 0;
        List<IndexRangeVo> rangeVos = Lists.newArrayList();
        for (Integer piece : pieces) {
            rangeVos.add(new IndexRangeVo(startIdx, startIdx + piece));
            startIdx = startIdx + piece;
        }

        IndexRangeVo range = rangeVos.get(0);
        typeAPrices = priceRoList.subList(range.getFrom(), range.getTo());
        range = rangeVos.get(1);
        typeBDepotPrices = priceRoList.subList(range.getFrom(), range.getTo());
        range = rangeVos.get(2);
        typeBWarehousePrices = priceRoList.subList(range.getFrom(), range.getTo());
        // 获取商品(ProductRo)
        List<ProductRo> typeAProductRos = productRedisDao.findByIdsWithNull(typeAProductCodes);
        List<ProductRo> typeBProductRos = productRedisDao.findByIdsWithNull(vo.getTypeBProductCodes());

        // 获取 A类商品 B 类商品的库存(A 类商品统计全国库存; B 类商品如果门店有货, 则统计门店库存; 如果门店没货, 统计全省库存)
        List<Integer> typeAProductStocks = productStockService.getTypeAProductStock(typeAProductCodes);
        List<ProductStockReqProductVo> productStockReqProductVos = this.getProductStockReqVos(typeBProductRos);
        List<TypeBProductStockVo> typeBProductStockVos = productStockService.getTypeBProductStock(productStockReqProductVos, vo.getDepotCode(), vo.getWarehouseDepotCode());
        // 获取当前定位到就近门店的距离
        Integer distance;
        if (vo.getLatitude() == null || vo.getLongitude() == null || StringUtils.isBlank(vo.getDepotCode()) ||
                depotVo == null || depotVo.getDepotLatitude() == null || depotVo.getDepotLongitude() == null) {
            distance = null;
        } else {
            Double calcDistance = DistanceCalc.distance(vo.getLatitude().doubleValue(), vo.getLongitude().doubleValue
                    (), depotVo.getDepotLatitude().doubleValue(), depotVo.getDepotLongitude().doubleValue());
            distance = calcDistance.intValue();
        }
        List<ProductPrototype> prototypes = Lists.newArrayList();
        List<ProductPrototype> typeAProducts = this.buildTypeAProductPrototypes(typeAProductRos, typeAProductStocks, typeAPrices, typeAProductShowStatues, vo.getTypeAProductValidator());
        List<ProductPrototype> typeBProducts = this.buildTypeBProductPrototypes(vo.getDepotCode(), vo.getWarehouseDepotCode(), typeBProductRos, typeBProductStockVos, typeBDepotPrices, typeBWarehousePrices, distance, vo.getTypeBProductValidator());
        int typeAIdx = 0;
        int typeBIdx = 0;
        for (ProductTypeWithIndexVo indexVo : vo.getIndexVos()) {
            ProductPrototype productPrototype = null;
            if (indexVo.getVendorType() == VendorTypeEnum.TYPE_A) {
                productPrototype = typeAProducts.get(typeAIdx++);
            } else {
                if (CollectionUtils.isNotEmpty(typeBProducts) && typeBIdx < typeBProducts.size()) {
                    productPrototype = typeBProducts.get(typeBIdx++);
                }
            }
            if (productPrototype == null) {
                continue;
            }
            prototypes.add(productPrototype);
        }

        return prototypes;
    }

    protected List<ProductStockReqProductVo> getProductStockReqVos(List<ProductRo> productRos) {
        List<ProductStockReqProductVo> productStockReqProductVos = Lists.newArrayList();
        for (ProductRo index : productRos) {
            ProductStockReqProductVo productStockReqProductVo = new ProductStockReqProductVo();
            productStockReqProductVo.setProductCode(index.getProductCode());
            productStockReqProductVo.setRapidProductItems(index.getRapidProductInfoVo());
            productStockReqProductVo.setRapidProduct(index.getRapidProduct());
            productStockReqProductVos.add(productStockReqProductVo);
        }
        return productStockReqProductVos;
    }
}
