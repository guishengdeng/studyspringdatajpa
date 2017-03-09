package com.spring.jpa.repository;

import com.spring.jpa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends PagingAndSortingRepository<User,Long> {

    List<User> findByUsernameAndPassword(String username, String password);

    /*@Query("select o from User o where o.username =:username")
    User getUserList(String username);*/


}
