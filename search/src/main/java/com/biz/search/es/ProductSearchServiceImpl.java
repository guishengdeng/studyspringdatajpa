package com.biz.search.es;

import com.biz.core.algorithm.kmeans.KMeans;
import com.biz.core.algorithm.kmeans.KMeansRangeVo;
import com.biz.core.util.StringTool;
import com.biz.core.util.StringUtil;
import com.biz.gbck.dao.redis.repository.product.PriceGroupRedisDao;
import com.biz.gbck.dao.redis.repository.product.ProductRedisDao;
import com.biz.gbck.dao.redis.ro.product.price.PriceGroupRO;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.vo.product.UpdateProductIdxVO;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.*;
import com.biz.gbck.vo.search.*;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.search.es.entity.ProductEsEntity;
import com.biz.search.es.repository.ProductEsRepository;
import com.biz.search.es.service.interfaces.ProductSearchService;
import com.biz.soa.feign.client.product.ProductFeignClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.CollectionUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * 商品搜索ServiceImpl
 *
 * Created by david-liu on 2017/05/02 23:12.
 */
@Service
public final class ProductSearchServiceImpl implements ProductSearchService {

    private static final Logger logger = LoggerFactory.getLogger(ProductSearchServiceImpl.class);

    @Autowired
    private ProductEsRepository productEsRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private PriceGroupRedisDao priceGroupRedisDao;

    @Autowired
    private ProductRedisDao productRedisDao;

    @Override
    public ProductSearchResultVo<ProductSearchResultEntityVo> searchProducts(ProductAppListReqVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("search products reqVo: {}", reqVo);
        }
        // 调用私有方法获取QueryBuilder
        BoolQueryBuilder rootQueryBuilder = this.getQueryBuilder(reqVo);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 获取10000条记录, Spring Data ES最大支持一页10000条数据, 取出所有记录, 方便做搜索结果属性聚类
        // 如果商品数量的超过了10000, 可以采取分页取多次对结果进行合并再做聚类
        nativeSearchQueryBuilder.withQuery(rootQueryBuilder).withSort(this.getSort(reqVo.getSort())).withPageable(new PageRequest(0, 10000));
        Iterable<ProductEsEntity> esEntityPage = productEsRepository.search(nativeSearchQueryBuilder.build());
        ProductSearchResultVo<ProductSearchResultEntityVo> searchResultVo = new ProductSearchResultVo<>();
        List<ProductEsEntity> resultEntities = Lists.newArrayList();
        if (esEntityPage != null) {
            Iterator<ProductEsEntity> iterator = esEntityPage.iterator();
            iterator.forEachRemaining(productEsEntity -> resultEntities.add(iterator.next()));
        }

        return this.getSearchResult(resultEntities, reqVo.getFilterMap());
    }

    @Override
    public void updateTotalIndices(UpdateProductIdxVO updateProductIdxVO) {
        logger.info("start update product idx vo");
        if (updateProductIdxVO == null) {
            logger.warn("updateProductIdxVO is null, return direct, it's a invalid request on updateTotalIndices");
            return;
        }
        StopWatch stopWatch = new StopWatch("updateTotalIndices");
        Long lastUpdateTimestamp = System.currentTimeMillis();
        if (Objects.isNull(updateProductIdxVO.getProductId())) {
            if (Objects.nonNull(updateProductIdxVO.getPriceGroupId())) {
                logger.warn("product id is null but price group id is not null, use increment indices method instead");
            } else {
                logger.info("update all price group product indices");
                stopWatch.start("fetch all priceGroupRO");
                List<PriceGroupRO> priceGroupROS = priceGroupRedisDao.findAll();
                stopWatch.stop();
                List<Long> priceGroupIds = Lists.newArrayList();
                List<Long> sellerIds = Lists.newArrayList();
                Optional.ofNullable(priceGroupROS).orElse(priceGroupROS).stream().filter(priceGroupRO ->
                        Objects.nonNull(priceGroupRO)
                                && Objects.nonNull(priceGroupRO.getPriceGroupId())
                                && Objects.nonNull(priceGroupRO.getSellerId())).forEach(priceGroupRO -> {
                    priceGroupIds.add(priceGroupRO.getPriceGroupId());
                    sellerIds.add(priceGroupRO.getSellerId());
                });
                stopWatch.start("fetch on sale product ids");
                List<Long> onSaleProductIds = productRedisDao.getAllOnSaleProductIds();
                stopWatch.stop();
                logger.info("there are {} on sale product ids", onSaleProductIds.size());
                stopWatch.start("build product indices");
                IntStream.range(0, priceGroupIds.size()).forEach(index -> {
                    Long priceGroupId = priceGroupIds.get(index), sellerId = sellerIds.get(index);
                    logger.info("build No[{}] priceGroup product documents, priceGroupId=[{}]", index + 1, priceGroupId);
                    TotalProductIdxReqVo reqVo = new TotalProductIdxReqVo(onSaleProductIds, priceGroupId, sellerId);
                    MicroServiceResult<List<ProductIdxVO>> searchTotalIndices = productFeignClient.getSearchTotalIndices(reqVo);
                    if (searchTotalIndices.getStatus() == MicroServiceResult.SUCCESS_STATUS) {
                        this.saveEsEntitiesFromIdxVO(searchTotalIndices.getData(), lastUpdateTimestamp);
                    } else {
                        logger.warn("get search indices fault, error msg: {}", searchTotalIndices.getMsg());
                    }
                });
                stopWatch.stop();
            }
        } else {
            logger.warn("product id in request param must be null");
        }
        stopWatch.start("delete old version product documents");
        productEsRepository.deleteByLastUpdateTimestampLessThan(lastUpdateTimestamp);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    @Override
    public void updateIncrIndices(UpdateProductIdxVO updateProductIdxVO) {
        if (logger.isDebugEnabled()) {
            logger.debug("update product incr indices, param: {}", updateProductIdxVO);
        }
        StopWatch stopWatch = new StopWatch("updateIncrIndices");
        Long productId = updateProductIdxVO.getProductId(), priceGroupId = updateProductIdxVO.getPriceGroupId();
        IncrProductIdxReqVo reqVo;
        if (Objects.nonNull(priceGroupId)) {
            PriceGroupRO priceGroupRO = priceGroupRedisDao.findOne(priceGroupId);
            reqVo = new IncrProductIdxReqVo(productId, priceGroupRO.getId(), priceGroupRO.getSellerId());
        } else {
            reqVo = new IncrProductIdxReqVo(productId, null, null);
        }
        MicroServiceResult<List<ProductIdxVO>> searchIncrIndices = productFeignClient.getSearchIncrIndices(reqVo);
        stopWatch.start("fetch product indices");
        if (searchIncrIndices.getStatus() == MicroServiceResult.SUCCESS_STATUS) {
            List<ProductIdxVO> idxVOS = searchIncrIndices.getData();
            List<ProductEsEntity> esEntities = Optional.ofNullable(idxVOS).orElse(idxVOS).stream().filter(Objects::nonNull).map(ProductEsEntity::new).collect(Collectors.toList());
            productEsRepository.save(esEntities);
        } else {
            logger.warn("fetch product documents fault");
        }
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    private SortBuilder getSort(String sort) {
        SortBuilder sortBuilder;
        if (StringUtils.equalsIgnoreCase("saleVolumeAsc", sort)) {
            sortBuilder = SortBuilders.fieldSort("salesVolume").order(SortOrder.ASC);
        } else if (StringUtils.equalsIgnoreCase("salePrice", sort)) {
            sortBuilder = SortBuilders.fieldSort("salePrice").order(SortOrder.ASC);
        } else if (StringUtils.equalsIgnoreCase("salePrice", sort)) {
            sortBuilder = SortBuilders.fieldSort("salePrice").order(SortOrder.DESC);
        } else {
            sortBuilder = SortBuilders.fieldSort("salesVolume").order(SortOrder.DESC);
        }
        return sortBuilder;
    }

    private BoolQueryBuilder getQueryBuilder(ProductAppListReqVo reqVo) {
        BoolQueryBuilder rootQueryBuilder = QueryBuilders.boolQuery();
        // 过滤上架状态(仅显示已上架商品)
        rootQueryBuilder.must(QueryBuilders.termQuery("saleStatus", SaleStatusEnum.ON_SALE.getValue()));

        // 过滤价格组ID
        rootQueryBuilder.must(QueryBuilders.termQuery("priceGroup", reqVo.getPriceGroupId()));

        // 过滤库存
        rootQueryBuilder.must(QueryBuilders.rangeQuery("stock").gt(0));

        if (reqVo.getCategoryId() != null) {
            // 过滤分类
            rootQueryBuilder.must(QueryBuilders.termQuery("categoryId", reqVo.getCategoryId()));
        }

        // 按照关键字模糊匹配
        String keyword = reqVo.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            rootQueryBuilder.must(QueryBuilders.queryStringQuery(StringUtil.patternMatcherStr(keyword)).field("name").analyzer("ik_smart"));
        }

        // 前端传入搜索字段过滤
        List<ProductSearchFieldVo> productSearchFields = reqVo.getProductSearchFields();
        if (CollectionUtils.isNotEmpty(productSearchFields)) {
            productSearchFields.forEach(searchField -> {
                String fieldName = searchField.getField();
                List<String> searchValues = searchField.getValues();
                if (CollectionUtils.isNotEmpty(searchValues)) {
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                    if (StringUtils.equalsIgnoreCase(fieldName, "brandId")) {
                        searchValues.forEach(val -> boolQueryBuilder.should(QueryBuilders.termQuery(fieldName, val)));
                    } else if (StringUtils.equalsIgnoreCase(fieldName, "properties")) {
                        searchValues.forEach(val -> boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("properties", val)));
                    } else if (StringUtils.equalsIgnoreCase(fieldName, "price")) {
                        searchValues.forEach(val -> {
                            if (StringUtils.contains(val, "_") && StringUtils.isNotBlank(val)) {
                                String[] prices = val.split("_");
                                RangeQueryBuilder priceRange = new RangeQueryBuilder("salePrice");
                                priceRange.gte(Integer.parseInt(prices[0]));
                                if (prices.length == 2 && StringUtils.isNotBlank(prices[1])) {
                                    priceRange.lte(Integer.parseInt(prices[1]));
                                }
                                boolQueryBuilder.should(priceRange);
                            }
                        });
                    } else {
                        searchValues.forEach(val -> boolQueryBuilder.should(QueryBuilders.termQuery(fieldName, val)));
                    }
                    rootQueryBuilder.must(boolQueryBuilder);
                }
            });
        }

        return rootQueryBuilder;
    }

    private ProductSearchResultVo<ProductSearchResultEntityVo> getSearchResult(List<ProductEsEntity> esEntities, Map<String, ProductFilterVO> filterMap) {
        if (CollectionUtils.isEmpty(esEntities)) {
            return new ProductSearchResultVo<>();
        }
        logger.info("Fetched {} entities from elasticsearch", esEntities.size());
        ProductSearchResultVo<ProductSearchResultEntityVo> productSearchResultVo = new ProductSearchResultVo<>();
        List<ProductSearchResultEntityVo> resultEntityVos = Lists.newArrayList();
        List<Double> prices = Lists.newArrayList();
        Multiset<String> brandSet = TreeMultiset.create();
        Map<String, Multiset<String>> propertyMap = Maps.newHashMap();
        StopWatch stopWatch = new StopWatch("build search result");
        stopWatch.start("build product search result and prepare indeed resources");
        esEntities.forEach(entity -> {
            ProductSearchResultEntityVo resultEntityVo = new ProductSearchResultEntityVo();
            resultEntityVo.setProductCode(entity.getProductCode());
            resultEntityVo.setProductId(Long.valueOf(entity.getProductId()));
            resultEntityVos.add(resultEntityVo);
            if (entity.getSalePrice() != null) {
                prices.add(Double.valueOf(entity.getSalePrice()));
            }
            Long brandId = entity.getBrandId();
            String brandName = entity.getBrand();
            if (brandId != null && StringUtils.isNotBlank(brandName)) {
                brandSet.add(String.format("%s:%s", brandName, brandId));
            }

            String propertyTexts = entity.getPropertyTexts();
            if (StringUtils.isNotBlank(propertyTexts)) {
                List<String> propertyTextsList = StringTool.split(propertyTexts, ",");
                propertyTextsList.forEach(propertyText -> {
                    String[] properties = propertyText.split("_");
                    Multiset<String> propertySet = propertyMap.get(properties[1]);
                    if (CollectionUtils.isEmpty(propertySet)) {
                        propertySet = TreeMultiset.create();
                    }
                    propertySet.add(String.format("%s_%s", properties[0], properties[2]));
                    propertyMap.put(properties[1], propertySet);
                });
            }
        });
        stopWatch.stop();
        stopWatch.start("indeed brand items");
        // 获取所有品牌的过滤结果
        if (CollectionUtils.isNotEmpty(brandSet)) {
            List<ProductBrandFilterItemVO> brandFilterItems = brandSet.elementSet().stream().map(brand -> {
                String[] brandTexts = brand.split(":");
                ProductBrandFilterItemVO brandFilterItemVO = new ProductBrandFilterItemVO();
                brandFilterItemVO.setLabel(brandTexts[0]);
                brandFilterItemVO.setValue(brandTexts[1]);
                return brandFilterItemVO;
            }).collect(Collectors.toList());
            ProductBrandFilterVO brandFilterVO = new ProductBrandFilterVO();
            brandFilterVO.setItems(brandFilterItems);
            productSearchResultVo.setBrands(brandFilterVO);
        }
        stopWatch.stop();
        stopWatch.start("indeed price items");
        // 处理价格聚类结果
        List<KMeansRangeVo> priceRanges = new KMeans(prices).getRanges("%d-%d元");
        List<ProductFilterItemVO> priceFields = Lists.newArrayList();
        priceRanges.forEach(priceRange -> {
            ProductFilterItemVO fieldItemVo = new ProductFilterItemVO();
            fieldItemVo.setLabel(priceRange.getTitle());
            fieldItemVo.setValue(priceRange.getField());
            priceFields.add(fieldItemVo);
        });
        stopWatch.stop();
        ProductFilterVO priceFieldVO = new ProductFilterVO();
        priceFieldVO.setFilterItems(priceFields);
        List<ProductFilterVO> filters = Lists.newArrayList();
        stopWatch.start("indeed property items");
        if (MapUtils.isNotEmpty(filterMap)) {
            filterMap.forEach((filterName, filterVO) -> {
                Multiset<String> propertySet = propertyMap.get(filterName);
                if (CollectionUtils.isNotEmpty(propertySet)) {
                    List<ProductFilterItemVO> filterItemVOS = propertySet.elementSet().stream().map(property -> {
                        ProductFilterItemVO itemVO = new ProductFilterItemVO();
                        itemVO.setLabel(property);
                        itemVO.setValue(property);
                        return itemVO;
                    }).collect(Collectors.toList());
                    filterVO.setField("property");
                    filterVO.setFilterItems(filterItemVOS);
                    filters.add(filterVO);
                }
            });
        } else {
            propertyMap.forEach((propertyName, propertySet) -> {
                if (CollectionUtils.isNotEmpty(propertySet)) {
                    ProductFilterVO filterVO = new ProductFilterVO();
                    filterVO.setField("property");
                    filterVO.setLabel(propertyName);
                    List<ProductFilterItemVO> itemVOS = Lists.newArrayList();
                    propertySet.elementSet().forEach(property -> {
                        ProductFilterItemVO itemVO = new ProductFilterItemVO();
                        itemVO.setLabel(property.split("_")[1]);
                        itemVO.setValue(property);
                        itemVOS.add(itemVO);
                    });
                    filterVO.setFilterItems(itemVOS);
                    filters.add(filterVO);
                }
            });
        }
        stopWatch.stop();
        filters.add(priceFieldVO);
        productSearchResultVo.setFilters(filters);
        productSearchResultVo.setItems(resultEntityVos);
        logger.info(stopWatch.prettyPrint());
        return productSearchResultVo;
    }

    private void saveEsEntitiesFromIdxVO(List<ProductIdxVO> idxVOS, Long lastUpdateTimestamp) {
        List<ProductEsEntity> esEntities = Optional.ofNullable(idxVOS).orElse(Lists.newArrayList())
                .stream().filter(Objects::nonNull).map(idxVO -> new ProductEsEntity(idxVO, lastUpdateTimestamp)).collect(Collectors.toList());
        productEsRepository.save(esEntities);
    }
}
