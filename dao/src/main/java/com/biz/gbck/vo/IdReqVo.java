package com.biz.gbck.vo;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;

import javax.validation.constraints.NotNull;

/**
 * Id请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class IdReqVo extends CommonReqVoBindUserId {

    private static final long serialVersionUID = 1960246981093046447L;

    @NotNull(message = "id不能为null")
    private Long id;

    public IdReqVo() {
    }

    public IdReqVo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
