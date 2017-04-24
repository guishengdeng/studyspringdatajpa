package com.biz.gbck.vo.user;

import com.biz.gbck.vo.IRequestVo;

/**
 * @author lei
 * @date 2017年02月10日
 * @reviewer
 * @see
 */
public class UserIdRequestVo implements IRequestVo {

    private static final long serialVersionUID = -8573806836176376378L;

    /**
     * 根据用户id(非中台memberId)查询用户信息(必传)
     */
    private Long userId;

    /**
     * 渠道编码
     */
    private String channelCode;

    public UserIdRequestVo() {
    }

    public UserIdRequestVo(Long userId, String channelCode) {
        this.userId = userId;
        this.channelCode = channelCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
