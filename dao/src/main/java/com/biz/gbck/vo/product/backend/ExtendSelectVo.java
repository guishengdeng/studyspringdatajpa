package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * 商品扩展属性
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/17
 */
public class ExtendSelectVo implements Serializable {

    private String extendName;

    private String extendId;

    private List<ExtendPropertyVo> extendPropertyVos;



    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public String getExtendId() {
        return extendId;
    }

    public void setExtendId(String extendId) {
        this.extendId = extendId;
    }

    public List<ExtendPropertyVo> getExtendPropertyVos() {
        return extendPropertyVos;
    }

    public void setExtendPropertyVos(List<ExtendPropertyVo> extendPropertyVos) {
        this.extendPropertyVos = extendPropertyVos;
    }
}
