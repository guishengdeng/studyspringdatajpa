package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 商品分类 Vo
 *
 * @author david-liu
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public interface ICategoryVo extends Serializable {
    String getName();

    String getLogo();

    CommonStatusEnum getStatus();

    String getSeoTitle();

    String getSeoKeywords();

    String getSeoDescription();
}
