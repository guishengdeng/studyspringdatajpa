package com.biz.gbck.dao.redis.ro.product.master;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.vo.product.backend.CascadeExtendVo;
import com.biz.gbck.vo.product.backend.CascadeProductVo;
import com.biz.gbck.vo.product.backend.CascadeVo;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * 商品配置Ro
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
@Ro(key = "product:ProductCascadeRo")
public class ProductCascadeRo extends BaseRedisObject<Long> implements Serializable {
    private static final long serialVersionUID = -1413200852416402030L;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 商家ID
     */
    private Long vendorId;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置的属性(以JSON方式存放)
     */
    private String cascadeExtends;

    /**
     * 配置的商品图(以JSON方式存放)
     */
    private String productExtendsMap;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CascadeExtendVo> getCascadeExtends() {
        if (StringUtils.isBlank(cascadeExtends)) {
            return Lists.newArrayList();
        } else {
            return JSON.parseArray(this.cascadeExtends, CascadeExtendVo.class);
        }
    }

    public void setCascadeExtends(String cascadeExtends) {
        this.cascadeExtends = cascadeExtends;
    }

    public List<CascadeProductVo> getProductExtendsMap() {
        if (StringUtils.isBlank(productExtendsMap)) {
            return Lists.newArrayList();
        } else {
            return JSON.parseArray(productExtendsMap, CascadeProductVo.class);
        }
    }

    public void setProductExtendsMap(String productExtendsMap) {
        this.productExtendsMap = productExtendsMap;
    }

    public CascadeVo toCascadeVo() {
        CascadeVo vo = new CascadeVo();
        vo.setExtendList(this.getCascadeExtends());
        vo.setProductList(this.getProductExtendsMap());
        return vo;
    }
}
