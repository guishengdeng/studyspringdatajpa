package com.biz.soa.builder;

import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.vo.order.req.OrderApplyReturnReqVo;

/**
 * 订单Builder
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderReturnBuilder extends AbstractOrderBuilder {

    private OrderReturn orderReturn;

    public static OrderReturnBuilder createBuilder(OrderApplyReturnReqVo reqVo){
        OrderReturnBuilder builder = new OrderReturnBuilder();
        builder.orderReturn = new OrderReturn();
        if (reqVo.getCause() != null) {

        }


//        builder.orderReturn.setReason(reqVo.getCause());
        return builder;
    }






}
