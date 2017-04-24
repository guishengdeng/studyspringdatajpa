package com.biz.gbck.vo.evaluation.backend;


import com.biz.gbck.vo.PageableRequestVo;

/**
 * 后台分页搜索评价vo
 *
 * @author yangzichun
 * @date 2017/2/9
 */
public class EvaluationQueryRequestVo extends PageableRequestVo {

    private static final long serialVersionUID = 4994408359158984565L;

    /**
     * 商店名称
     */
    private String vendorName;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 状态
     */
    private Integer status;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
