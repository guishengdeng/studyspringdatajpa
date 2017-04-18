package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.gbck.vo.product.backend.IdNameVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/15
 */
public class Brand2IdNameVo implements Function<Brand, IdNameVo> {

    @Nullable
    @Override
    public IdNameVo apply(@Nullable Brand input) {
        return new IdNameVo(String.valueOf(input.getId()), input.getName());
    }

}
