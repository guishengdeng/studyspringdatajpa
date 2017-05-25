package com.biz.gbck.vo.order.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created lzz on 2017/5/23.
 */
public class SalesSearchVo implements Serializable{

    private static final long serialVersionUID = -6121103040151462034L;

    /**
     * 下单日期
     */
    private Timestamp orderTime;

    /**
     * 发货日期
     */
    private Date deliveryTime;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 会员账号（店铺手机号）
     */
    private String mobile;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
