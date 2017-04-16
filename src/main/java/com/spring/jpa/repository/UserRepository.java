package com.spring.jpa.repository;

import com.spring.jpa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<User,Long>,JpaSpecificationExecutor<User> {

    /*List<User> findByUsernameAndPassword(String username, String password);

    User  findUserByUsername(String username);

    Long countByUsername(String username);

    Long  deleteByUsername(String username);


    List<User> queryFirst2ByUsername(String username);

    User queryTopByOrderByAge();

    Page<User> queryTop3ByUsername(String username,Pageable pageable);

    List<User> findFirst3ByUsername(String username,Sort sort);

    List<User> queryTop4ByUsername(String username,Pageable pageable);
    @Query(value = "select u from User u where u.username = :username order by u.username desc,u.email asc")
    List<User> findTopThree(@Param("username") String username);

    @Modifying
    @Query("update User u set u.username=:username where u.id=:id")
    int setFixedUsernameFor(@Param("username") String username,@Param("id") Long id);*/

   /* @Query("select u from User u")
    List<User>  getUserList();

    User findById(Long id);*/

    List<User> findByUsername(String username);
    //分页查询
    //该方法直接从PagingAndSortingRepository接口复制而来的
    Page<User> findAll(Pageable pageable);//分页查询需要页码，以及每页需要显示几条记录

    //还可以写个带查询条件的分页
    //Page<User> findByUsername(String username,Pageable pageable);


}
