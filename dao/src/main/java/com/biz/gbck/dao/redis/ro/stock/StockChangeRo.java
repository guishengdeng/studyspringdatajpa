package com.biz.gbck.dao.redis.ro.stock;

import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.sql.Timestamp;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品变量库存信息
 *
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月23日
 * @reviewer
 */
@Ro(key = "stock:change")
public class StockChangeRo extends BaseRedisObject<Long> {

    /**
     * 全局库存变动id， 中台分配
     */
    private Long id;

    /**
     * 库存变动的单据号
     */
    private String bill;

    /**
     * 库存变动数量
     */
    private int changeStock;

    /**
     * 创建时间
     */
    private Timestamp createTime;

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
