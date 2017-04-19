package com.biz.gbck.vo.search;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 商品过滤字段vo
 *
 * @author david-liu
 * @date 2017年01月09日
 * @reviewer
 * @see
 */
public class ProductFieldVo implements Serializable {
    private static final long serialVersionUID = 5664751926717850660L;

    /**
     * 字段名
     */
    private String field;

    /**
     * 显示名称
     */
    private String label;

    /**
     * 字段值集合
     */
    private List<ProductFieldItemVo> items;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<ProductFieldItemVo> getItems() {
        return items;
    }

    public void setItems(List<ProductFieldItemVo> items) {
        this.items = items;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
