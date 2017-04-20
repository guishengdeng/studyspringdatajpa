package com.biz.gbck.dao.mysql.po.vendor.bbc;

import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
@Entity
@Table(name = "ven_vendor_role")
public class VendorRole extends BaseEntity {

    private static final long serialVersionUID = 2352942195867446745L;

    private String name;


}
