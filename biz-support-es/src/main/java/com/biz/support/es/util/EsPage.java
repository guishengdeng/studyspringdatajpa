package com.biz.support.es.util;

import java.io.Serializable;

/**
 * Elastic 分页对象
 *
 * @author david-liu
 * @date 2017年01月06日
 * @reviewer
 * @see
 */
public class EsPage implements Serializable {

    private static final long serialVersionUID = 6091890904439722054L;

    /**
     * 起始下标
     */
    private Integer from;

    /**
     * 获取页大小
     */
    private Integer size;

    public EsPage(Integer from, Integer size) {
        this.from = from;
        this.size = size;
    }

    public EsPage() {
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "EsPageVo{" +
                "from=" + from +
                ", size=" + size +
                '}';
    }
}
