package com.biz.support.jpa.po;

import com.biz.core.model.Identifiable;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.UpdateTimestamp;

public class BaseEntityPKIncrement implements Serializable, Identifiable<Long> {

    private static final long serialVersionUID = -4594777811110196557L;

    @Id
    @GeneratedValue
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
        return "BaseEntityPKIncrement{" +
                "id=" + id +
                ", createTimestamp=" + createTimestamp +
                ", updateTimestamp=" + updateTimestamp +
                '}';
    }
}
