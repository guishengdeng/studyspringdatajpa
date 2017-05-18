package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.gbck.enums.order.InventoryStatus;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 盘点
 *
 * @author lei
 * @date 2017年05月18日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pur_inventory")
public class Inventory extends BaseEntity {

    private static final long serialVersionUID = -1825091536400908176L;

    /**
     * 盘点单号
     */
    @Column(length = 50)
    private String sn;

    /**
     * 盘点状态
     */
    @Convert(converter = InventoryStatus.Converter.class)
    private InventoryStatus status = InventoryStatus.CREATED;

    //盘点明细
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> items;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }
}
