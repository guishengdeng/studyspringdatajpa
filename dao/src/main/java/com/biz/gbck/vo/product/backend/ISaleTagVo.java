package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/23
 */
public interface ISaleTagVo extends Serializable {

    Long getId();

    String getName();

    String getLogo();

    Integer getIdx();

    String getRawHtml();

    String getDescription();

    CommonStatusEnum getStatus();

    String getVendorId();
}
