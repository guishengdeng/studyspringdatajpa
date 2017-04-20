package com.biz.gbck.transform.product;

import com.alibaba.fastjson.JSON;
import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.product.*;
import com.biz.gbck.dao.mysql.po.product.bbc.GroupProduct;
import com.biz.gbck.dao.mysql.po.product.bbc.GroupProductItem;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.dao.redis.ro.product.ProductRo;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.RapidProductItemVo;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 转换器(Product --> ProductRo)
 *
 * @author david-liu
 * @date 2017年02月24日
 * @reviewer
 */
public class Product2ProductRo implements Function<Product, ProductRo> {

    @Override
    public ProductRo apply(Product po) {
        ProductRo ro = new ProductRo();
        ro.setId(po.getProductCode());
        ro.setVendorId(po.getVendorId());
        ro.setProductCode(po.getProductCode());
        ro.setName(po.getName());
        ro.setSubTitle(po.getSubTitle());
        ro.setI18nCode(po.getI18nCode());
        ro.setBreif(po.getBreif());
        Brand brand = po.getBrand();
        if (brand != null) {
            ro.setBrandId(brand.getId());
            ro.setBrandName(brand.getName());
        }
        if (po.getCategory() != null) {
            ro.setCategoryId(po.getCategory().getId());
        }
        if (CollectionUtils.isNotEmpty(po.getIntroImages())) {
            ro.setIntroImages(StringUtils.join(po.getIntroImages(), ","));
        }
        ro.setLogo(po.getLogo());
        List<SaleTag> saleTags = po.getSaleTags();
        if (CollectionUtils.isNotEmpty(saleTags)) {
            ro.setSaleTagIds(po.getSaleTagIds());
        }
        List<ApartTag> apartTags = po.getApartTags();
        if (CollectionUtils.isNotEmpty(apartTags)) {
            ro.setApartTagIds(po.getApartTagIds());
        }
        ro.setSeoTitle(po.getSeoTitle());
        ro.setSeoKeywords(po.getSeoKeywords());
        ro.setSeoDescription(po.getSeoDescription());
        if (CollectionUtils.isNotEmpty(po.getImages())) {
            ro.setImages(StringUtils.join(po.getImages(), ","));
        }
        ro.setProductId(po.getId());
        // B类商品需要判断PO中B类商品的上架标识
        if (po.getTypeBSaleStatus() != null && po.getProductType() != null && po.getProductType() == VendorTypeEnum.TYPE_B) {
            ro.setSaleStatus(po.getTypeBSaleStatus().getValue());
        } else {
            ro.setSaleStatus(SaleStatusEnum.ON_SALE.getValue());
        }
        ro.setProductType(po.getProductType().getValue());
        ro.setOnSaleTime(DateUtil.now());
        List<ExtendProperty> properties = po.getProperties();
        if (CollectionUtils.isNotEmpty(properties)) {
            ro.setPropertiesJson(po.getPropertyJson());
        }
        boolean isRapidProduct = po.getRapidProduct() != null && po.getRapidProduct();
        ro.setRapidProduct(isRapidProduct);

        if (isRapidProduct) {
            GroupProduct groupProduct = po.getGroupProduct();
            Preconditions.checkArgument(groupProduct != null && CollectionUtils.isNotEmpty(groupProduct.getItemList()));
            List<RapidProductItemVo> rapidProductItemVoList = Lists.newArrayList();
            List<GroupProductItem> groupProductItems = groupProduct.getItemList();
            for (GroupProductItem groupProductItem : groupProductItems) {
                RapidProductItemVo rapidProductItemVo = new RapidProductItemVo();
                rapidProductItemVo.setProductCode(groupProductItem.getProduct().getProductCode());
                rapidProductItemVo.setQuantity(groupProductItem.getQuantity());
                rapidProductItemVoList.add(rapidProductItemVo);
            }
            ro.setRapidProductInfo(JSON.toJSONString(rapidProductItemVoList));
        }
        ro.setWeight(po.getWeight());
        return ro;
    }
}
