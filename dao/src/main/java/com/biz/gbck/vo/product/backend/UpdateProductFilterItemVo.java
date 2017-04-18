package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 搜索条件值修改VO
 *
 * @author wangyumin
 * @date 2017年1月5日
 */
public class UpdateProductFilterItemVo implements Serializable {

    private static final long serialVersionUID = 7878840096848148235L;

    /**
     * ID
     */
    private Long id;

    /**
     * 商品首字母
     */
    private String prefix;

    /**
     * 值显示名称
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 显示图片
     */
    private String image;

    /**
     * 是否特殊显示
     */
    private Boolean highlightShow = false;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getHighlightShow() {
        return highlightShow;
    }

    public void setHighlightShow(Boolean highlightShow) {
        this.highlightShow = highlightShow;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

}
