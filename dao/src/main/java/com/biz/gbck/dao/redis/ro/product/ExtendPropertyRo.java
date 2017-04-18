package com.biz.gbck.dao.redis.ro.product;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 商品扩展属性值 Ro
 *
 * @author david-liu
 * @date 2016年12月30日
 * @reviewer
 * @see
 */
@Ro(key = "product:ExtendPropertyRo")
public class ExtendPropertyRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = -5388564313320067766L;

    /**
     * 商品扩展属性 ID
     */
    @FieldSortedSet(key = "productExtendId", score = "idx")
    private Long productExtendId;

    /**
     * 商品扩展属性值
     */
    private String value;

    /**
     * 显示顺序
     */
    private Integer idx;

    /**
     * 启用状态
     */
    private CommonStatusEnum status;

    public Long getProductExtendId() {
        return productExtendId;
    }

    public void setProductExtendId(Long productExtendId) {
        this.productExtendId = productExtendId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
