package com.biz.gbck.dao.redis.ro.product.meta;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 销售标签 Ro
 *
 * @author david-liu
 * @date 2016年12月29日
 * @reviewer
 * @see
 */
@Ro(key = "product:SaleTagRo")
public class SaleTagRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = 5869233720670875892L;

    /**
     * 名称
     */
    private String name;

    /**
     * Logo
     */
    private String logo;

    /**
     * 序号
     */
    private Integer idx;

    /**
     * 富文本
     */
    private String rawHtml;

    /**
     * 描述
     */
    private String description;

    /**
     * 商户 ID
     */
    @FieldSortedSet(key = "vendorId", score = "idx")
    private Long vendorId;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
