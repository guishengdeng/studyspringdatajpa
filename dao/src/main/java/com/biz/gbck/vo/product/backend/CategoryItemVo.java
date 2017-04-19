package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/22
 */
public class CategoryItemVo implements Serializable {

    private static final long serialVersionUID = -4369752949218896017L;

    private String id;

    private String name;

    private CommonStatusEnum status;

    public CategoryItemVo() {
    }

    public CategoryItemVo(String id, String name, CommonStatusEnum status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

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

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
