package com.biz.gbck.vo.search;

import java.io.Serializable;

/**
 * 增量索引 Vo
 *
 * @author david-liu
 * @date 2017年01月11日
 * @reviewer
 * @see
 */
public class IncrIndexVo implements Serializable {

    private static final long serialVersionUID = -627892944069933513L;

    /**
     * 索引唯一标识符
     */
    private String identityCode;

    /**
     * 是否将要被删除
     */
    private Boolean willBeDeleted = false;

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public Boolean getWillBeDeleted() {
        return willBeDeleted;
    }

    public void setWillBeDeleted(Boolean willBeDeleted) {
        this.willBeDeleted = willBeDeleted;
    }

    public IncrIndexVo(String identityCode, Boolean willBeDeleted) {
        this.identityCode = identityCode;
        this.willBeDeleted = willBeDeleted;
    }

    public IncrIndexVo(String identityCode) {
        this.identityCode = identityCode;
    }

    public IncrIndexVo() {
    }
}
