package com.biz.gbck.vo.order.resp;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author lei
 * @date 2017年05月07日
 * @reviewer
 */
public class OrderListRespVo implements Serializable {
    private static final long serialVersionUID = -5113775306566869929L;

    /**
     * 最后访问标示
     */
    private String lastFlag;

    //集合
    private List<OrderRespVo> list = Lists.newArrayList();

    public OrderListRespVo() {
    }

    public OrderListRespVo(String lastFlag, List<OrderRespVo> list) {
        this();
        this.lastFlag = lastFlag;
        this.list = list;
    }

    public String getLastFlag() {
        return lastFlag;
    }

    public void setLastFlag(String lastFlag) {
        this.lastFlag = lastFlag;
    }

    public List<OrderRespVo> getList() {
        return list;
    }

    public void setList(List<OrderRespVo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
