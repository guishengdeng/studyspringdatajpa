/**
 *
 */
package com.depotnextdoor.support.web.assist;

import java.io.Serializable;

/**
 * 全局参数接口
 */
public interface GlobalParamsAware extends Serializable {

	void setGlobalParams(GlobalParams globalParams);

	GlobalParams getGlobalParams();

}
