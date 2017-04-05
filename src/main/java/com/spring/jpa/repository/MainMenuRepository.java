package com.spring.jpa.repository;

import com.spring.jpa.model.MainMenu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MainMenuRepository
 *
 * @author guisheng.deng
 * @date 2017年03月31日
 * @reviewer
 * @description
 * @see
 */
@Repository
public interface MainMenuRepository extends CrudRepository<MainMenu,Long> {

}
