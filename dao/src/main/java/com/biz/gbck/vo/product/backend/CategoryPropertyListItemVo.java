package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;
import java.util.List;

/**
 * 分类属性列表Vo
 *
 * @author wangyumin
 * @date 2016年12月27日
 */
public class CategoryPropertyListItemVo implements Serializable {

    private static final long serialVersionUID = 4304424248617095301L;

    /**
     * 分类属性ID
     */
    private String id;

    /**
     * 分类属性名称
     */
    private String name;

    /**
     * 分类属性的排序
     */
    private Integer idx;

    /**
     * 是否启用该分类属性
     */
    private CommonStatusEnum status;

    /**
     * 属性值
     */
    private List<ExtendPropertyVo> extendProperty;

    public List<ExtendPropertyVo> getExtendProperty() {
        return extendProperty;
    }

    public void setExtendProperty(List<ExtendPropertyVo> extendProperty) {
        this.extendProperty = extendProperty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

}
