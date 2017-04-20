package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.ProductAudit;
import com.biz.gbck.dao.mysql.po.product.bbc.Vendor;
import com.biz.gbck.dao.mysql.repository.vendor.VendorRepository;
import com.biz.gbck.vo.product.backend.PlatformProductAuditListItemVo;
import com.google.common.base.Function;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;

/**
 * 转换器(商品审核记录 Po --> 商家待审核商品列表项 Vo)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class ProductAudit2PlatformProductAuditListItemVo implements Function<ProductAudit, PlatformProductAuditListItemVo> {

    private SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private VendorRepository vendorRepository;

    public ProductAudit2PlatformProductAuditListItemVo(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public PlatformProductAuditListItemVo apply(ProductAudit productAudit) {
        PlatformProductAuditListItemVo vo = new PlatformProductAuditListItemVo();
        vo.setId(String.valueOf(productAudit.getId()));
        vo.setVendorName(productAudit.getVendorName());
        vo.setProductCode(productAudit.getProductCode());
        vo.setProductName(productAudit.getName());
        vo.setBrandName(productAudit.getBrand().getName());
        vo.setCategoryName(productAudit.getCategory().getName());
        vo.setUpdateTimeStamp(sfd.format(productAudit.getUpdateTimestamp()));
        vo.setAuditStatus(productAudit.getAuditStatus().getDescription());
        if (StringUtils.isBlank(productAudit.getVendorName())) {
            Vendor vendor = vendorRepository.findOne(productAudit.getVendorId());
            if (vendor == null) {
                vo.setVendorName("店铺名称异常 Vendorid=" + productAudit.getVendorId());
            } else {
                vo.setVendorName(vendor.getVendorName());
            }
        }
        return vo;
    }
}
