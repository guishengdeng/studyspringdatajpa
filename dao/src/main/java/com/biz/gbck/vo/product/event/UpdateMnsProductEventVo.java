package com.biz.gbck.vo.product.event;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 中台更新商品事件Vo
 *
 * @author zhangcheng
 * @date 2017/1/11
 * @reviewer
 * @see
 */
public class UpdateMnsProductEventVo implements Serializable {

    private static final long serialVersionUID = 2401222509337288381L;

    /**
     * 本地ID
     */
    private Long id;

    /**
     * 最后更新时间
     */
    private Timestamp lastUpdateTime;

    /**
     * 商品中台编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品主条码
     */
    private String productMainBarcode;

    public String getProductMainBarcode() {
        return productMainBarcode;
    }

    public void setProductMainBarcode(String productMainBarcode) {
        this.productMainBarcode = productMainBarcode;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
