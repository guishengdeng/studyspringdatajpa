package com.biz.gbck.vo.search;

import java.io.Serializable;

/**
 * 搜索字段 Vo
 *
 * @author david-liu
 * @date 2017年01月07日
 * @reviewer
 * @see
 */
public class SearchFieldVo implements Serializable {

    private static final long serialVersionUID = -6136223682309000511L;

    /**
     * 传递给服务器的字段名（例如：brandId 对应 ElasticSearch document 中的 field 名称）
     */
    private String field;

    /**
     * 搜索值
     */
    private String value;


    public SearchFieldVo(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public SearchFieldVo() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
