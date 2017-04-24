package com.biz.gbck.vo.evaluation.backend;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 后台管理商品列表vo
 *
 * @author yangzichun
 * @date 2017/2/17
 */
public class EvaluationResponseVo implements Serializable {
    private Long id;

    private Long productId;

    private String orderCode;

    private Timestamp evaluationTime;

    private String vendorName;

    private Integer commonStatus;

    private String productCode;
    /**
     * 避免前端js精度丢失
     */
    private String evaluationIdString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(Integer commonStatus) {
        this.commonStatus = commonStatus;
    }

    public String getEvaluationIdString() {
        return evaluationIdString;
    }

    public void setEvaluationIdString(String evaluationIdString) {
        this.evaluationIdString = evaluationIdString;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
