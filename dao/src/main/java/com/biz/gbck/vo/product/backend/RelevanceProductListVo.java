package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * @author 江南
 * @date 2017/1/24
 * @reviewer
 */

public class RelevanceProductListVo implements IRequestVo, Serializable {


    private static final long serialVersionUID = -7997998601224974574L;

    /**
     * 被配置关联商品code
     */
    private String sourceProductCode;

    /**
     * 商家ID
     */
    private String vendorId;

    /**
     * 关联商品code List
     */
    private List<String> relevanceProductCodeList;

    public RelevanceProductListVo() {
    }

    public RelevanceProductListVo(String sourceProductCode, List<String> relevanceProductCodeList) {
        this.sourceProductCode = sourceProductCode;
        this.relevanceProductCodeList = relevanceProductCodeList;
    }

    public String getSourceProductCode() {
        return sourceProductCode;
    }

    public void setSourceProductCode(String sourceProductCode) {
        this.sourceProductCode = sourceProductCode;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public List<String> getRelevanceProductCodeList() {
        return relevanceProductCodeList;
    }

    public void setRelevanceProductCodeList(List<String> relevanceProductCodeList) {
        this.relevanceProductCodeList = relevanceProductCodeList;
    }

    public Boolean isValid() {
        return CollectionUtils.isNotEmpty(relevanceProductCodeList) && StringUtils.isNotBlank(sourceProductCode) &&
                StringUtils.isNotBlank(vendorId);
    }

    @Override
    public String toString() {
        return "RelevanceProductListVo [sourceProductCode=" + sourceProductCode + ", vendorId=" + vendorId
                + ", relevanceProductCodeList=" + relevanceProductCodeList + "]";
    }


}
