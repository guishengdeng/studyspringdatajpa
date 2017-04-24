package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 后台管理中台推送商品的返回Vo
 *
 * @author zhangcheng
 * @date 2017/3/15
 * @reviewer
 * @see
 */
public class MnsProductResponseVo implements Serializable {

    private static final long serialVersionUID = 244392509072077705L;

    /**
     * 中台商品本地id
     */
    private String id;

    /**
     * 中台商品编码
     */
    private String productCode;

    /**
     * 中台商品名称
     */
    private String name;

    /**
     * 中台商品的基本单位
     */
    private String baseUnit;

    /**
     * 品牌类别
     */
    private String brand;

    /**
     * 最后更新时间
     */
    private String lastModifyTime;

    /**
     * 中台商品该状态
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
