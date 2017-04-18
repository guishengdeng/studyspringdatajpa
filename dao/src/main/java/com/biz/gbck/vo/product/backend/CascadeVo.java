package com.biz.gbck.vo.product.backend;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/**
 * 配置商品Vo
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public class CascadeVo implements Serializable {
    private static final long serialVersionUID = 4688028397310301014L;

    private List<CascadeExtendVo> extendList = Lists.newArrayList();

    private List<CascadeProductVo> productList = Lists.newArrayList();

    public List<CascadeExtendVo> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<CascadeExtendVo> extendList) {
        this.extendList = extendList;
    }

    public List<CascadeProductVo> getProductList() {
        return productList;
    }

    public void setProductList(List<CascadeProductVo> productList) {
        this.productList = productList;
    }
}
