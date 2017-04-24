package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/9
 */
public interface ICreateOrUpdateCascadeVo {

    Long getId();

    Long getCategoryId();

    Long getVendorId();

    String getName();

    List<Long> getExtendPropertyIds();

    List<Long> getHighlightValueIds();

    CommonStatusEnum getStatus();

    List<Long> getProductIds();

    List<Long> getHighlightProductIds();

}
