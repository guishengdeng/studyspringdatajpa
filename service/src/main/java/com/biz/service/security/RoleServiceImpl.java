package com.biz.service.security;

import com.biz.gbck.dao.mysql.po.security.Role;
import com.biz.gbck.dao.mysql.repository.admin.RoleRepository;
import com.biz.service.AbstractBaseService;
import com.biz.service.security.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * RoleServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年04月20日
 * @reviewer
 * @description
 * @see
 */
@Service
public class RoleServiceImpl extends AbstractBaseService implements RoleService {
    @Resource
    private RoleRepository roleRepository;
    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public void addOrUpdate(Role role) {
         roleRepository.save(role);
    }
}