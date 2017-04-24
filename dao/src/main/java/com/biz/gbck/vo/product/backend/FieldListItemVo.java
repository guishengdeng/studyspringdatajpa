package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/21
 */
public class FieldListItemVo implements Serializable {

    private String categoryId;

    private String fieldName;

    private Integer idx;

    public FieldListItemVo() {
    }

    public FieldListItemVo(String categoryId, String fieldName, Integer idx) {
        this.categoryId = categoryId;
        this.fieldName = fieldName;
        this.idx = idx;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Boolean isValid() {
        if (categoryId != null && fieldName != null && idx != null) {
            return true;
        } else {
            return false;
        }
    }
}
