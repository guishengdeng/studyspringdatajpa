package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 出库单
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_delivery")
public class DeliveryOrder extends BaseEntity {

    private static final long serialVersionUID = -7749433829358520328L;

    /**
     * 出库单号
     */
    @Column(length = 50)
    private String sn;

    //TODO 增加属性




}
