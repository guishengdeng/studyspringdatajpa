package com.biz.gbck.dao.redis.ro.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 商品扩展属性 Ro
 *
 * @author david-liu
 * @date 2016年12月30日
 * @reviewer
 * @see
 */
@Ro(key = "product:ProductExtendRo")
public class ProductExtendRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = 1412915459372757310L;

    /**
     * 扩展属性名
     */
    private String name;

    /**
     * 分类 ID
     */
    @FieldSortedSet(key = "categoryId", score = "idx")
    private Long categoryId;

    /**
     * 顺序
     */
    private Integer idx;

    /**
     * 启用状态
     */
    private CommonStatusEnum status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
