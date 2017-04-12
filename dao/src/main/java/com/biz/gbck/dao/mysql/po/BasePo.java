package com.biz.gbck.dao.mysql.po;

import java.util.Objects;

/**
 * 基础Po
 *
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
public class BasePo<T> {

    private T id;

    public BasePo(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasePo)) return false;

        BasePo po = (BasePo) o;
        return Objects.equals(po.getId(), this.getId());
    }
}
