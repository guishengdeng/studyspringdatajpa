package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
public interface IApartTagVo extends Serializable {

    String getName();

    String getLogo();

    Integer getIdx();

    String getDescription();

    CommonStatusEnum getStatus();
}
