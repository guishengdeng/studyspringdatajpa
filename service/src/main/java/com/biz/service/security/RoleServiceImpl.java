package com.biz.service.security;

import com.biz.gbck.dao.mysql.po.security.Role;
import com.biz.gbck.dao.mysql.repository.admin.RoleRepository;
import com.biz.gbck.enums.CommonStatusEnum;
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
        roleRepository.updateStatus(id,CommonStatusEnum.DISABLE);
    }

    @Override
    public void addOrUpdate(Role role) {
         roleRepository.save(role);
    }

    @Override
    public List<Role> findByStatus(CommonStatusEnum status) {
        return roleRepository.getByStatus(status);
    }

    /**
     *
     *用于判定：在修改或添加角色名称时,数据库是否是该角色名称
     */
    @Override
    public Boolean isExist(Role param) {
        Role role = roleRepository.getRoleCondition(param.getName());
        List<Role> list = roleRepository.findAll();
        if(role != null){
             if(param.getId() != null){
                 for(Role item : list){
                     if(param.getId().equals(item.getId()) && param.getName().trim().equals(item.getName().trim())){
                         return Boolean.TRUE;
                     }
                 }
             }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}