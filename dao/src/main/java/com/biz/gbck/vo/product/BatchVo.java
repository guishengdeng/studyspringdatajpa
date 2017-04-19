package com.biz.gbck.vo.product;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/**
 * 批量处理数据Vo
 *
 * @author david-liu
 * @date 2017年01月13日
 * @reviewer
 * @see
 */
public class BatchVo<T> implements Serializable {
    private static final long serialVersionUID = 3420359110618524920L;

    /**
     * 价格列表
     */
    private List<T> data = Lists.newArrayList();

    /**
     * 数据分片大小
     */
    private List<Integer> pieces = Lists.newArrayList();

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<Integer> getPieces() {
        return pieces;
    }

    public void setPieces(List<Integer> pieces) {
        this.pieces = pieces;
    }
}
