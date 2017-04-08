package com.depotnextdoor.support.jpa.po;


import com.depotnextdoor.core.model.Identifiable;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.UpdateTimestamp;

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
public class BaseEntity implements Serializable, Identifiable<Long> {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "#" + this.getId();
    }

}
