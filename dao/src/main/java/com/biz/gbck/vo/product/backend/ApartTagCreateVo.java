package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
public class ApartTagCreateVo implements IApartTagVo {

    private static final long serialVersionUID = -4829495196770159883L;

    /**
     * id
     */
    private Long id;

    /**
     * 随标名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer idx;

    /**
     * logo
     */
    private String logo;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 描述
     */
    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}