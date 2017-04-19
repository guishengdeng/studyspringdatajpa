package com.biz.gbck.vo.product.backend;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/**
 * 配置属性Vo
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public class CascadeExtendVo implements Serializable {
    private static final long serialVersionUID = 2563962404886691396L;

    /**
     * 扩展属性名
     */
    private String extendName;

    /**
     * 扩展属性值列表
     */
    private List<String> extendProperties = Lists.newArrayList();

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public List<String> getExtendProperties() {
        return extendProperties;
    }

    public void setExtendProperties(List<String> extendProperties) {
        this.extendProperties = extendProperties;
    }
}
