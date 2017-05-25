package com.biz.service.security;

import com.biz.gbck.dao.mysql.po.security.*;
import com.biz.gbck.dao.mysql.repository.admin.AdminRepository;
import com.biz.gbck.dao.mysql.repository.admin.MainMenuRepository;
import com.biz.gbck.dao.mysql.repository.admin.RoleRepository;
import com.biz.gbck.dao.mysql.specification.admin.AdminDynamicSpecification;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.exceptions.product.AdminNotFoundException;
import com.biz.gbck.vo.admin.AdminReqVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.security.interfaces.AdminService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AdminServiceImpl extends AbstractBaseService implements UserDetailsService, AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MainMenuRepository mainMenuRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findOne(username);
        if (admin != null) {
            admin.setMenus(this.initAdminMenu(admin));
            Hibernate.initialize(admin.getRoles());
            logger.info("load user from db;");
        } else {
            throw new UsernameNotFoundException(String.format("username: %s is not exists", username));
        }
        return admin;
    }

    public List<Menu> initAdminMenu(Admin admin) {
        List<Menu> result = Lists.newArrayList();
        List<Role> roles = admin.getRoles();
        List<MenuItem> menuItems = Lists.newArrayList();
        roles.forEach(role -> menuItems.addAll(role.getMenuItems()));
        this.listAllMainMenu().forEach(mainMenu -> {
            List<Menu> children = Lists.newArrayList();
            mainMenu.getMenuItems().stream().filter(menuItems::contains).forEach(menuItem -> {
                children.add(new Menu(menuItem.getName(), menuItem.getLink(), menuItem.getIcon()));
            });
            if (CollectionUtils.isNotEmpty(children)) {
                result.add(new Menu(mainMenu.getName(), "#", mainMenu.getIcon(), children));
            }
        });
        return result;
    }

    public List<MainMenu> listAllMainMenu() {
        return mainMenuRepository.findByOrderByCodeAscNameAsc();
    }

    @Override
    public List<Admin> listEnableAdmins() {
        return adminRepository.findAdminsByStatus(CommonStatusEnum.ENABLE);
    }

    @Override
    public List<Admin> listDisableAdmins() {
        return adminRepository.findAdminsByStatus(CommonStatusEnum.DISABLE);
    }

    @Override
    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Admin getAdmin(String username) {
        return adminRepository.findOne(username);
    }

    @Override
    public void createAdmin(Admin admin, String createBy) {
        admin.setCreateDate(new Date(System.currentTimeMillis()));
        admin.setCreateBy(createBy);
        adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(String username) {
         adminRepository.updateStatus(CommonStatusEnum.DISABLE,username);
    }

    /**
     * 用户管理页面进行分页查询
     * findAll方法继承自JpaSpecificationExecutor接口
     * @param
     * @return
     */
    @Override
    public Page<Admin> queryAdminsByCondition(AdminReqVo vo) {
        return adminRepository.findAll(new AdminDynamicSpecification(vo),
                new PageRequest(vo.getPage()-1,vo.getPageSize()));
    }

    /**
     *用户名一旦注册则不允许修改。
     */
    @Override
    public Boolean isExistAdmin(String username,String cmd) throws AdminNotFoundException {
        Admin user = adminRepository.findOne(username.trim());
         if(user != null){
              if(cmd.equals("edit")){
                  return Boolean.TRUE;
              }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
