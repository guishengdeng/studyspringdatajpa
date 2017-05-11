package com.biz.gbck.vo.search;

import java.io.Serializable;

/**
 * 商品搜索结果字段项 Vo
 *
 * @author david-liu
 * @date 2017年01月09日
 * @reviewer
 * @see
 */
public class ProductFieldItemVo implements Serializable {
    private static final long serialVersionUID = 5286202388171140915L;

    /**
     * 值
     */
    private String value;

    /**
     * 标题
     */
    private String title;

    /**
     * 数量
     */
    private Integer count;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductFieldItemVo{" +
                "value='" + value + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }
}
