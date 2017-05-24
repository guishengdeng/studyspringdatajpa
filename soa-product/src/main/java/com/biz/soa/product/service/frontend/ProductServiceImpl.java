package com.biz.soa.product.service.frontend;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.request.PurchaseProductReqVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListItemVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppListRespVO;
import com.biz.gbck.vo.product.gbck.response.PurchaseProductItemVO;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.service.product.frontend.ProductService;
import com.biz.soa.feign.client.product.ProductSearchFeignClient;
import com.biz.soa.product.vo.ProductPrototype;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.codelogger.utils.CollectionUtils;
import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductSearchFeignClient productSearchFeignClient;

    // TODO 后台配置商品筛选条件
    @Override
    public ProductAppListRespVO searchProducts(ProductAppListReqVo reqVo) {
        Preconditions.checkArgument(reqVo != null && reqVo.getPriceGroupId() != null, "商品价格组ID不能为空");
        if (logger.isDebugEnabled()) {
            logger.debug("search products reqVo: {}", reqVo);
        }
        StopWatch stopWatch = new StopWatch("search products");
        stopWatch.start("search");
        MicroServiceResult<ProductSearchResultVo<ProductSearchResultEntityVo>> searchServiceResult = productSearchFeignClient.productSearch(reqVo);
        ProductSearchResultVo<ProductSearchResultEntityVo> searchResult = searchServiceResult.getData();
        stopWatch.stop();
        stopWatch.start("filter and organize response");
        List<Long> searchedProductIds = Optional.ofNullable(searchResult.getItems()).orElse(Lists.newArrayList())
                .stream().map(ProductSearchResultEntityVo::getProductId).collect(Collectors.toList());
        List<Long> orderedProductIds = Lists.newArrayList();
        String returnLastFlag = reqVo.getLastFlag();
        if (CollectionUtils.isNotEmpty(searchedProductIds)) {
            if (StringUtils.isEmpty(reqVo.getLastFlag())) {
                Integer endElementIndex = Math.min(20, searchedProductIds.size());
                IntStream.range(0, endElementIndex).forEach(index -> orderedProductIds.add(searchedProductIds.get(index)));
                returnLastFlag = String.valueOf(searchedProductIds.get(endElementIndex - 1));
            } else {
                boolean isReached = false;
                Integer fetchCount = 0;
                for (Long productId : searchedProductIds) {
                    if (isReached) {
                        orderedProductIds.add(productId);
                        fetchCount++;
                    }
                    if (fetchCount == 20) {
                        returnLastFlag = String.valueOf(productId);
                        break;
                    }
                    if (reqVo.getLastFlag().equals(String.valueOf(productId))) {
                        isReached = true;
                    }
                    returnLastFlag = String.valueOf(productId);
                }
            }
        }
        logger.info("get ordered product code list through search: {}", orderedProductIds);
        ProductAppListRespVO respVO = new ProductAppListRespVO();
        respVO.setLastFlag(returnLastFlag);
        if (CollectionUtils.isNotEmpty(orderedProductIds)) {
            List<ProductAppListItemVo> itemVOS = this.getProductPrototype(orderedProductIds, reqVo.getPriceGroupId(), reqVo.getSellerId())
                    .stream().map(ProductPrototype::toAppListItemVO).collect(Collectors.toList());
            if (logger.isDebugEnabled()) {
                logger.debug("result: {}", JSON.toJSONString(itemVOS));
            }
            respVO.setResult(itemVOS);
            respVO.setFilters(searchResult.getFilters());
            respVO.setBrands(searchResult.getBrands());
        }
        stopWatch.stop();
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

    @Override
    public List<PurchaseProductItemVO> purchaseProducts(PurchaseProductReqVO reqVO) {
        Preconditions.checkArgument(Objects.nonNull(reqVO)
                && CollectionUtils.isNotEmpty(reqVO.getProductIds())
                && Objects.nonNull(reqVO.getCompanyGroupId()) && Objects.nonNull(reqVO.getSellerId()));
        return this.getProductPrototype(reqVO.getProductIds(), reqVO.getCompanyGroupId(), reqVO.getSellerId())
                .stream().map(ProductPrototype::toPurchaseProductItemVO).collect(Collectors.toList());
    }
}
