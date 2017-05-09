package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;
import java.util.List;

/**
 * 商品属性字段Vo
 *
 * Created by david-liu on 2017/04/28 09:27.
 */
public class ProductSearchFieldVo implements Serializable {
    private static final long serialVersionUID = 5488735707974606163L;

    private String field;

    private List<String> values;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ProductSearchFieldVo{" +
                "field='" + field + '\'' +
                ", values=" + values +
                '}';
    }
}
