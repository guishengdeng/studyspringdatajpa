package com.biz.gbck.vo.user;

import java.io.Serializable;

/**
 * 类说明：注册成功的响应对象
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 上午9:37:34
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public class RegisterResponseVo implements Serializable {

    private static final long serialVersionUID = 2810901188743806785L;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 中台用户Id
     */
    private Long memberId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

}
