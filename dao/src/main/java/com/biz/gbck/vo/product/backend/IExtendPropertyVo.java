package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author wangyumin
 * @date 2016年12月29日
 */

public interface IExtendPropertyVo extends Serializable {

    String getValue();

    CommonStatusEnum getStatus();

}
