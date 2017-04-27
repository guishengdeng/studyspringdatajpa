package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.ProductAudit;
import com.biz.gbck.vo.product.backend.PlatformProductAuditDetailVo;
import com.google.common.base.Function;

/**
 * 转换器(ProductAudit --> PlatformProductAuditDetailVo)
 * 未设置商品扩展属性和商家名称
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class ProductAudit2PlatformProductAuditDetailVo implements Function<ProductAudit, PlatformProductAuditDetailVo> {

    public ProductAudit2PlatformProductAuditDetailVo() {

    }


    @Override
    public PlatformProductAuditDetailVo apply(ProductAudit po) {
        PlatformProductAuditDetailVo vo = new PlatformProductAuditDetailVo();
        vo.setId(String.valueOf(po.getId()));
        vo.setProductCode(po.getProductCode());
        vo.setProductName(po.getName());
        vo.setVendorId(String.valueOf(po.getVendorId()));
        vo.setSubTitle(po.getSubTitle());
        vo.setI18nCode(po.getI18nCode());
        vo.setBreif(po.getBreif());
        if (po.getBrand() != null) {
            vo.setBrandName(po.getBrand().getName());
        }
        if (po.getCategory() != null) {
            vo.setCategoryName(po.getCategory().getName());
        }
        vo.setLogo(po.getLogo());
        vo.setIntroImages(po.getIntroImages());
        vo.setImages(po.getImages());
        vo.setAuditStatus(po.getAuditStatus());
        vo.setSeoTitle(po.getSeoTitle());
        vo.setSeoKeywords(po.getSeoKeywords());
        vo.setSeoDescription(po.getSeoDescription());
        vo.setWeight(po.getWeight());
        return vo;
    }
}
