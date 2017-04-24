package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.IApartTagVo;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 商品列表随标(展示在商品列表项之下的商品标签)
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_product_apart_tag")
public class ApartTag extends BaseEntity {

    private static final long serialVersionUID = 3844899741783875988L;

    /**
     * 随标名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * logo
     */
    @Column(nullable = false)
    private String logo;

    /**
     * 顺序
     */
    @Column
    private Integer idx;

    /**
     * 描述
     */
    @Column
    private String description;

    /**
     * 删除标识(true: 已删除, false: 未删除)
     */
    @Column
    private Boolean deleteFlag = Boolean.FALSE;

    /**
     * 状态
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void form(IApartTagVo iApartTagVo) {
        this.setName(iApartTagVo.getName());
        this.setIdx(iApartTagVo.getIdx());
        this.setStatus(iApartTagVo.getStatus());
        this.setDescription(iApartTagVo.getDescription());
        this.setLogo(iApartTagVo.getLogo());
    }
}
