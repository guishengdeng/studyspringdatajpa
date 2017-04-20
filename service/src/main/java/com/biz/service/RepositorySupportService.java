package com.biz.service;

import com.biz.gbck.dao.mysql.po.BasePo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

import static org.codelogger.utils.JudgeUtils.isNull;

/**
 * 支持基本的JPA CRUD的service
 *
 * @author defei
 */
public interface RepositorySupportService<T extends BasePo> extends InitializingBean {

	T save(final T t);

	T get(final Long id);

	List<T> findByIds(final Iterable<Long> ids);

	List<T> findAll();

	Long count();

	Boolean exist(final Long id);

	void delete(final Long id);

	void remove(final Long id);
}
