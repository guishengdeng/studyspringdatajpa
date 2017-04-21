package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.vo.product.backend.ExtendPropertyListItemVo;
import com.google.common.base.Function;

/**
 * @author wangyumin
 * @date 2016年12月29日
 */
public class ExtendProperty2CategoryPropertyListItemVo implements Function<ExtendProperty, ExtendPropertyListItemVo> {

    @Override
    public ExtendPropertyListItemVo apply(ExtendProperty extendProperty) {
        ExtendPropertyListItemVo extendPropertyListItemVo = new ExtendPropertyListItemVo();
        extendPropertyListItemVo.setId(extendProperty.getId().toString());
        extendPropertyListItemVo.setValue(extendProperty.getValue());
        extendPropertyListItemVo.setIdx(extendProperty.getIdx());
        extendPropertyListItemVo.setStatus(extendProperty.getStatus());
        return extendPropertyListItemVo;
    }

}
