package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.tag.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author lzz
 * @usage
 * @reviewer
 * @since 2017/5/3
 */
public interface ISaleTagVo extends Serializable {

    Long getId();

    String getName();

    String getShowName();

    String getTag();

    Integer getIdx();

    String getDescription();

    SaleStatusEnum getSaleStatus();

    CommonStatusEnum getStatus();

    String getVendorId();
}
