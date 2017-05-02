package com.biz.support.jpa.po;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * po基类,id不会自动生成
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年8月2日
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity extends BasePO<Long> implements Serializable {

    @Id
    private Long id;

    private Timestamp createTimestamp = new Timestamp(System.currentTimeMillis());

    @UpdateTimestamp
    private Timestamp updateTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "#" + this.getId();
    }

}
