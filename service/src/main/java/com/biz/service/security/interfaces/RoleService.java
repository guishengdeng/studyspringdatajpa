package com.biz.service.security.interfaces;

import com.biz.gbck.dao.mysql.po.security.Role;
import com.biz.gbck.enums.CommonStatusEnum;

import java.util.List;

/**
 * RoleService
 *
 * @author guisheng.deng
 * @date 2017年04月20日
 * @reviewer
 * @description
 * @see
 */
public interface RoleService {
    List<Role> listRoles();

    Role getRole(Long id);

    void delete(Long id);

    void addOrUpdate(Role role);

    List<Role>  findByStatus(CommonStatusEnum status);
}
