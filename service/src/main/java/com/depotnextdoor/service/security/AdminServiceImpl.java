package com.depotnextdoor.service.security;

import com.depotnextdoor.dao.rdb.po.security.*;
import com.depotnextdoor.dao.rdb.repository.admin.AdminRepository;
import com.depotnextdoor.dao.rdb.repository.admin.MainMenuRepository;
import com.depotnextdoor.service.AbstractBaseService;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends AbstractBaseService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MainMenuRepository mainMenuRepository;

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
        List<Menu> children = Lists.newArrayList();
        this.listAllMainMenu().forEach(mainMenu -> {
            mainMenu.getMenuItems().stream().filter(menuItems::contains).forEach(menuItem -> children.add(new Menu(menuItem.getName(), menuItem.getLink(), menuItem.getIcon())));
            if (CollectionUtils.isNotEmpty(children)) {
                result.add(new Menu(mainMenu.getName(), "#", mainMenu.getIcon(), children));
            }
        });
        return result;
    }

    public List<MainMenu> listAllMainMenu() {
        return mainMenuRepository.findByOrderByCodeAscNameAsc();
    }

}
