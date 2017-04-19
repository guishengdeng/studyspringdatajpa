package com.biz.gbck.vo.user;

import com.biz.gbck.vo.IRequestVo;
import java.util.Set;

/**
 * @author lei
 * @date 2017年02月10日
 * @reviewer
 * @see
 */
public class BatchMemberIdRequestVo implements IRequestVo {

    private static final long serialVersionUID = -8573806836176376378L;

    /**
     * 根据会员ids(中台memberId)查询用户信息(必传)
     */
    private Set<Long> memberIds;
    /**
     * 渠道编码(必传)
     */
    private String channelCode;

    public Set<Long> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(Set<Long> memberIds) {
        this.memberIds = memberIds;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
