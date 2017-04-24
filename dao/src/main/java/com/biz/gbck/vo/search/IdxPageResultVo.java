package com.biz.gbck.vo.search;

import java.io.Serializable;
import java.util.List;

/**
 * 索引分页结果对象
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
public class IdxPageResultVo<T> implements Serializable {
    private static final long serialVersionUID = -668512994162608654L;

    /**
     * 是否还有下页
     */
    private Boolean hasNextPage;

    /**
     * 索引Vo集合
     */
    private List<T> idxVos;

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getIdxVos() {
        return idxVos;
    }

    public void setIdxVos(List<T> idxVos) {
        this.idxVos = idxVos;
    }
}
