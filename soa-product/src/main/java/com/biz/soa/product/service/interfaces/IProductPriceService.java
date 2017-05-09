package com.biz.soa.product.service.interfaces;

import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.vo.price.PriceGroupProductCodePriceReqVO;
import com.biz.gbck.vo.price.PriceGroupProductCodesPriceReqVO;
import com.biz.gbck.vo.price.PriceGroupsProductCodePriceReqVO;
import java.util.List;

/**
 * 商品价格Service
 *
 * Created by david-liu on 2017/05/02 11:29.
 */
public interface IProductPriceService {

    List<PriceRO> productPrices(PriceGroupProductCodesPriceReqVO reqVo);

    PriceRO productPrice(PriceGroupProductCodePriceReqVO reqVO);

    List<PriceRO> productPrices(PriceGroupsProductCodePriceReqVO reqVO);
}
