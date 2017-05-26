package com.biz.soa.order.util;

import com.biz.gbck.vo.order.resp.IProduct;
import org.codelogger.utils.ValueUtils;

import java.util.List;

/**
 * 订单辅助工具
 *
 * @author lei
 * @date 2017年05月20日
 * @reviewer
 * @see
 */
public class OrderUtil {

    public static Integer calcOrderAmount(List<? extends IProduct> productVos) {
        int orderAmount = 0;
        for (IProduct productVo : productVos) {
            orderAmount += ValueUtils.getValue(productVo.getSalePrice()) * ValueUtils.getValue(productVo.getQuantity());
        }
        return orderAmount;

    }
}
