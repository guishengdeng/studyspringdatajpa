package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.gbck.vo.product.backend.BrandAndVendorInfoVo;
import com.google.common.base.Function;

public class Brand2BrandAndVendorInfoVo implements Function<Brand, BrandAndVendorInfoVo> {

    @Override
    public BrandAndVendorInfoVo apply(Brand input) {
        BrandAndVendorInfoVo brand = new BrandAndVendorInfoVo();
        brand.setBrandLogo(input.getLogo());
        brand.setBrandName(input.getName());
        //		Random random=new Random();
        //		Vendor vendor=input.getVendors().get(random.nextInt(input.getVendors().size()));
        //		brand.setVendorName(vendor.getVendorName());
        //		brand.setVendorLogo(vendor.getLogo());
        return brand;
    }
}
