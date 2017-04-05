package com.spring.jpa.repository;

import com.spring.jpa.model.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MenuItemRepository
 *
 * @author guisheng.deng
 * @date 2017年03月31日
 * @reviewer
 * @description
 * @see
 */
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem,Long> {


}
