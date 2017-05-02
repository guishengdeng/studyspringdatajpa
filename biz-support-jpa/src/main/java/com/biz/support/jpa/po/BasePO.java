package com.biz.support.jpa.po;

import com.biz.core.model.Identifiable;

import java.util.Objects;

/**
 * 基础Po
 *
 * @author defei
 * @date 2017年04月12日
 */
public abstract class BasePO<T> implements Identifiable<T> {

	private static final long serialVersionUID = 4920583045812713490L;

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof BasePO))
			return false;

		BasePO po = (BasePO) o;
		return getId() != null && po.getId() != null && Objects.equals(po.getId(), this.getId());
	}

	@Override
	public int hashCode() {

		return getId() != null ? getId().hashCode() : 0;
	}
}
