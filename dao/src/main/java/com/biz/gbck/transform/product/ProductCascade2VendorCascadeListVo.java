package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.ProductCascade;
import com.biz.gbck.vo.product.backend.VendorCascadeListVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/9
 */
public class ProductCascade2VendorCascadeListVo implements Function<ProductCascade, VendorCascadeListVo> {

    @Override
    public VendorCascadeListVo apply(ProductCascade input) {
        return new VendorCascadeListVo(input.getName(), String.valueOf(input.getId()), input.getCreateTimestamp());
    }
}
