package com.biz.soa.product.service.interfaces;

import com.biz.gbck.vo.product.gbck.response.ProductFilterVO;
import java.util.List;

/**
 * Created by david-liu on 2017/05/11 09:18.
 */
public interface IProductFilterService {

    /**
     * 获取商品搜索结果过滤条件
     *
     * @param categoryId 分类ID
     * @return 商品过滤条件集合
     */
    List<ProductFilterVO> getProductSearchFilters(Long categoryId);

}
