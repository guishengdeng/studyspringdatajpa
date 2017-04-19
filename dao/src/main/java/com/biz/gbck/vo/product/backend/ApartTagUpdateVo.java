package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
public class ApartTagUpdateVo implements Serializable, IApartTagVo {

    private static final long serialVersionUID = -2484096912557917754L;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    @Override
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

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
