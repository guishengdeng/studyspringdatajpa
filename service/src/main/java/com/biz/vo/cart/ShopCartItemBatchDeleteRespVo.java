package com.biz.vo.cart;

import com.biz.core.util.DateUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import static com.google.common.collect.Sets.newHashSet;

/**
 * 删除购物车明细vo
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemBatchDeleteRespVo implements Serializable {

    /**
     * 商品Id集合
     */
    private Set<String> pCodes = newHashSet();

    /**
     * 操作时间
     */
    private Timestamp operateTime = DateUtil.now();


    public Set<String> getpCodes() {
        return pCodes;
    }

    public void setpCodes(Set<String> pCodes) {
        this.pCodes = pCodes;
    }

    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }
}
