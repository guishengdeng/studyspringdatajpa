package com.biz.gbck.vo.user;

import com.biz.gbck.vo.IRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 类说明：抽取公共参数
 *
 * @author xiaoyasong
 * @version 创建时间：2017年1月12日 上午9:51:29
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public class BaseRequestVo implements IRequestVo {

    private static final long serialVersionUID = 6444955805404831941L;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
