package com.biz.service;

import com.biz.core.exceptions.MethodNotSupportException;
import com.biz.support.jpa.po.BasePO;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.codelogger.utils.JudgeUtils.isNull;

/**
 * 支持基本的JPA CRUD的service
 *
 * @author defei
 */
public abstract class AbstractRepositorySupportService<T extends BasePO> extends AbstractBaseService implements RepositorySupportService<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected abstract CommonJpaRepository<T, Long> getRepository();

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public T save(final T t) {

		if (isNull(t.getId())) {
			t.setId(idService.nextId());
		}
		return getRepository().save(t);
	}

	public T get(final Long id) {

		return id == null ? null : getRepository().findOne(id);
	}

	public List<T> getByIds(final Iterable<Long> ids) {

		return getRepository().findAll(ids);
	}

	public List<T> findAll() {

		return getRepository().findAll();
	}

	public Long count() {

		return getRepository().count();
	}

	public Boolean exist(final Long id) {

		return getRepository().exists(id);
	}

	@Override
	@Deprecated
	public void delete(Long id) {

		getRepository().delete(id);
	}

	public void remove(final Long id) throws MethodNotSupportException {

		throw new MethodNotSupportException();
	}
}
