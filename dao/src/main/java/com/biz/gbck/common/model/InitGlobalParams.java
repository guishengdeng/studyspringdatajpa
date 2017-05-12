/**
 *
 */
package com.biz.gbck.common.model;

import com.biz.support.web.assist.GlobalParams;

import java.io.Serializable;

/**
 * 初始化Rest全局参数接口
 * <p/>
 * <p>
 * 用于标记ReqVo是否需要初始化全局参数, 在com.depotnearby.rest.utils.RestUtil.parseBizData(HttpServletRequest, Class<T>)处理
 * </p>
 *
 * @author defei
 * @date 2015-2-3
 */
public interface InitGlobalParams extends Serializable {

    void setGlobalParams(GlobalParams globalParams);

    GlobalParams getGlobalParams();

}
