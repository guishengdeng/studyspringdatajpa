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

    public Iterable<Role> getRoleList(){
        return roleRepository.getRoleList();
    }
}