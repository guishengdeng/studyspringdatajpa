package com.spring.jpa.service;

import com.spring.jpa.model.User;
import com.spring.jpa.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserService
 *
 * @author guisheng.deng
 * @date 2017年03月08日
 * @reviewer
 * @see
 */
@Service("userService")
public class UserService {
    @Resource
    private UserRepository userRepository;

    public void showUserList(String username,String password){
        List<User> list=userRepository.findByUsernameAndPassword(username,password);
        if(!list.isEmpty()){
            for(User user:list){
                System.out.println(user);
            }
        }
    }
    /*findAll方法 是SpringDataJpa中PagingAndSortingRepository接口自带的一个方法。只要符合其规范，就可以调用*/
    public Page<User> getAllUserByPage(PageRequest pageRequest){

        return userRepository.findAll(pageRequest);
    }

}