package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;
import java.util.List;

/**
 * 分类树形结构 Vo
 *
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public class CategoryTreeViewVo implements Serializable {

    private static final long serialVersionUID = 2511793038943916019L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类 Id
     */
    private String id;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 子分类
     */
    private List<CategoryTreeViewVo> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CategoryTreeViewVo> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTreeViewVo> children) {
        this.children = children;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
