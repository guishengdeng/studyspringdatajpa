package com.biz.soa.product.service.frontend.bbc;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.redis.repository.product.CategoryRedisDao;
import com.biz.gbck.dao.redis.repository.product.ProductCascadeRedisDao;
import com.biz.gbck.dao.redis.repository.product.bbc.PriceRedisDao;
import com.biz.gbck.dao.redis.repository.product.bbc.ProductRedisDao;
import com.biz.gbck.dao.redis.repository.vendor.VendorRedisDao;
import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.dao.redis.ro.product.master.ProductCascadeRo;
import com.biz.gbck.dao.redis.ro.vendor.VendorRo;
import com.biz.gbck.enums.product.ProductRecommend;
import com.biz.gbck.enums.product.ProductShowStatus;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.exceptions.product.ProductFatalException;
import com.biz.gbck.exceptions.product.ProductNotFoundException;
import com.biz.gbck.exceptions.product.ProductSaleStatusException;
import com.biz.gbck.transform.product.*;
import com.biz.gbck.vo.PageVo;
import com.biz.gbck.vo.depot.DepotPromotionReqVo;
import com.biz.gbck.vo.depot.DepotPromotionRespVo;
import com.biz.gbck.vo.depot.DepotResponseVo;
import com.biz.gbck.vo.product.ProductPageResult;
import com.biz.gbck.vo.product.ProductPropertyVo;
import com.biz.gbck.vo.product.ProductTypeWithIndexVo;
import com.biz.gbck.vo.product.TypeProductsGroupVo;
import com.biz.gbck.vo.product.backend.CascadeVo;
import com.biz.gbck.vo.product.backend.ProductDetailVo;
import com.biz.gbck.vo.product.frontend.*;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.gbck.vo.search.SearchProductConditionVo;
import com.biz.gbck.vo.user.UserProductsReqVo;
import com.biz.service.depot.DepotService;
import com.biz.service.product.frontend.ProductRecommendService;
import com.biz.service.product.frontend.bbc.ProductService;
import com.biz.soa.product.service.frontend.IProductStockService;
import com.biz.soa.product.service.interfaces.bbc.ProductPriceGenerator;
import com.biz.soa.product.service.interfaces.impl.bbc.*;
import com.biz.soa.product.util.PageUtil;
import com.biz.soa.product.vo.BuildProductPrototypesVo;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.codelogger.utils.ValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.biz.gbck.common.Constant.DEFAULT_USER_LEVEL;

/**
 * 商品 Service 实现
 *
 * @author david-liu
 * @date 2017年01月12日
 * @reviewer
 */
//@Service
public class ProductServiceImpl extends AbstractProductService implements ProductService {

    private static final long serialVersionUID = -3282345687871106312L;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private IProductStockService productStockService;

    @Autowired
    private ProductRedisDao productRedisDao;

    @Autowired
    private PriceRedisDao priceRedisDao;

    @Autowired
    private DepotService depotService;

    @Autowired
    private ProductRecommendService productRecommendService;

    @Autowired
    private ProductCascadeRedisDao productCascadeRedisDao;

    @Autowired
    private CategoryRedisDao categoryRedisDao;

    @Autowired
    private VendorRedisDao vendorRedisDao;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductPageResult searchProducts(SearchProductConditionVo vo) {

        if (logger.isDebugEnabled()) {
            logger.debug("ProductServiceImpl#searchProducts vo: {}", vo);
        }

        // 搜索商品, 得到(商品 ID, 商品类型)的集合
        long t1 = System.currentTimeMillis();
        ProductSearchResultVo<ProductSearchResultEntityVo> searchResult = this.doSearch(vo);
        long t2 = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("searchProducts search cost: {}ms", t2 - t1);
        }

        if (searchResult == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("after search entityVos is null");
            }
            return new ProductPageResult(null);
        }

        List<ProductSearchResultEntityVo> entityVos = searchResult.getItems();
        if (CollectionUtils.isEmpty(entityVos)) {
            return new ProductPageResult(null);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("after search entityVos: {}, items: {}", searchResult.toString(), entityVos);
        }

        long t3 = System.currentTimeMillis();
        ProductPageResult result = this.getSearchResult(searchResult, vo);
        long t4 = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("searchProducts package product result cost: {}ms", t4 - t3);
        }

        return result;
    }

    @Override
    public List<UserProductItemVo> userProducts(UserProductsReqVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("userProducts reqVo: {}", reqVo.toString());
        }

        TypeProductsGroupVo typeProductsGroupVo = this.buildTypeProductsGroup(reqVo.getProductCodes());

        BuildProductPrototypesVo buildProductPrototypesVo = new BuildProductPrototypesVo();
        buildProductPrototypesVo.setDepotCode(reqVo.getDepotCode());
        buildProductPrototypesVo.setIndexVos(typeProductsGroupVo.getIndexVos());
        buildProductPrototypesVo.setTypeAProductCodes(typeProductsGroupVo.getTypeAProductCodes());
        buildProductPrototypesVo.setTypeBProductCodes(typeProductsGroupVo.getTypeBProductCodes());
        buildProductPrototypesVo.setWarehouseDepotCode(reqVo.getWarehouseDepotCode());
        buildProductPrototypesVo.setTypeAProductValidator(TypeAUserFootPrintProductItemValidator.getInstance());
        buildProductPrototypesVo.setTypeBProductValidator(TypeBUserFootPrintProductItemValidator.getInstance());
        buildProductPrototypesVo.setLatitude(reqVo.getLat());
        buildProductPrototypesVo.setLongitude(reqVo.getLon());
        buildProductPrototypesVo.setGeoId(reqVo.getGeoId());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(buildProductPrototypesVo);
        ProductPrototype2UserProductItemVo transformer = new ProductPrototype2UserProductItemVo(reqVo.getUserLevel());
        List<UserProductItemVo> itemVos = Lists.newArrayList();
        for (ProductPrototype productPrototype : prototypes) {
            if (productPrototype == null) {
                itemVos.add(null);
            } else {
                if (productPrototype.validate(reqVo.getUserLevel(), Boolean.FALSE)) {
                    itemVos.add(transformer.apply(productPrototype));
                }
            }
        }
        // 设置店铺名称
        setUserFootVendorNames(itemVos);
        if (logger.isDebugEnabled()) {
            logger.debug("itemVos: {}", itemVos.toString());
        }
        return itemVos;
    }

    @Override
    public List<ShopCartProductResponseVo> shoppingCartProducts(ShopCartProductRequestVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("shoppingCartProducts reqVo: {}", reqVo.toString());
        }

        TypeProductsGroupVo typeProductsGroupVo = this.buildTypeProductsGroup(reqVo.getProductCodes());

        if (logger.isDebugEnabled()) {
            logger.debug("typeProductsGroupVo: {}", typeProductsGroupVo.toString());
        }

        BuildProductPrototypesVo buildProductPrototypesVo = new BuildProductPrototypesVo();
        buildProductPrototypesVo.setTypeBProductCodes(typeProductsGroupVo.getTypeBProductCodes());
        buildProductPrototypesVo.setTypeAProductCodes(typeProductsGroupVo.getTypeAProductCodes());
        buildProductPrototypesVo.setIndexVos(typeProductsGroupVo.getIndexVos());
        buildProductPrototypesVo.setDepotCode(reqVo.getDepotCode());
        buildProductPrototypesVo.setWarehouseDepotCode(reqVo.getWarehouseDepotCode());
        buildProductPrototypesVo.setLatitude(reqVo.getLat());
        buildProductPrototypesVo.setLongitude(reqVo.getLon());
        buildProductPrototypesVo.setGeoId(reqVo.getGeoId());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(buildProductPrototypesVo);
        List<ProductPrototype> result = Lists.newArrayList();
        for (ProductPrototype productPrototype : prototypes) {
            if (productPrototype != null) {
                result.add(productPrototype);
            }
        }

        return Lists.transform(result, new ProductPrototype2ShopCartProductResponseVo(reqVo.getUserLevel() == null ? 1 : reqVo.getUserLevel()));
    }

    @Override
    public PlatformProductDetailVo findPlatformProductDetailByProductCode(String productCode) {
        ProductPrototype productPrototype = this.buildTypeAProductPrototype(productRedisDao.findOne(productCode), productStockService.getTypeAProductStock(productCode),
                priceRedisDao.getTypeAPrice(productCode), TypeAProductListValidator.getInstance(), null);
        return new ProductPrototype2PlatformProductDetailVo().apply(productPrototype);
    }

    @Override
    public List<SoaOrderProductResponseVo> orderProducts(ShopCartProductRequestVo req) {
        if (logger.isDebugEnabled()) {
            logger.debug("orderProducts reqVo: {}", req.toString());
        }

        TypeProductsGroupVo typeProductsGroupVo = this.buildTypeProductsGroup(req.getProductCodes());

        if (logger.isDebugEnabled()) {
            logger.debug("typeProductsGroupVo: {}", typeProductsGroupVo.toString());
        }

        BuildProductPrototypesVo buildProductPrototypesVo = new BuildProductPrototypesVo();
        buildProductPrototypesVo.setTypeBProductCodes(typeProductsGroupVo.getTypeBProductCodes());
        buildProductPrototypesVo.setTypeAProductCodes(typeProductsGroupVo.getTypeAProductCodes());
        buildProductPrototypesVo.setIndexVos(typeProductsGroupVo.getIndexVos());
        buildProductPrototypesVo.setDepotCode(req.getDepotCode());
        buildProductPrototypesVo.setWarehouseDepotCode(req.getWarehouseDepotCode());
        buildProductPrototypesVo.setLongitude(req.getLon());
        buildProductPrototypesVo.setLatitude(req.getLat());
        buildProductPrototypesVo.setGeoId(req.getGeoId());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(buildProductPrototypesVo);
        List<ProductPrototype> result = Lists.newArrayList();
        for (ProductPrototype productPrototype : prototypes) {
            if (productPrototype != null) {
                result.add(productPrototype);
            }
        }
        return Lists.transform(result, new ProductPrototype2SoaOrderProductResponseVo(req.getUserLevel()));
    }

    @Override
    public PageResult<ProductListItemVo> searchVendorProducts(SearchProductConditionVo vo) {

        if (logger.isDebugEnabled()) {
            logger.debug("searchVendorProducts vo: {}", vo);
        }

        // 搜索商品, 得到(商品 ID, 商品类型)的集合
        ProductSearchResultVo<ProductSearchResultEntityVo> searchResult = productSearchService.searchVendorProduct(vo);
        if (searchResult == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("after search entityVos is null");
            }
            return new PageResult<>();
        }
        List<ProductSearchResultEntityVo> entityVos = searchResult.getItems();
        if (logger.isDebugEnabled()) {
            logger.debug("after search entityVos: {}, items: {}", searchResult.toString(), entityVos);
        }

        return this.getSearchResult(searchResult, vo);
    }

    @Override
    public ProductDetailVo productDetail(ProductDetailRequestVo vo)
            throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("productDetail vo: {}", vo.toString());
        }

        if (vo == null) {
            throw new IllegalParameterException("传入参数错误");
        }

        if (StringUtils.isBlank(vo.getProductCode())) {
            throw new IllegalParameterException("商品编码不能为空");
        }

        ProductRo productRo = productRedisDao.findOne(vo.getProductCode());
        if (productRo == null) {
            throw new ProductNotFoundException(String.format("商品编码为%s的商品不存在", vo.getProductCode()));
        }


        return this.getProductDetailVo(productRo, vo);
    }

    @Override
    public List<ProductListItemVo> recommendProducts(RecommendProductReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("recommendProducts reqVo: {}", reqVo);
        }

        long t1 = System.currentTimeMillis();
        if (reqVo == null || !reqVo.isValid()) {
            String errMsg = reqVo == null ? "传入参数为空" : String.format("获取商品推荐: %s", reqVo.getInvalidMessage());
            logger.error(errMsg);
            throw new IllegalParameterException(errMsg);
        }

        ProductRecItemsVo productRec;
        if (reqVo.getType() == ProductRecommend.HOME_PAGE) {
            productRec = productRecommendService.getHomePageProductRec(reqVo.getMemberId());
        } else {
            productRec = productRecommendService.getProductDetailRec(reqVo.getMemberId(), reqVo.getProductCode());
        }

        if (productRec == null || StringUtils.isBlank(productRec.getTraceId()) || CollectionUtils.isEmpty(productRec.getProductCodes())) {
            throw new ProductNotFoundException("未从推荐引擎获取到推荐记录");
        }
        String recTraceId = productRec.getTraceId();
        TypeProductsGroupVo groupVo = this.buildTypeProductsGroup(productRec.getProductCodes());
        BuildProductPrototypesVo vo = new BuildProductPrototypesVo();
        vo.setDepotCode(reqVo.getDepotCode());
        vo.setWarehouseDepotCode(reqVo.getWarehouseDepotCode());
        vo.setTypeAProductCodes(groupVo.getTypeAProductCodes());
        vo.setTypeBProductCodes(groupVo.getTypeBProductCodes());
        vo.setIndexVos(groupVo.getIndexVos());
        vo.setGeoId(reqVo.getGeoId());
        vo.setUserLevel(reqVo.getUserLevel());
        vo.setLatitude(reqVo.getLatitude());
        vo.setLongitude(reqVo.getLongitude());
        vo.setTypeAProductValidator(TypeAProductListValidator.getInstance());
        vo.setTypeBProductValidator(TypeBProductListValidator.getInstance());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(vo);
        List<ProductListItemVo> itemVos = this.transformProductPrototype2ProductListItemVo(prototypes, reqVo.getUserLevel(), recTraceId);
        setVendorNames(itemVos);
        long t2 = System.currentTimeMillis();
        logger.info("Fetched {} items through Rec, which cost: {}ms", itemVos.size(), t2 - t1);
        return itemVos;
    }

    @Override
    public List<ProductListItemVo> relevantProducts(RelevantProductReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("relevantProducts reqVo: {}", reqVo);
        }

        long t1 = System.currentTimeMillis();
        if (reqVo == null || !reqVo.isValid()) {
            String errMsg = reqVo == null ? "关联商品请求参数为空" : reqVo.getInvalidMessage();
            logger.error(errMsg);
            throw new IllegalParameterException(errMsg);
        }

        List<String> relevantProductCodes = productRedisDao.findRelevanceList(reqVo.getProductCode());
        List<ProductListItemVo> itemVos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(relevantProductCodes)) {
            TypeProductsGroupVo groupVo = buildTypeProductsGroup(relevantProductCodes);
            BuildProductPrototypesVo vo = new BuildProductPrototypesVo();
            vo.setTypeAProductCodes(groupVo.getTypeAProductCodes());
            vo.setTypeBProductCodes(groupVo.getTypeBProductCodes());
            vo.setIndexVos(groupVo.getIndexVos());
            vo.setLatitude(reqVo.getLatitude());
            vo.setLongitude(reqVo.getLongitude());
            vo.setUserLevel(reqVo.getUserLevel());
            vo.setGeoId(reqVo.getGeoId());
            vo.setDepotCode(reqVo.getDepotCode());
            vo.setWarehouseDepotCode(reqVo.getWarehouseDepotCode());
            vo.setTypeAProductValidator(TypeAProductListValidator.getInstance());
            vo.setTypeBProductValidator(TypeBProductListValidator.getInstance());
            List<ProductPrototype> productPrototypes = this.buildProductPrototypes(vo);
            if (CollectionUtils.isNotEmpty(productPrototypes)) {
                ProductPrototype2ProductListItemVo transformer = new ProductPrototype2ProductListItemVo(reqVo.getUserLevel(), null);
                for (ProductPrototype p : productPrototypes) {
                    if (p != null && p.validate(reqVo.getUserLevel(), Boolean.TRUE)) {
                        itemVos.add(transformer.apply(p));
                    }
                }
            }
        }
        long t2 = System.currentTimeMillis();
        logger.info("Fetched {} relevantProducts, cost: {}ms", itemVos.size(), t2 - t1);
        return itemVos;
    }

    /**
     * 获取搜索结果
     *
     * @param searchResultVo 商品搜索结果Vo
     * @param vo 商品搜索条件Vo
     * @return 商品上搜索结果
     */
    private ProductPageResult getSearchResult(ProductSearchResultVo<ProductSearchResultEntityVo> searchResultVo, SearchProductConditionVo vo) {
        List<ProductSearchResultEntityVo> entityVos = searchResultVo.getItems();
        PageVo pageVo = PageUtil.getPage(vo.getPage(), entityVos.size(), vo.getPageSize());
        List<ProductSearchResultEntityVo> entityVoSubList;
        logger.debug("pageVo {}", pageVo);
        if ((pageVo.getCurrentPage() + 1) * pageVo.getPageSize() < pageVo.getTotalElementsCount()) {
            entityVoSubList = entityVos.subList((pageVo.getCurrentPage() * pageVo.getPageSize()), (pageVo.getCurrentPage() + 1) * pageVo.getPageSize());
        } else {
            entityVoSubList = entityVos.subList((pageVo.getCurrentPage() * pageVo.getPageSize()), pageVo.getTotalElementsCount());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("searchProducts search result entityVo size: {}", entityVoSubList.size());
        }

        // A, B 类商品编码 集合
        Set<String> typeAProductCodes = Sets.newLinkedHashSet();
        Set<String> typeBProductCodes = Sets.newLinkedHashSet();
        // 所有的商品编码集合(用于取出所有的商品 Ro)
        List<String> totalProductCodes = Lists.newArrayList();

        // 遍历搜索结果, 将商品编码按 A/B 类分组并且记录下各个结果在搜索结果集总的下标
        List<ProductTypeWithIndexVo> indexVos = Lists.newArrayList();
        int i = 0;
        for (ProductSearchResultEntityVo entityVo : entityVoSubList) {
            if (entityVo.getType() != null) {
                totalProductCodes.add(entityVo.getProductCode());
                if (entityVo.getType() == VendorTypeEnum.TYPE_A.getValue()) {
                    indexVos.add(new ProductTypeWithIndexVo(i++, VendorTypeEnum.TYPE_A));
                    typeAProductCodes.add(entityVo.getProductCode());
                } else {
                    indexVos.add(new ProductTypeWithIndexVo(i++, VendorTypeEnum.TYPE_B));
                    typeBProductCodes.add(entityVo.getProductCode());
                }
            }
        }
        // B类商品黑名单过滤
        List<String> typeBProductSaleBlackList = productRedisDao.getTypeBProductSaleBlackList();
        if (CollectionUtils.isNotEmpty(typeBProductSaleBlackList) && CollectionUtils.isNotEmpty(typeBProductCodes)) {
            typeBProductCodes.removeAll(typeBProductSaleBlackList);
        }

        BuildProductPrototypesVo buildProductPrototypesVo = new BuildProductPrototypesVo();
        buildProductPrototypesVo.setGeoId(vo.getGeoId());
        buildProductPrototypesVo.setDepotCode(vo.getDepotCode());
        buildProductPrototypesVo.setIndexVos(indexVos);
        buildProductPrototypesVo.setTypeAProductCodes(Lists.newArrayList(typeAProductCodes));
        buildProductPrototypesVo.setTypeBProductCodes(Lists.newArrayList(typeBProductCodes));
        buildProductPrototypesVo.setWarehouseDepotCode(vo.getWarehouseDepotCode());
        buildProductPrototypesVo.setTypeAProductValidator(TypeAProductListValidator.getInstance());
        buildProductPrototypesVo.setTypeBProductValidator(TypeBProductListValidator.getInstance());
        buildProductPrototypesVo.setLongitude(vo.getLongitude());
        buildProductPrototypesVo.setLatitude(vo.getLatitude());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(buildProductPrototypesVo);
        List<ProductPrototype> resultProductTypes = Lists.newArrayList();
        for (ProductPrototype productPrototype : prototypes) {
            if (productPrototype != null && productPrototype.validate(vo.getUserLevel(), Boolean.TRUE)) {
                resultProductTypes.add(productPrototype);
            }
        }

        List<ProductListItemVo> itemVos = Lists.transform(resultProductTypes, new ProductPrototype2ProductListItemVo(vo.getUserLevel(), null));
        List<ProductListItemVo> items = Lists.newArrayList();

        for (ProductListItemVo itemVo : itemVos) {
            if (items.size() == vo.getPageSize()) {
                break;
            }
            items.add(itemVo);
        }

        return new ProductPageResult(vo.getPage(), vo.getPageSize(), entityVos.size(), items, searchResultVo.getFilters());
    }

    @Override
    public CascadeVo cascadeProducts(ProductCascadeReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("cascadeProducts reqVo: {}", reqVo);
        }

        long t1 = System.currentTimeMillis();
        Long vendorId = null;
        String productCode = null;
        String errMsg = null;
        if (reqVo == null) {
            errMsg = "参数不能为空";
        } else {
            vendorId = reqVo.getVendorId();
            productCode = reqVo.getProductCode();
            if (vendorId == null) {
                errMsg = "商户ID不能为空";
            }
            if (StringUtils.isBlank(productCode)) {
                errMsg = "商品编码不能为空";
            }
        }

        if (StringUtils.isNotBlank(errMsg)) {
            logger.error(errMsg);
            throw new IllegalParameterException(errMsg);
        }

        ProductCascadeRo ro = productCascadeRedisDao.getCascadeByVendorIdAndProductCode(vendorId, productCode);
        CascadeVo vo = ro == null ? new CascadeVo() : ro.toCascadeVo();
        long t2 = System.currentTimeMillis();
        logger.info("cascadeProducts cost: {}ms", t2 - t1);
        return vo;
    }

    @Override
    public SeckillProductDetailRespVo seckillProductDetail(SeckillProductDetailReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("seckillProductDetail reqVo: {}", reqVo);
        }

        String errMsg = null;
        if (reqVo == null) {
            errMsg = "秒杀商品详情请求参数不能为空";
        } else {
            if (StringUtils.isBlank(reqVo.getProductCode())) {
                errMsg = "秒杀商品详情商品编码不能为空";
            }
            if (ValueUtils.getValue(reqVo.getGeoId()) <= 0) {
                errMsg = "秒杀商品详情区域ID不能为空";
            }
        }
        if (StringUtils.isNotBlank(errMsg)) {
            throw new IllegalParameterException(errMsg);
        }

        assert reqVo != null;
        ProductRo ro = productRedisDao.findOne(reqVo.getProductCode());
        assert ro != null;

        String productCode = reqVo.getProductCode();
        if (!ro.isValid()) {
            throw new ProductFatalException(String.format("商品数据错误, 请联系管理员, 出错商品编码:[%s]", productCode));
        }

        ProductPrototype p;
        if (ro.isTypeA()) {
            PriceRo priceRo = priceRedisDao.getTypeAPrice(productCode);
            List<String> canSaleGeoIds = productRedisDao.getProductSaleGeoIdMapping(productCode);
            if (!canSaleGeoIds.contains(String.valueOf(reqVo.getGeoId()))) {
                throw new ProductNotFoundException("商品在当前地区不支持销售");
            }
            p = new TypeAProductPrototype(ro, 0, priceRo, TypeASeckillProductDetailValidator.getInstance(), null);
        } else {
            DepotResponseVo warehouseDepotVo = depotService.findWarehouseDepotByCityId(reqVo.getGeoId().intValue());
            assert warehouseDepotVo != null;
            PriceRo priceRo = priceRedisDao.getTypeBPrice(productCode, warehouseDepotVo.getAreaNo());
            if (priceRo == null) {
                throw new ProductNotFoundException("商品在当前地区不支持销售");
            }
            p = new TypeBProductPrototype(null, warehouseDepotVo.getDepotCode(), ro,
                    null, priceRo, null,
                    null, null, null, TypeBSeckillProductDetailValidator.getInstance());
        }

        p.validateWithException(DEFAULT_USER_LEVEL, Boolean.FALSE);
        ProductPrototype2SeckillProductDetailRespVo transformer = new ProductPrototype2SeckillProductDetailRespVo();
        return transformer.apply(p);
    }

    @Override
    public ProductStockVo productStock(TypeBProductStockReqVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("productStock reqVo: {}", reqVo);
        }
        assert reqVo != null;
        String productCode = reqVo.getProductCode();
        assert StringUtils.isNotBlank(productCode);
        ProductRo productRo = productRedisDao.findOne(productCode);
        assert productRo != null;
        ProductStockVo stockVo = new ProductStockVo();
        stockVo.setProductCode(productCode);
        if (productRo.isTypeA()) {
            // A类商品库存
            Integer countryStock = productStockService.getTypeAProductStock(productCode);
            stockVo.setProductType(VendorTypeEnum.TYPE_A.getValue());
            stockVo.setCountryStock(countryStock);
        } else {
            stockVo.setProductType(VendorTypeEnum.TYPE_B.getValue());
            String warehouseDepotCode = reqVo.getWarehouseDepotCode();
            String depotCode = reqVo.getDepotCode();
            assert StringUtils.isNotBlank(warehouseDepotCode);
            stockVo.setWarehouseDepotCode(warehouseDepotCode);
            // B类商品库存
            TypeBProductStockVo typeBProductStockVo;
            ProductStockReqProductVo productStockReqProductVo = new ProductStockReqProductVo();
            productStockReqProductVo.setProductCode(productRo.getProductCode());
            productStockReqProductVo.setRapidProduct(productRo.getRapidProduct());
            productStockReqProductVo.setRapidProductItems(productRo.getRapidProductInfoVo());
            if (StringUtils.isNotBlank(depotCode)) {
                stockVo.setDepotCode(depotCode);
                typeBProductStockVo = productStockService.getTypeBProductStock(productStockReqProductVo, depotCode, warehouseDepotCode);
            } else {
                typeBProductStockVo = productStockService.getTypeBProductStock(productStockReqProductVo, null, warehouseDepotCode);
            }
            stockVo.setDepotStock(typeBProductStockVo == null ? 0 : typeBProductStockVo.getDepotStock());
            stockVo.setProvinceStock(typeBProductStockVo == null ? 0 : typeBProductStockVo.getProvinceStock());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("productStock stockVo: {}", stockVo);
        }

        return stockVo;
    }

    @Override
    public ProductPriceVo productPrice(ProductPriceReqVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("productPrice reqVos: {}", reqVo);
        }

        Preconditions.checkArgument(reqVo != null);
        String productCode = reqVo.getProductCode();
        Preconditions.checkArgument(StringUtils.isNotBlank(productCode));
        ProductRo ro = productRedisDao.findOne(productCode);
        Preconditions.checkArgument(ro != null);
        ProductPriceVo vo = new ProductPriceVo();
        Integer userLevel = reqVo.getUserLevel();
        vo.setProductCode(productCode);
        if (ro.isTypeA()) {
            vo.setProductType(VendorTypeEnum.TYPE_A.getValue());
            PriceRo priceRo = priceRedisDao.getTypeAPrice(productCode);
            ProductPriceGenerator productPriceGenerator = TypeAProductPriceGenerator.doClone(priceRo);
            vo.setMarketPrice(productPriceGenerator.doGetMarketPrice());
            vo.setFinalPrice(productPriceGenerator.doGetFinalPrice(userLevel));
        } else {
            String depotCode = reqVo.getDepotCode();
            String warehouseDepotCode = reqVo.getWarehouseDepotCode();
            Preconditions.checkArgument(StringUtils.isNotBlank(warehouseDepotCode));
            vo.setProductType(VendorTypeEnum.TYPE_B.getValue());
            if (StringUtils.isNotBlank(depotCode)) {
                // 已经开启快喝模式
                List<String> depotCodes = Arrays.asList(depotCode, warehouseDepotCode);
                List<DepotResponseVo> depotResponseVos = depotService.findDepotsByDepotCodes(depotCodes);
                List<String> priceIds = Arrays.asList(
                        String.format("%s%s", productCode, depotResponseVos.get(0).getAreaNo()),
                        String.format("%s%s", productCode, depotResponseVos.get(1).getAreaNo())
                );
                List<PriceRo> priceRos = priceRedisDao.findByIdsWithNull(priceIds);
                PriceRo depotPriceRo = priceRos.get(0);
                PriceRo warehouseDepotPriceRo = priceRos.get(1);
                DepotPromotionReqVo depotPromotionReqVo = new DepotPromotionReqVo(productCode, depotCode, warehouseDepotCode);
                DepotPromotionRespVo depotPromotionRespVo = depotPromotionService.depotPromotion(depotPromotionReqVo);
                ProductPriceGenerator depotPriceGenerator = TypeBProductPriceGenerator.doClone(depotPriceRo, depotPromotionRespVo.getDepotPromotionVo());
                ProductPriceGenerator warehouseDepotPriceGenerator = TypeBProductPriceGenerator.doClone(warehouseDepotPriceRo, depotPromotionRespVo.getWarehouseDepotPromotionVo());
                Integer depotMarketPrice = depotPriceGenerator.doGetMarketPrice();
                Integer depotFinalPrice = depotPriceGenerator.doGetFinalPrice(userLevel);
                Integer warehouseDepotFinalPrice = warehouseDepotPriceGenerator.doGetFinalPrice(userLevel);
                vo.setMarketPrice(depotMarketPrice);
                vo.setFinalPrice(depotFinalPrice);
                vo.setWarehouseDepotFinalPrice(depotMarketPrice);
                vo.setWarehouseDepotFinalPrice(Math.max(depotFinalPrice, warehouseDepotFinalPrice));
            } else {
                // 未开启快喝模式
                DepotResponseVo warehouseDepotRespVo = depotService.findDepotByDepotCode(warehouseDepotCode);
                PriceRo priceRo = priceRedisDao.findOne(String.format("%s%s", productCode, warehouseDepotRespVo.getAreaNo()));
                DepotPromotionRespVo depotPromotionRespVo = depotPromotionService.depotPromotion(new DepotPromotionReqVo(productCode, null, warehouseDepotCode));
                ProductPriceGenerator warehouseDepotPriceGenerator = TypeBProductPriceGenerator.doClone(priceRo, depotPromotionRespVo.getWarehouseDepotPromotionVo());
                vo.setWarehouseDepotMarketPrice(warehouseDepotPriceGenerator.doGetMarketPrice());
                vo.setWarehouseDepotFinalPrice(warehouseDepotPriceGenerator.doGetFinalPrice(userLevel));
            }
        }
        return vo;
    }

    @Override
    public List<ProductListItemVo> indexAdProducts(IndexAdProductReqVo reqVo) throws IllegalParameterException {
        if (logger.isDebugEnabled()) {
            logger.debug("indexAdProducts reqVo: {}", reqVo);
        }

        long t1 = System.currentTimeMillis();
        if (reqVo == null || !reqVo.isValid()) {
            String errMsg = reqVo == null ? "传入参数为空" : String.format("获取indexAdProducts: %s", reqVo.getInvalidMessage());
            logger.error(errMsg);
            throw new IllegalParameterException(errMsg);
        }

        TypeProductsGroupVo groupVo = this.buildTypeProductsGroup(reqVo.getProductCodes());
        BuildProductPrototypesVo vo = new BuildProductPrototypesVo();
        vo.setDepotCode(reqVo.getDepotCode());
        vo.setWarehouseDepotCode(reqVo.getWarehouseDepotCode());
        vo.setTypeAProductCodes(groupVo.getTypeAProductCodes());
        vo.setTypeBProductCodes(groupVo.getTypeBProductCodes());
        vo.setIndexVos(groupVo.getIndexVos());
        vo.setGeoId(reqVo.getGeoId());
        vo.setUserLevel(reqVo.getUserLevel());
        vo.setLatitude(reqVo.getLatitude());
        vo.setLongitude(reqVo.getLongitude());
        vo.setTypeAProductValidator(TypeAProductListValidator.getInstance());
        vo.setTypeBProductValidator(TypeBProductListValidator.getInstance());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(vo);
        List<ProductListItemVo> itemVos = this.transformProductPrototype2ProductListItemVo(prototypes, reqVo.getUserLevel(), null);
        long t2 = System.currentTimeMillis();
        logger.info("Fetched {} items through indexAdProducts, which cost: {}ms", itemVos.size(), t2 - t1);
        return itemVos;
    }

    public List<ProductListItemVo> listTopProductsBySalesVolumeForCategory(ListTopNReqVo reqVo) throws IllegalParameterException {
        Preconditions.checkArgument(reqVo != null);
        List<String> productCodeList = categoryRedisDao.getCategorySalesTopN(reqVo.getCategoryId(), reqVo.getSize());
        long t1 = System.currentTimeMillis();
        if (!reqVo.isValid()) {
            String errMsg = String.format("获取indexAdProducts: %s", reqVo.getInvalidMessage());
            logger.error(errMsg);
            throw new IllegalParameterException(errMsg);
        }
        TypeProductsGroupVo groupVo = this.buildTypeProductsGroup(productCodeList);
        BuildProductPrototypesVo vo = new BuildProductPrototypesVo();
        vo.setDepotCode(reqVo.getDepotCode());
        vo.setWarehouseDepotCode(reqVo.getWarehouseDepotCode());
        vo.setTypeAProductCodes(groupVo.getTypeAProductCodes());
        vo.setTypeBProductCodes(groupVo.getTypeBProductCodes());
        vo.setIndexVos(groupVo.getIndexVos());
        vo.setGeoId(reqVo.getGeoId());
        vo.setUserLevel(reqVo.getUserLevel());
        vo.setLatitude(reqVo.getLatitude());
        vo.setLongitude(reqVo.getLongitude());
        vo.setTypeAProductValidator(TypeAProductListValidator.getInstance());
        vo.setTypeBProductValidator(TypeBProductListValidator.getInstance());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(vo);
        List<ProductListItemVo> itemVos = this.transformProductPrototype2ProductListItemVo(prototypes, reqVo.getUserLevel(), null);
        long t2 = System.currentTimeMillis();
        if (itemVos.size() > reqVo.getSize()) {
            itemVos = itemVos.subList(0, reqVo.getSize());
        }
        // 设置店铺名称
        setVendorNames(itemVos);
        logger.info("Fetched {} items through indexAdProducts, which cost: {}ms", itemVos.size(), t2 - t1);
        return itemVos;
    }

    private void setVendorNames(List<ProductListItemVo> itemVos) {
        List<Long> vendorId = Lists.newArrayList();
        for (ProductListItemVo itemVo : itemVos) {
            vendorId.add(Long.valueOf(itemVo.getVendorId()));
        }
        List<VendorRo> vendorRos = vendorRedisDao.findVendorByIdsWithNull(vendorId);
        if (vendorRos.size() == itemVos.size()) {
            int i = 0;
            for (; i < itemVos.size(); i++) {
                itemVos.get(i).setShopName(Optional.fromNullable(vendorRos.get(i)).or(new VendorRo()).getVendorName());
            }
        }
    }

    private void setUserFootVendorNames(List<UserProductItemVo> itemVos) {
        List<Long> vendorId = Lists.newArrayList();
        for (UserProductItemVo itemVo : itemVos) {
            vendorId.add(Long.valueOf(itemVo.getVendorId()));
        }
        List<VendorRo> vendorRos = vendorRedisDao.findVendorByIdsWithNull(vendorId);
        if (vendorRos.size() == itemVos.size()) {
            for (int i = 0; i < itemVos.size(); i++) {
                itemVos.get(i).setVendorName(Optional.fromNullable(vendorRos.get(i)).or(new VendorRo()).getVendorName());
            }
        }
    }


    @Override
    public ProductPropertyVo productProperties(ProductPropertyReqVo reqVo) throws DepotNextDoorException {
        Preconditions.checkArgument(reqVo != null);
        String productCode = reqVo.getProductCode();
        if (StringUtils.isBlank(productCode)) {
            throw new IllegalParameterException("商品编码不能为空");
        }
        ProductRo ro = productRedisDao.findOne(productCode);
        if (ro == null) {
            throw new ProductNotFoundException("商品不存在");
        }
        return ro.getProductProperty();
    }

    @Override
    public List<ProductListItemVo> indexProducts(IndexProductReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("indexProducts reqVo: {}", reqVo);
        }

        long t1 = System.currentTimeMillis();
        if (reqVo == null || !reqVo.isValid()) {
            String errMsg = reqVo == null ? "传入参数为空" : String.format("indexProducts: %s", reqVo.getInvalidMessage());
            logger.error(errMsg);
            throw new IllegalParameterException(errMsg);
        }

        TypeProductsGroupVo groupVo = this.buildTypeProductsGroup(reqVo.getProductCodes());
        BuildProductPrototypesVo vo = new BuildProductPrototypesVo();
        vo.setDepotCode(reqVo.getDepotCode());
        vo.setWarehouseDepotCode(reqVo.getWarehouseDepotCode());
        vo.setTypeAProductCodes(groupVo.getTypeAProductCodes());
        vo.setTypeBProductCodes(groupVo.getTypeBProductCodes());
        vo.setIndexVos(groupVo.getIndexVos());
        vo.setGeoId(reqVo.getGeoId());
        vo.setUserLevel(reqVo.getUserLevel());
        vo.setLatitude(reqVo.getLatitude());
        vo.setLongitude(reqVo.getLongitude());
        vo.setTypeAProductValidator(TypeAProductListValidator.getInstance());
        vo.setTypeBProductValidator(TypeBProductListValidator.getInstance());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(vo);
        List<ProductListItemVo> itemVos = this.transformProductPrototype2ProductListItemVo(prototypes, reqVo.getUserLevel(), null);
        this.setVendorNames(itemVos);
        long t2 = System.currentTimeMillis();
        logger.info("Fetched {} items through indexAdProducts, which cost: {}ms", itemVos.size(), t2 - t1);
        return itemVos;
    }

    @Override
    public List<ProductListItemVo> findAllProducts() {
        List<Product> products = productRepository.findAll();
        logger.debug("所有的商品数量为：{}", products.size());
        List<ProductListItemVo> productListItemVos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(products)) {
            for (Product product : products) {
                ProductListItemVo productListItemVo = new ProductListItemVo();
                productListItemVo.setProductId(product.getId());
                productListItemVo.setProductName(product.getName());
                productListItemVo.setProductCode(product.getProductCode());
                if (StringUtils.isNotBlank(product.getLogo())) {
                    productListItemVo.setLogo(product.getLogo());
                }
                productListItemVos.add(productListItemVo);
            }
            return productListItemVos;
        }
        return productListItemVos;
    }

    @Override
    public List<ProductListItemVo> singlePageActivity(SinglePageActivityReqVo vo) {
        // A, B 类商品编码 集合
        Set<String> typeAProductCodes = Sets.newLinkedHashSet();
        Set<String> typeBProductCodes = Sets.newLinkedHashSet();
        // 所有的商品编码集合(用于取出所有的商品 Ro)
        List<String> totalProductCodes = Lists.newArrayList();

        // 遍历搜索结果, 将商品编码按 A/B 类分组并且记录下各个结果在搜索结果集总的下标
        List<ProductTypeWithIndexVo> indexVos = Lists.newArrayList();
        int i = 0;
        for (ProductSearchResultEntityVo entityVo : vo.getReqVos()) {
            if (entityVo.getType() != null) {
                totalProductCodes.add(entityVo.getProductCode());
                if (entityVo.getType() == VendorTypeEnum.TYPE_A.getValue()) {
                    indexVos.add(new ProductTypeWithIndexVo(i++, VendorTypeEnum.TYPE_A));
                    typeAProductCodes.add(entityVo.getProductCode());
                } else {
                    indexVos.add(new ProductTypeWithIndexVo(i++, VendorTypeEnum.TYPE_B));
                    typeBProductCodes.add(entityVo.getProductCode());
                }
            }
        }
        // B类商品黑名单过滤
        List<String> typeBProductSaleBlackList = productRedisDao.getTypeBProductSaleBlackList();
        if (CollectionUtils.isNotEmpty(typeBProductSaleBlackList) && CollectionUtils.isNotEmpty(typeBProductCodes)) {
            typeBProductCodes.removeAll(typeBProductSaleBlackList);
        }

        BuildProductPrototypesVo buildProductPrototypesVo = new BuildProductPrototypesVo();
        buildProductPrototypesVo.setGeoId(vo.getGeoId());
        buildProductPrototypesVo.setDepotCode(vo.getDepotCode());
        buildProductPrototypesVo.setIndexVos(indexVos);
        buildProductPrototypesVo.setTypeAProductCodes(Lists.newArrayList(typeAProductCodes));
        buildProductPrototypesVo.setTypeBProductCodes(Lists.newArrayList(typeBProductCodes));
        buildProductPrototypesVo.setWarehouseDepotCode(vo.getWarehouseDepotCode());
        buildProductPrototypesVo.setTypeAProductValidator(TypeAProductListValidator.getInstance());
        buildProductPrototypesVo.setTypeBProductValidator(TypeBProductListValidator.getInstance());
        buildProductPrototypesVo.setLongitude(vo.getLon());
        buildProductPrototypesVo.setLatitude(vo.getLat());
        List<ProductPrototype> prototypes = this.buildProductPrototypes(buildProductPrototypesVo);
        List<ProductPrototype> resultProductTypes = Lists.newArrayList();
        for (ProductPrototype productPrototype : prototypes) {
            if (productPrototype != null && productPrototype.validate(vo.getUserLevel(), Boolean.TRUE)) {
                resultProductTypes.add(productPrototype);
            }
        }

        List<ProductListItemVo> itemVos = Lists.transform(resultProductTypes, new ProductPrototype2ProductListItemVo(vo.getUserLevel(), null));
        return itemVos;
    }

    /**
     * 获取商品详情Vo
     *
     * @param productRo 商品Ro
     * @param reqVo 商品详情请求Vo
     * @return 商品详情Vo
     */
    private ProductDetailVo getProductDetailVo(ProductRo productRo, ProductDetailRequestVo reqVo)
            throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("getProductDetailVo productRo: {}", productRo.toString());
        }

        if (!productRo.isValid()) {
            throw new ProductFatalException(String.format("商品数据错误, 请联系管理员, 出错商品编码:[%s]", productRo.getProductCode()));
        }

        if (!productRo.isOnSale()) {
            throw new ProductSaleStatusException(String.format("商品[%s]已经下架", productRo.getProductCode()));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("product[%s] type: {}", productRo.getProductType());
        }
        ProductPrototype productPrototype;
        if (productRo.isTypeA()) {
            PriceRo priceRo = priceRedisDao.getTypeAPrice(productRo.getProductCode());
            if (priceRo == null) {
                throw new ProductFatalException(String.format("商品[%s]价格信息不存在", productRo.getProductCode()));
            }
            Integer stock = productStockService.getTypeAProductStock(productRo.getProductCode());
            List<String> geoIds = productRedisDao.getProductSaleGeoIdMapping(reqVo.getProductCode());
            ProductShowStatus showStatus;
            if (CollectionUtils.isNotEmpty(geoIds) && geoIds.contains(String.valueOf(reqVo.getGeoId()))) {
                showStatus = null;
            } else {
                showStatus = ProductShowStatus.NOT_IN_SALE_AREA;
                stock = 0;
            }
            productPrototype = this.buildTypeAProductPrototype(productRo, stock, priceRo, TypeAProductDetailValidator.getInstance(), showStatus);
        } else {

            if (StringUtils.isBlank(reqVo.getWarehouseDepotCode())) {
                throw new IllegalParameterException("省仓门店编码不能为空");
            }
            ProductStockReqProductVo productStockReqProductVo = new ProductStockReqProductVo();
            productStockReqProductVo.setProductCode(productRo.getProductCode());
            productStockReqProductVo.setRapidProduct(productRo.getRapidProduct());
            productStockReqProductVo.setRapidProductItems(productRo.getRapidProductInfoVo());
            TypeBProductStockVo stockVo = productStockService.getTypeBProductStock(productStockReqProductVo, reqVo.getDepotCode(), reqVo.getWarehouseDepotCode());
            List<DepotResponseVo> depotResponseVos = depotService.findDepotsByDepotCodes(Lists.newArrayList(reqVo.getDepotCode(), reqVo.getWarehouseDepotCode()));
            if (depotResponseVos == null || depotResponseVos.size() != 2) {
                throw new ProductFatalException("查找门店和省仓门店出错");
            }

            List<String> priceRoIds = Lists.newArrayList();
            for (DepotResponseVo vo : depotResponseVos) {
                if (vo == null) {
                    priceRoIds.add(null);
                } else {
                    priceRoIds.add(String.format("%s%s", productRo.getProductCode(), vo.getAreaNo()));
                }
            }

            List<PriceRo> priceRos = priceRedisDao.findByIds(priceRoIds);
            Integer distance;
            if (StringUtils.isBlank(reqVo.getDepotCode())) {
                distance = null;
            } else {
                if (reqVo.getLatitude() != null | reqVo.getLongitude() != null |
                        depotResponseVos.get(0).getDepotLatitude() != null |
                        depotResponseVos.get(0).getDepotLongitude() != null) {
                    logger.debug("distance calculate start depotCode= {} ,Lon = {},Lat = {}", reqVo.getDepotCode(), reqVo.getLongitude().doubleValue(), reqVo.getLatitude().doubleValue());
                    Double calcDistance = DistanceCalc.distance(reqVo.getLatitude().doubleValue(), reqVo.getLongitude().doubleValue(), depotResponseVos.get(0).getDepotLatitude().doubleValue(), depotResponseVos.get(0).getDepotLongitude().doubleValue());
                    distance = calcDistance.intValue();
                } else {
                    distance = null;
                }

            }
            productPrototype = this.buildTypeBProductPrototype(reqVo.getDepotCode(), reqVo.getWarehouseDepotCode(), productRo, stockVo, priceRos.get(0), priceRos.get(1), distance, TypeBProductDetailValidator.getInstance());
        }

        productPrototype.validateWithException(reqVo.getUserLevel(), reqVo.getValidateStock());

        ProductDetailVo productDetailVo = new ProductPrototype2ProductDetailVo(reqVo.getUserLevel()).apply(productPrototype);
        Preconditions.checkArgument(productDetailVo != null);
        if (NumberUtils.isNumber(productDetailVo.getVendorId())) {
            VendorRo vendorRo = vendorRedisDao.findById(Long.valueOf(productDetailVo.getVendorId()));
            productDetailVo.setVendorName(java.util.Optional.ofNullable(vendorRo).orElse(new VendorRo()).getVendorName());
        }

        return productDetailVo;
    }

    private List<ProductListItemVo> transformProductPrototype2ProductListItemVo(List<ProductPrototype> prototypes, Integer userLevel, String traceId) {
        List<ProductListItemVo> itemVos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(prototypes)) {
            ProductPrototype2ProductListItemVo transformer = new ProductPrototype2ProductListItemVo(userLevel, traceId);
            for (ProductPrototype p : prototypes) {
                if (p != null && p.validate(userLevel, Boolean.TRUE)) {
                    itemVos.add(transformer.apply(p));
                }
            }
        }
        return itemVos;
    }

}
