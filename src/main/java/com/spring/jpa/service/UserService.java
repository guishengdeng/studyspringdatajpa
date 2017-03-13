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

    public List<User> showUserList(){

        return userRepository.getUserList();
    }

    public User getUserById(Long id){
        return userRepository.findById(id);
    }
    public void updateOrAddSubmit(User user){
          userRepository.save(user);//备注save方法来自于父类接口CrudRepository
    }
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

}