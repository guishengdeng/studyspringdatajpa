package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.Brand;
import com.biz.gbck.vo.product.backend.IdNameVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/15
 */
public class Brand2IdNameVo implements Function<Brand, IdNameVo> {

    @Override
    public IdNameVo apply(Brand input) {
        return new IdNameVo(String.valueOf(input.getId()), input.getName());
    }

}
