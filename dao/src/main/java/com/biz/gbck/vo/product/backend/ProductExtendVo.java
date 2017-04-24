package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;
import java.util.List;

/**
 * 分类属性 Vo
 *
 * @author yumin.wang
 * @date 2016年12月20日
 * @reviewer
 * @see
 */
public class ProductExtendVo implements Serializable {

    private static final long serialVersionUID = -1707108134941993041L;

    /**
     * ID
     */
    private Long id;

    /**
     * 商品的扩展属性名
     */
    private String name;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 显示的顺序
     */
    private Integer idx;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 扩展属性值
     */
    private List<ExtendPropertyVo> properties;


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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public List<ExtendPropertyVo> getProperties() {
        return properties;
    }

    public void setProperties(List<ExtendPropertyVo> properties) {
        this.properties = properties;
    }

}
