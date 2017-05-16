package com.biz.soa.product.service.frontend;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.vo.PageVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListItemVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppListRespVO;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.service.product.frontend.ProductService;
import com.biz.soa.product.util.PageUtil;
import com.biz.soa.product.vo.ProductPrototype;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * 商品ServiceImpl
 *
 * Created by david-liu on 2017/05/02 10:50.
 */
@Service
public class ProductServiceImpl extends AbstractProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private IProductSearchService searchService;

    // TODO 后台配置商品筛选条件
    @Override
    public ProductAppListRespVO searchProducts(ProductAppListReqVo reqVo) {
        Preconditions.checkArgument(reqVo != null && reqVo.getPriceGroupId() != null, "商品价格组ID不能为空");
        if (logger.isDebugEnabled()) {
            logger.debug("search products reqVo: {}", reqVo);
        }
        StopWatch stopWatch = new StopWatch("search products");
        stopWatch.start("search");
        ProductSearchResultVo<ProductSearchResultEntityVo> searchResult = searchService.searchProducts(reqVo);
        stopWatch.stop();
        stopWatch.start("filter and organize response");
        List<Long> searchedProductIds = Optional.ofNullable(searchResult.getItems()).orElse(Lists.newArrayList())
                .stream().map(ProductSearchResultEntityVo::getProductId).collect(Collectors.toList());
        PageVo page = PageUtil.getPage(reqVo.getPage(), searchedProductIds.size(), reqVo.getPageSize());
        List<Long> orderedProductIds = searchedProductIds.subList(page.getStartElementIndex(), page.getEndElementIndex());
        logger.info("get ordered product code list through search: {}", orderedProductIds);
        List<ProductAppListItemVo> itemVOS = this.getProductPrototype(orderedProductIds, reqVo.getPriceGroupId(), reqVo.getSellerId())
                .stream().map(ProductPrototype::toAppListItemVO).collect(Collectors.toList());
        ProductAppListRespVO respVO = new ProductAppListRespVO();
        respVO.setResult(itemVOS);
        respVO.setFilters(searchResult.getFilters());
        respVO.setBrands(searchResult.getBrands());
        stopWatch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug("result: {}", JSON.toJSONString(itemVOS));
        }
        logger.info(stopWatch.prettyPrint());
        return respVO;
    }

    @Override
    public ProductAppDetailRespVO productDetail(ProductAppDetailReqVo reqVo) {
        Preconditions.checkArgument(reqVo != null && reqVo.getPriceGroupId() != null && reqVo.getSellerId() != null);
        if (logger.isDebugEnabled()) {
            logger.debug("product detail reqVo: {}", reqVo);
        }
        return this.getProductPrototype(reqVo.getProductId(), reqVo.getPriceGroupId(), reqVo.getSellerId()).toAppDetailRespVO();
    }
}
