package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.ExtendProperty;
import com.biz.gbck.vo.product.backend.ExtendStringVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @date 2017/1/17
 * @reviewer
 */
public class ExtendProperty2ExtendStringVo implements Function<ExtendProperty, ExtendStringVo> {
    @Nullable
    @Override
    public ExtendStringVo apply(@Nullable ExtendProperty input) {
        return new ExtendStringVo(input.getProductExtend().getName(), input.getValue());
    }
}
