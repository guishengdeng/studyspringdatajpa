package com.biz.gbck.vo.evaluation.backend;

import java.io.Serializable;

/**
 * 评价审核vo
 *
 * @author yangzichun
 * @date 2017/2/9
 */
public class EvaluationAuditRequestVo implements Serializable {

    private static final long serialVersionUID = 7206409017318173598L;

    private Long id;

    /**
     * 状态
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
