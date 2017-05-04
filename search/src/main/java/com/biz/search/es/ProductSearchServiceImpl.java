package com.biz.search.es;

import com.biz.core.util.StringUtil;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.vo.product.frontend.UpdateProductIncrIndicesReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.ProductSearchFieldVo;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.search.es.entity.ProductEsEntity;
import com.biz.search.es.repository.ProductEsRepository;
import com.biz.search.es.service.interfaces.ProductSearchService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.CollectionUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * 商品搜索ServiceImpl
 *
 * Created by david-liu on 2017/05/02 23:12.
 */
public final class ProductSearchServiceImpl implements ProductSearchService {

    private static final Logger logger = LoggerFactory.getLogger(ProductSearchServiceImpl.class);

    private ProductEsRepository productEsRepository;

    @Override
    public ProductSearchResultVo<ProductSearchResultEntityVo> searchProducts(ProductAppListReqVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("search products reqVo: {}", reqVo);
        }

        BoolQueryBuilder rootQueryBuilder = QueryBuilders.boolQuery();
        // 过滤上架状态(仅显示已上架商品)
        rootQueryBuilder.must(QueryBuilders.termQuery("saleStatus", SaleStatusEnum.ON_SALE.getValue()));

        // 过滤价格组ID
        rootQueryBuilder.must(QueryBuilders.termQuery("priceGroup", reqVo.getPriceGroupId()));

        // 过滤库存
        rootQueryBuilder.must(QueryBuilders.rangeQuery("stock").gt(0));

        if (reqVo.getCategoryId() != null) {
            // 过滤分类
            rootQueryBuilder.must(QueryBuilders.termQuery("category", reqVo.getCategoryId()));
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
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                searchField.getValues().forEach(val -> {
                    if (StringUtils.equals(searchField.getField(), "properties")) {
                        boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("properties", val));
                    } else {
                        boolQueryBuilder.should(QueryBuilders.termQuery(searchField.getField(), val));
                    }
                });
                rootQueryBuilder.must(boolQueryBuilder);
            });
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(rootQueryBuilder).withSort(this.getSort(reqVo.getSort())).withPageable(new PageRequest(reqVo.getPage(), reqVo.getPageSize()));
        Page<ProductEsEntity> esEntityPage = productEsRepository.search(nativeSearchQueryBuilder.build());
        ProductSearchResultVo<ProductSearchResultEntityVo> searchResultVo = new ProductSearchResultVo<>();
        if (esEntityPage != null) {
            searchResultVo.setTotalCount((int) esEntityPage.getTotalElements());
            List<ProductEsEntity> esEntities = Optional.ofNullable(esEntityPage.getContent()).orElse(Lists.newArrayList());
            searchResultVo.setCount(esEntities.size());
            searchResultVo.setItems(esEntities.stream().map(esEntity -> new ProductSearchResultEntityVo(esEntity.getProductId(), esEntity.getProductCode())).collect(Collectors.toList()));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("search result: {}", searchResultVo);
        }

        return searchResultVo;
    }

    @Override
    public void updateTotalIndices() {

    }

    @Override
    public void updateIncrIndices(UpdateProductIncrIndicesReqVo reqVo) {

    }

    private SortBuilder getSort(String sort) {
        SortBuilder sortBuilder;
        if (StringUtils.equalsIgnoreCase("saleVolume", sort)) {
            sortBuilder = SortBuilders.fieldSort("salesVolume").order(SortOrder.DESC);
        } else if (StringUtils.equalsIgnoreCase("salePriceAsc", sort)) {
            sortBuilder = SortBuilders.fieldSort("salePrice").order(SortOrder.ASC);
        } else if (StringUtils.equalsIgnoreCase("salePriceDesc", sort)) {
            sortBuilder = SortBuilders.fieldSort("salePrice").order(SortOrder.DESC);
        } else {
            sortBuilder = SortBuilders.fieldSort("salesVolume").order(SortOrder.DESC);
        }
        return sortBuilder;
    }
}
