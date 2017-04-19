package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author wangyumin
 * @date 2016年12月18日
 */

public class CreateCategoryPropertyVo implements Serializable {

    private static final long serialVersionUID = -4449507590844428143L;

    /**
     * ID
     */
    private Long id;

    /**
     * 属性名
     */
    private String name;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 属性所在分类ID
     */
    private Long categoryId;

    /**
     * 是否启用
     */
    private CommonStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

}
