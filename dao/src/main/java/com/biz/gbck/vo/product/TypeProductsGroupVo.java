package com.biz.gbck.vo.product;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品分组Vo(将商品编码按照类型分组)
 *
 * @author david-liu
 * @date 2017年01月18日
 * @reviewer
 */
public class TypeProductsGroupVo implements Serializable {
    private static final long serialVersionUID = -247876732574093676L;

    /**
     * A类商品编码集合
     */
    private List<String> typeAProductCodes;

    /**
     * B类商品编码集合
     */
    private List<String> typeBProductCodes;

    /**
     * 所有有效的商品编码
     */
    private List<String> allProductCodes;

    /**
     * 商品序号类型对应Vo集合
     */
    private List<ProductTypeWithIndexVo> indexVos;

    public List<String> getTypeAProductCodes() {
        if (CollectionUtils.isEmpty(typeAProductCodes)) {
            typeAProductCodes = Lists.newArrayList();
        }
        return typeAProductCodes;
    }

    public void setTypeAProductCodes(List<String> typeAProductCodes) {
        this.typeAProductCodes = typeAProductCodes;
    }

    public List<String> getTypeBProductCodes() {
        if (CollectionUtils.isEmpty(typeBProductCodes)) {
            typeBProductCodes = Lists.newArrayList();
        }
        return typeBProductCodes;
    }

    public void setTypeBProductCodes(List<String> typeBProductCodes) {
        this.typeBProductCodes = typeBProductCodes;
    }

    public List<ProductTypeWithIndexVo> getIndexVos() {
        if (CollectionUtils.isEmpty(indexVos)) {
            indexVos = Lists.newArrayList();
        }
        return indexVos;
    }

    public void setIndexVos(List<ProductTypeWithIndexVo> indexVos) {
        this.indexVos = indexVos;
    }

    public List<String> getAllProductCodes() {
        return allProductCodes;
    }

    public void setAllProductCodes(List<String> allProductCodes) {
        this.allProductCodes = allProductCodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
