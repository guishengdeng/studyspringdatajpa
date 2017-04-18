package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * 商品角标信息
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
public class ProductApartTagVo implements Serializable {

    private static final long serialVersionUID = -1827485611083221151L;

    /**
     * 标签名称
     */
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
