package com.spring.jpa.service;

import com.spring.jpa.model.PageInfo;
import com.spring.jpa.model.User;
import com.spring.jpa.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
@Secured("ROLE_ADMIN")//同样的，RoleService类，也需要这样定义@(secured)
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //备注：持久层UserRepositroy一条sql语句都没有写，全是调用父类接口（CrudRepository）里提供的方法满足需求

    public Iterable<User> showUserList(){

        return userRepository.findAll();//返回值是迭代器，这里暂不使用自带的查询方法
    }
   /* public List<User> showUserList(){

       return userRepository.getUsers();
   }*/
    public List<User> userList(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long id){
       // return userRepository.findById(id);
        return userRepository.findOne(id);
    }

    /**
     * 表示此方法只能被拥有ROLE_ADMIN权限或者ROLE_USER的用户调用
     * @param user
     */

    public void updateOrAddSubmit(User user){
          userRepository.save(user);//备注save方法来自于父类接口CrudRepository,上述角色为什么需要定义成ROLE_,见RoleVoter类
    }

    /**
     * 表示此方法只能被拥有 ROLE_ADMIN权限OPT_USER_DELETE的用户调用
     * @param id
     */
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    public void deleteUser(Long id){
         userRepository.delete(id);
    }


    public List<User> search(final User user, PageInfo pageInfo){//返回Page
        //userRepository调用的findAll方法，来自于父接口JpaSpecificationExecutor
        //而该接口默认是有一个实现类的SimpleJpaRepository
        return userRepository.findAll(new Specification<User>() {
            //注意看SimpleJpaRepository中的方法以及applySpecificationToCriteria
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate userNameLike=null;
                if(null!=user&&!StringUtils.isEmpty(user.getUsername())){
                   userNameLike=cb.like(root.<String>get("username"),"%"+user.getUsername()+"%");
                }
                if(null!=userNameLike){
                    query.where(userNameLike);
                }
                return userNameLike;
            }
        });
    }


}