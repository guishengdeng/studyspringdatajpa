package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * 用于包装对象返回给bootstrap table
 *
 * @author david-liu
 * @date 2016年08月09日
 * @reviewer
 * @see
 */
public class BootstrapTablePageResult<T extends Object> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2001071263395573698L;

    /**
     * 总共有多少条记录
     */
    private int total;

    private List<T> rows;

    public BootstrapTablePageResult(int total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public BootstrapTablePageResult() {
        super();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
