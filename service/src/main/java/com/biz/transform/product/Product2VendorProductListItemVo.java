package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.biz.gbck.dao.mysql.repository.product.ProductAuditRepository;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.vo.product.backend.VendorProductListItemVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/10
 */
public class Product2VendorProductListItemVo implements Function<Product, VendorProductListItemVo> {

    private ProductAuditRepository productAuditRepository;

    @Nullable
    @Override
    public VendorProductListItemVo apply(@Nullable Product product) {
        VendorProductListItemVo respVo = new VendorProductListItemVo();
        respVo.setProductCode(product.getProductCode());
        respVo.setProductName(product.getName());
        respVo.setLastUpdateTime(product.getUpdateTimestamp());
        respVo.setInAudit(product.getInAudit());
        // 设置价格信息
        respVo.setLocked(product.getLocked());
        ProductAudit productAudit = productAuditRepository.findByProductId(product.getId());
        if (productAudit != null) {
            respVo.setAuditStatus(productAudit.getAuditStatus().getDescription());
        } else {
            throw new RuntimeException("找不到审核信息");
        }
        respVo.setSaleStatus(SaleStatusEnum.OFF_SALE.getDescription());
        respVo.setLogo(product.getLogo());
        respVo.setId(String.valueOf(product.getId()));
        return respVo;
    }

    public Product2VendorProductListItemVo(ProductAuditRepository productAuditRepository) {
        this.productAuditRepository = productAuditRepository;
    }
}
