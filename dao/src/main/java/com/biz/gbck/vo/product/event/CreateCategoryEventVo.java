package com.biz.gbck.vo.product.event;

import com.biz.gbck.vo.product.backend.CreateCategoryVo;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分类创建事件 Vo
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public class CreateCategoryEventVo implements Serializable {

    private static final long serialVersionUID = 1173917402714640549L;

    /**
     * 分类创建 Vo
     */
    private CreateCategoryVo vo;

    public CreateCategoryEventVo(CreateCategoryVo vo) {
        this.vo = vo;
    }

    public CreateCategoryVo getVo() {
        return vo;
    }

    public void setVo(CreateCategoryVo vo) {
        this.vo = vo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
