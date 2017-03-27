package com.spring.jpa.repository;

import com.spring.jpa.model.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ResourceRepository
 *
 * @author guisheng.deng
 * @date 2017年03月23日
 * @reviewer
 * @description
 * @see
 */
@Repository
public interface ResourceRepository extends CrudRepository<Resource,Long> {

}
