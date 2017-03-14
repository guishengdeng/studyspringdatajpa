package com.spring.jpa.service;

import com.spring.jpa.model.User;
import com.spring.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author guisheng.deng
 * @date 2017年03月08日
 * @reviewer
 * @see
 */

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //备注：持久层UserRepositroy一条sql语句都没有写，全是调用父类接口（CrudRepository）里提供的方法满足需求
    public Iterable<User> showUserList(){

        //return userRepository.getUserList();
        return userRepository.findAll();//返回值是迭代器，这里暂不使用自带的查询方法
    }
    public List<User> userList(String username){
        return userRepository.findByUsername(username);
    }
    public User getUserById(Long id){
       // return userRepository.findById(id);
        return userRepository.findOne(id);
    }
    public void updateOrAddSubmit(User user){
          userRepository.save(user);//备注save方法来自于父类接口CrudRepository
    }
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

}