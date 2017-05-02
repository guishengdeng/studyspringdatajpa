package com.biz.service.security.interfaces;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.dao.mysql.po.security.Role;
import java.util.List;

import com.biz.gbck.vo.admin.AdminReqVo;
import com.biz.gbck.vo.demo.CatSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
public interface AdminService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<Admin> listEnableAdmins();

    List<Admin> listDisableAdmins();

    List<Role> listAllRoles();

    Admin getAdmin(String username);

    void createAdmin(Admin admin, String createBy);

    void deleteAdmin(String username);

    Page<Admin> queryAdminsByCondition(AdminReqVo vo);
}
