package com.biz.gbck.vo.cart;

import com.biz.core.util.DateUtil;
import com.biz.gbck.vo.user.BaseRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * 删除购物车明细vo
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemBatchDeleteReqVo extends BaseRequestVo {

    /**
     * 商品Id集合
     */
    private Set<String> productCodes = newHashSet();

    /**
     * 操作时间
     */
    private Timestamp operateTime = DateUtil.now();


    public Set<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(Set<String> productCodes) {
        this.productCodes = productCodes;
    }

    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
