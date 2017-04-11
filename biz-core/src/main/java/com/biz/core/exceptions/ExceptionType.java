package com.biz.core.exceptions;

/**
 * 业务异常类型顶层接口,推荐使用枚举来进行实现
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public interface ExceptionType {
    /**
     * 异常代码,为方便前端捕捉,推荐分段处理
     *
     * @author yanweijin
     * @date 2016年7月24日
     */
    int getCode();

    /**
     * 异常描述信息
     *
     * @author yanweijin
     * @date 2016年7月24日
     */
    String getDescription();
}
