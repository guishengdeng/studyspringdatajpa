package com.biz.gbck.vo;

import java.io.Serializable;

/**
 * 范围Vo
 *
 * @author david-liu
 * @date 2017年01月13日
 * @reviewer
 * @see
 */
public class IndexRangeVo implements Serializable {
    private static final long serialVersionUID = -6403867536808417061L;

    /**
     * 起始位置
     */
    private Integer from;

    private Integer to;

    public IndexRangeVo(Integer from, Integer to) {
        this.from = from;
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
