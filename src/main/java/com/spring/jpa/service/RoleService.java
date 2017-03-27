package com.spring.jpa.service;

import com.spring.jpa.model.Role;
import com.spring.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleService
 *
 * @author guisheng.deng
 * @date 2017年03月20日
 * @reviewer
 * @description
 * @see
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getList(){
        return roleRepository.getRoleList();
    }
    public Role getRoleByName(String name){
       return roleRepository.findByName(name);
    }
    /**
     * 获取role表里的所有记录
     */
    public Iterable<Role> getRoleList(){

        return roleRepository.findAll();
    }
    public  Role  getRoleById(Long id){
          return roleRepository.findOne(id);
    }

    public void deleteRole(Long role_id){
         roleRepository.delete(role_id);
    }
    public void addOrUpdateSubimt(Role role){
         roleRepository.save(role);
    }
}