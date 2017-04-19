package com.biz.gbck.transform.product;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.Category;
import com.biz.gbck.dao.mysql.po.product.ExtendProperty;
import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.dao.mysql.po.product.ProductCascade;
import com.biz.gbck.dao.redis.ro.product.ProductCascadeRo;
import com.biz.gbck.vo.product.backend.CascadeExtendVo;
import com.biz.gbck.vo.product.backend.CascadeProductVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;

/**
 * 转换器(ProductCascade --> ProductCascadeRo)
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public class ProductCascade2ProductCascadeRo implements Function<ProductCascade, ProductCascadeRo> {
    @Override
    public ProductCascadeRo apply(ProductCascade po) {
        ProductCascadeRo ro = new ProductCascadeRo();
        ro.setId(po.getId());
        Category category = po.getCategory();
        if (category != null) {
            ro.setCategoryId(category.getId());
        }
        ro.setName(po.getName());
        ro.setVendorId(po.getVendorId());
        List<ExtendProperty> properties = po.getExtendProperties();
        if (CollectionUtils.isNotEmpty(properties)) {
            List<CascadeExtendVo> extendVos = Lists.newArrayList();
            Map<String, List<String>> propertyMap = Maps.newHashMap();
            for (ExtendProperty p : properties) {
                if (p != null) {
                    String extendName = p.getProductExtend().getName();
                    List<String> values;
                    if (propertyMap.containsKey(extendName)) {
                        values = propertyMap.get(extendName);
                        values.add(p.getValue());
                    } else {
                        values = Lists.newArrayList();
                        values.add(p.getValue());
                    }
                    propertyMap.put(extendName, values);
                }
            }
            if (CollectionUtils.isNotEmpty(propertyMap.entrySet())) {
                for (Map.Entry<String, List<String>> e : propertyMap.entrySet()) {
                    CascadeExtendVo vo = new CascadeExtendVo();
                    vo.setExtendName(e.getKey());
                    vo.setExtendProperties(e.getValue());
                    extendVos.add(vo);
                }
            }
            ro.setCascadeExtends(JSON.toJSONString(extendVos));
        }
        List<Product> products = po.getProducts();
        if (CollectionUtils.isNotEmpty(products)) {
            List<CascadeProductVo> vos = Lists.newArrayList();
            for (Product product : products) {
                List<ExtendProperty> propertyList = product.getProperties();
                CascadeProductVo vo = new CascadeProductVo();
                vo.setProductCode(product.getProductCode());
                if (CollectionUtils.isNotEmpty(propertyList)) {
                    Map<String, String> propertyMap = Maps.newHashMap();
                    for (ExtendProperty p : propertyList) {
                        propertyMap.put(p.getProductExtend().getName(), p.getValue());
                    }
                    vo.setProperties(propertyMap);
                }
                vos.add(vo);
            }
            ro.setProductExtendsMap(JSON.toJSONString(vos));
        }
        return ro;
    }
}
