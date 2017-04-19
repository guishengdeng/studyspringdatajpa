package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public interface IBrandVo extends Serializable {
    String getName();

    String getNameEn();

    String getBrandCode();

    String getLogo();

    String getDescription();

    CommonStatusEnum getStatus();

    String getSeoTitle();

    String getSeoKeywords();

    String getSeoDescription();
}
