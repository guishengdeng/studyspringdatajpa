package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 更改中台商品状态的Vo
 *
 * @author zhangcheng
 * @date 2017/3/25
 * @reviewer
 * @see
 */
public class UpdateMnsProductStatusReqVo implements Serializable {

    private static final long serialVersionUID = 5898412432702280030L;

    /**
     * 中台商品在本地保存的id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
