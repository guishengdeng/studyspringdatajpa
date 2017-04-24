package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.vo.product.backend.TypeBProductListItemVo;
import com.google.common.base.Function;
import java.text.SimpleDateFormat;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/15
 */
public class Product2TypeBProductListItemVo implements Function<Product, TypeBProductListItemVo> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public TypeBProductListItemVo apply(Product input) {
        TypeBProductListItemVo respVo = new TypeBProductListItemVo();
        respVo.setProductCode(input.getProductCode());
        if (input.getBrand() != null) {
            respVo.setBrandName(input.getBrand().getName());
        }
        if (input.getCategory() != null) {
            respVo.setCategoryName(input.getCategory().getName());
        }
        respVo.setProductName(input.getName());
        if (input.getTypeBSaleStatus() != null) {
            respVo.setSaleStatus(input.getTypeBSaleStatus().getDescription());
        }
        if (respVo.getUpdateTimeStamp() != null) {
            respVo.setUpdateTimeStamp(sdf.format(input.getUpdateTimestamp()));
        }
        respVo.setId(String.valueOf(input.getId()));
        return respVo;
    }

}
