package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.ProductCascade;
import com.biz.gbck.vo.product.backend.VendorCascadeListVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/9
 */
public class ProductCascade2VendorCascadeListVo implements Function<ProductCascade, VendorCascadeListVo> {

    @Nullable
    @Override
    public VendorCascadeListVo apply(@Nullable ProductCascade input) {
        return new VendorCascadeListVo(input.getName(), String.valueOf(input.getId()), input.getCreateTimestamp());
    }
}
