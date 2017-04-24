package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.ExtendProperty;
import com.biz.gbck.vo.product.backend.ExtendPropertyVo;
import com.google.common.base.Function;

public class ExtendProperty2ExtendPropertyVo implements Function<ExtendProperty, ExtendPropertyVo> {

    @Override
    public ExtendPropertyVo apply(ExtendProperty input) {
        ExtendPropertyVo vo = new ExtendPropertyVo();
        if (input.getId() != null) {
            vo.setId(String.valueOf(input.getId()));
        }
        vo.setValue(input.getValue());
        return vo;
    }

}
