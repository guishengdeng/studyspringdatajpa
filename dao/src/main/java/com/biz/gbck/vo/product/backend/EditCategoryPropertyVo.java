package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * @author wangyumin
 * @date 2016年12月18日
 */

public class EditCategoryPropertyVo implements Serializable {

    private static final long serialVersionUID = -1599988661288315943L;

    /**
     * ID
     */
    private Long id;

    /**
     * 属性名
     */
    private String name;

    /**
     * 是否启用
     */
    private CommonStatusEnum status;

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

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

}
