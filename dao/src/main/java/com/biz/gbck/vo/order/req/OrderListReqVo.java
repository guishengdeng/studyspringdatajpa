package com.biz.gbck.vo.order.req;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.enums.order.OrderStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codelogger.utils.ValueUtils;

/**
 * 订单列表请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderListReqVo extends CommonReqVoBindUserId {

    private static final long serialVersionUID = -5292997499176479924L;

    private static final int DEFAULT_PAGE_SIZE = 20;

    private String lastFlag;

    private Integer size = DEFAULT_PAGE_SIZE;

    /**
     * 订单状态 {@link OrderStatus}
     */
    private Integer status;


    public String getLastFlag() {
        return lastFlag;
    }

    public void setLastFlag(String lastFlag) {
        this.lastFlag = lastFlag;
    }

    public Integer getSize() {
        return ValueUtils.getValue(size) > 0 ? size : DEFAULT_PAGE_SIZE;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
