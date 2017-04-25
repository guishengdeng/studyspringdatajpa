package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.vo.product.backend.ExtendStringVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @date 2017/1/17
 * @reviewer
 */
public class ExtendProperty2ExtendStringVo implements Function<ExtendProperty, ExtendStringVo> {
    @Override
    public ExtendStringVo apply(ExtendProperty input) {
        return new ExtendStringVo(input.getProductExtend().getName(), input.getValue());
    }
}
