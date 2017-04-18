package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.ApartTag;
import com.biz.gbck.vo.product.backend.ApartTagListItemVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
public class ApartTag2ApartTagListItemVo implements Function<ApartTag, ApartTagListItemVo> {
    @Nullable
    @Override
    public ApartTagListItemVo apply(@Nullable ApartTag input) {
        ApartTagListItemVo resp = new ApartTagListItemVo();
        resp.setId(String.valueOf(input.getId()));
        resp.setIdx(input.getIdx());
        resp.setName(input.getName());
        resp.setStatus(input.getStatus());
        return resp;
    }
}
