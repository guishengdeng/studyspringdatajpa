package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.VendorTypeEnum;
import java.io.Serializable;
import java.util.List;

/**
 * 商品 Vo 抽象(用于 Product的 fromVo)
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public interface IProductAuditVo extends Serializable {

    Long getVendorId();

    Long getId();

    String getName();

    String getSubTitle();

    String getProductCode();

    String getI18nCode();

    String getBreif();

    String getLogo();

    List<String> getImages();

    List<String> getIntroImages();

    String getSeoTitle();

    String getSeoKeywords();

    String getSeoDescription();

    List<ExtendVo> getExtendVos();

    Long getBrandId();

    Long getCategoryId();

    String toString();

    Integer getWeight();

    VendorTypeEnum getType();
}
