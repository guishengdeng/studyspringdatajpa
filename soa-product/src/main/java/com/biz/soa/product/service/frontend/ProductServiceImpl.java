package com.biz.soa.product.service.frontend;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductItemVO;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.service.product.frontend.ProductService;
import com.biz.soa.product.vo.ProductPrototype;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 商品ServiceImpl
 *
 * Created by david-liu on 2017/05/02 10:50.
 */
public class ProductServiceImpl extends AbstractProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private IProductSearchService searchService;

    @Override
    public List<ProductItemVO> searchProducts(ProductAppListReqVo reqVo) {
        Preconditions.checkArgument(reqVo != null && reqVo.getPriceGroupId() != null, "商品价格组ID不能为空");
        if (logger.isDebugEnabled()) {
            logger.debug("search products reqVo: {}", reqVo);
        }
        ProductSearchResultVo<ProductSearchResultEntityVo> searchResult = searchService.searchProducts(reqVo);
        List<String> orderedProductCodes = Optional.ofNullable(searchResult.getItems()).orElse(Lists.newArrayList())
                .stream().map(ProductSearchResultEntityVo::getProductCode).collect(Collectors.toList());
        logger.info("get ordered product code list through search: {}", orderedProductCodes);
        List<ProductItemVO> itemVOS = this.getProductPrototype(orderedProductCodes, reqVo.getPriceGroupId(), reqVo.getSellerId())
                .stream().map(ProductPrototype::toProductItemVO).collect(Collectors.toList());
        if (logger.isDebugEnabled()) {
            logger.debug("result: {}", JSON.toJSONString(itemVOS));
        }

        return itemVOS;
    }

    @Override
    public ProductAppDetailRespVO productDetail(ProductAppDetailReqVo reqVo) {
        Preconditions.checkArgument(reqVo != null && reqVo.getPriceGroupId() != null && reqVo.getSellerId() != null);
        if (logger.isDebugEnabled()) {
            logger.debug("product detail reqVo: {}", reqVo);
        }
        return this.getProductPrototype(reqVo.getProductCode(), reqVo.getPriceGroupId(), reqVo.getSellerId()).toAppDetailRespVO();
    }
}
