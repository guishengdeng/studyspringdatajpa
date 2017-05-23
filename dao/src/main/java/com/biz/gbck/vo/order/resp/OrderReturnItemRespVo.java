package com.biz.gbck.vo.order.resp;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.enums.order.ReturnStatus;
import com.biz.gbck.enums.order.ReturnType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

/**
 * Created by liqi1 on 2017/5/21.
 */
public class OrderReturnItemRespVo {

    /**
     * 退货单号
     */
    private String returnCode;

    /**
     * 原销售单
     */
    private Order order;

    /**
     * 退货金额
     */
    private Integer returnAmount;

    /**
     * 退货申请时间
     */
    @JsonIgnore
    private Timestamp creatTime;

    /**
     * 退货类型
     */
    private ReturnType returnType;

    /**
     * 退货单状态
     */
    private ReturnStatus returnStatus;

    /**
     * 退款单状态
     */



}
