package com.biz.gbck.vo.admin;

import com.biz.gbck.enums.CommonStatusEnum;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * AdminReqVo
 *
 * @author guisheng.deng
 * @date 2017年04月28日
 * @reviewer
 * @description：用于接收前段页面的参数
 * @see
 */
public class AdminReqVo {
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotNull(message = "状态不能为空")
    private CommonStatusEnum status;
    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}