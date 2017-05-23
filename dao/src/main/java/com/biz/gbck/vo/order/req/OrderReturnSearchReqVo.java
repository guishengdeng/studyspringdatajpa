package com.biz.gbck.vo.order.req;

import com.biz.gbck.enums.order.RefundStatus;
import com.biz.gbck.enums.order.ReturnStatus;
import com.biz.gbck.enums.order.ReturnType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * Created by liqi1 on 2017/5/17.
 */
public class OrderReturnSearchReqVo  {

    /**
     * 退货单ID
     */

    private Long id;

    /**
     * 退货单状态
     */
    private ReturnStatus status;

    /**
     * 退款单状态
     */

    private RefundStatus refundStatus;

    /**
     * 退货类型
     */
    private ReturnType returnType;

    /**
     *售后单编号
     */
    private String returnCode;

    /**
     *原销售单号
     */
    private String orderCode;

    /**
     * 退货申请时间
     */
    private String startTime;

    /**
     * 退货申请时间
     */
    private String endTime;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(ReturnStatus status) {
        this.status = status;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(ReturnType returnType) {
        this.returnType = returnType;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}


