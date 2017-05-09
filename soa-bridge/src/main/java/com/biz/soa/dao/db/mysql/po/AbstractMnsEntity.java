package com.biz.soa.dao.db.mysql.po;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author lei
 * @date 2016/12/09
 * @reviewer
 * @see
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractMnsEntity extends BaseEntity {

    /**
     * 原始数据
     */
    @Column(columnDefinition = "MEDIUMTEXT")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
