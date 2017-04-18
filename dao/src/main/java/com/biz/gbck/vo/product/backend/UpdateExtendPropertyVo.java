package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;

/**
 * @author wangyumin
 * @date 2016年12月29日
 */

public class UpdateExtendPropertyVo implements IExtendPropertyVo {

    private static final long serialVersionUID = 4623284338749585075L;

    /**
     * ID
     */
    private Long id;

    /**
     * 属性值
     */
    private String value;

    /**
     * 状态 启用or不启用
     */
    private CommonStatusEnum status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

}
