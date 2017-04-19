package com.biz.gbck.vo.user;

import com.biz.gbck.vo.IRequestVo;

/**
 * @author lei
 * @date 2017年02月10日
 * @reviewer
 * @see
 */
public class MemberIdRequestVo implements IRequestVo {

    private static final long serialVersionUID = -8573806836176376378L;

    /**
     * 根据会员id(中台memberId)查询用户信息(必传)
     */
    private Long memberId;

    /**
     * 渠道编码(必传)
     */
    private String channelCode;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
