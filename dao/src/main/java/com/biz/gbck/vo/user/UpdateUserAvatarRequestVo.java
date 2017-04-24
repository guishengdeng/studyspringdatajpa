package com.biz.gbck.vo.user;

import com.biz.gbck.vo.IRequestVo;

/**
 * @author lei
 * @date 2017年02月10日
 * @reviewer
 * @see
 */
public class UpdateUserAvatarRequestVo implements IRequestVo {

    private static final long serialVersionUID = 4279939034913607123L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 头像url
     */
    private String avatar;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
