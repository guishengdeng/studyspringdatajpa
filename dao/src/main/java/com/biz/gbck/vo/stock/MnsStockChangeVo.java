package com.biz.gbck.vo.stock;

import java.io.Serializable;
import java.sql.Timestamp;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author zhangcheng
 * @date 2016/7/19
 * @reviewer
 * @see
 */
public class MnsStockChangeVo implements Serializable {
    private static final long serialVersionUID = 1423684562088073472L;

    /**
     * 库存变动的单据号
     */
    private String bill;

    /**
     * 库存变动数量
     */
    private int changeStock;

    /**
     * 库存变动时间
     */
    private Timestamp changeTime;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 全局库存变动id， 中台分配
     */
    private Long id;

    /**
     * 门店号
     */
    private String mcuCode;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单来源
     */
    private String orderSource;

    /**
     * 商品编号
     */
    private String productCode;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 单位
     */
    private String unit;

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public int getChangeStock() {
        return changeStock;
    }

    public void setChangeStock(int changeStock) {
        this.changeStock = changeStock;
    }

    public Timestamp getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Timestamp changeTime) {
        this.changeTime = changeTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMcuCode() {
        return mcuCode;
    }

    public void setMcuCode(String mcuCode) {
        this.mcuCode = mcuCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
