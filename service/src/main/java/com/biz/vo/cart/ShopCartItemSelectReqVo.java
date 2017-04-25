package com.biz.vo.cart;

import com.biz.core.util.DateUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 修改购物车明细vo
 *
 * @author lei
 * @date 2017/01/23
 */
public class ShopCartItemSelectReqVo implements Serializable {
    /**
     * 商品编码集合
     */
    private Set<String> pCodes;

    /**
     * 选中状态
     */
    private Boolean selected;

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

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
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
