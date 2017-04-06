package com.spring.jpa.repository;

import com.spring.jpa.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleRepository
 *
 * @author guisheng.deng
 * @date 2017年03月20日
 * @reviewer
 * @description
 * @see
 */
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
   @Query("select distinct r  from Role r order by r.name asc ")
   List<Role> getRoleList();

   Role findByName(String name);

}
