package com.biz.gbck.dao.mysql.po;

import com.biz.core.model.Identifiable;

import java.util.Objects;

/**
 * 基础Po
 *
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
public abstract class BasePo<T> implements Identifiable<Long> {

    private static final long serialVersionUID = 4920583045812713490L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasePo)) return false;

        BasePo po = (BasePo) o;
        return getId() != null && po.getId() != null && Objects.equals(po.getId(), this.getId());
    }
}
