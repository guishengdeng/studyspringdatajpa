package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
public class ApartTagListItemVo implements Serializable {

    private static final long serialVersionUID = 5410122227581047101L;

    /**
     * id
     */
    private String id;

    /**
     * 随标名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer idx;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
