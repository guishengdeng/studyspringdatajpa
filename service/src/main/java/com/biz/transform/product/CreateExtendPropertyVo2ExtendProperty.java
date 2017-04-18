package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.ExtendProperty;
import com.biz.gbck.vo.product.backend.CreateExtendPropertyVo;
import com.google.common.base.Function;

public class CreateExtendPropertyVo2ExtendProperty implements Function<CreateExtendPropertyVo, ExtendProperty> {

    @Override
    public ExtendProperty apply(CreateExtendPropertyVo createVo) {
        ExtendProperty extendProperty = new ExtendProperty();
        extendProperty.setId(Long.valueOf(createVo.getId()));
        extendProperty.setValue(createVo.getValue());
        extendProperty.setStatus(createVo.getStatus());
        return extendProperty;
    }

}
