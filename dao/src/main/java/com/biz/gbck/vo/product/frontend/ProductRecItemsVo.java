package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import java.util.List;

/**
 * @author 江南
 * @date 2017/1/20
 * @reviewer
 */
public class ProductRecItemsVo implements Serializable {

    private static final long serialVersionUID = -6563218763601164849L;

    /**
     * 效果追踪ID
     */
    private String traceId;

    /**
     * 推荐结果sku 集合
     */
    private List<String> productCodes;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }

    public ProductRecItemsVo(String traceId, List<String> productCodes) {
        this.traceId = traceId;
        this.productCodes = productCodes;
    }
}
