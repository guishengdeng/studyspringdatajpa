package com.biz.service.product.backend;

import com.biz.core.page.PageResult;
import com.biz.gbck.vo.product.backend.*;
import java.util.List;

/**
 * 商品 Service (平台后台用)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public interface PlatformProductService {

    /**
     * 根据商品ID集合查询商品
     */
    List<IdNameVo> findProductByIds(List<Long> ids);

    /**
     * 根据searchKey 和 searchValue,模糊查询对应商品
     *
     * @param searchPageVo name 或 sku
     */
    PageResult<IdNameVo> findProductByKeyValue(SearchPageVo searchPageVo);

    /**
     * 根据商品编码查找商品id
     *
     * @return product id
     */
    Long getProductIdByProductCode(String productCode);

    /**
     * 根据商品名称模糊查询商品ID集合
     */
    List<Long> findProductIdByName(String name);


    /**
     * 根据productCode查询商品详情
     */
    ProductDetailVo getProductDetailVo(String productCode);

    BootstrapTablePageResult<TypeBBlackProductItemVo> listTypeBBlackList();

    void appendTypeBBlackList(String productCode);

    void removeFromTypeBBlackList(String productCode);
}
