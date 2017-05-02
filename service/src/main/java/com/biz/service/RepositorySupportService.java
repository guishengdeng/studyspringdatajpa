package com.biz.service;

import com.biz.support.jpa.po.BasePO;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * 支持基本的JPA CRUD的service
 *
 * @author defei
 */
public interface RepositorySupportService<T extends BasePO> extends InitializingBean {

	T save(final T t);

	T get(final Long id);

	List<T> findByIds(final Iterable<Long> ids);

	List<T> findAll();

	Long count();

	Boolean exist(final Long id);

	void delete(final Long id);

	void remove(final Long id);
}
