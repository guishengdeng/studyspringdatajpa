package com.biz.service.system;

import com.biz.gbck.dao.mysql.po.security.*;
import com.biz.gbck.dao.mysql.repository.admin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by defei on 1/12/17.
 */
@Service
public class InitManager {

    private static final Logger logger = LoggerFactory.getLogger(InitManager.class);

    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @Autowired
    private MainMenuRepository mainMenuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdminRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @PostConstruct
    public void init(){
        if(userRepository.count() == 0){
            logger.info("开始初始化系统基础数据...");

            //Admin
            Admin user = new Admin();
            user.setUsername("admin");
            user.setName("超级管理员");
            user.setPassword(md5PasswordEncoder.encodePassword("admin", user.getUsername()));
            user = userRepository.save(user);

            //Main menu
            MainMenu mainMenu = new MainMenu();
            mainMenu.setName("系统信息");
            mainMenu.setCode(999);
            mainMenu = mainMenuRepository.save(mainMenu);

            //Menu Item
            MenuItem menuItemOfAdmin = buildMenuItem("用户管理", "Admin Management", "ROLE_USER;OPT_USER_LIST", 1, "/manage/users.do", mainMenu);
            menuItemOfAdmin = menuItemRepository.save(menuItemOfAdmin);
            MenuItem menuItemOfMainMenu = buildMenuItem("菜单管理", "Main Menu", "ROLE_MAINMENU;OPT_MAINMENU_LIST;ROLE_MENUITEM;ROLE_RESOURCE", 2, "/manage/mainMenus.do", mainMenu);
            menuItemOfMainMenu = menuItemRepository.save(menuItemOfMainMenu);
            MenuItem menuItemOfRole = buildMenuItem("角色管理", "Role Management", "ROLE_ROLE;OPT_ROLE_LIST", 3, "/manage/roles.do", mainMenu);
            menuItemOfRole = menuItemRepository.save(menuItemOfRole);

            //Resource
            Resource manageAdmin = builtResource("用户管理", "Admin Management", "OPT_USER_ADD;OPT_USER_EDIT;OPT_USER_DELETE;OPT_USER_RESET;OPT_USER_DETAIL",menuItemOfAdmin);
            manageAdmin = resourceRepository.save(manageAdmin);
            Resource checkAdminDetail = builtResource("查看用户", "Admin Read", "OPT_USER_DETAIL",menuItemOfAdmin);
            checkAdminDetail = resourceRepository.save(checkAdminDetail);

            Resource manageMenuItem = builtResource("菜单管理", "Menu Management", "OPT_MAINMENU_ADD;OPT_MAINMENU_EDIT;OPT_MAINMENU_DELETE;OPT_MAINMENU_DETAIL;OPT_MENUITEM_ADD;OPT_MENUITEM_EDIT;OPT_MENUITEM_DELETE;OPT_MENUITEM_DETAIL;OPT_RESOURCE_ADD;OPT_RESOURCE_EDIT;OPT_RESOURCE_DELETE",menuItemOfMainMenu);
            manageMenuItem = resourceRepository.save(manageMenuItem);
            Resource checkMainMenu = builtResource("菜单查看", "Main Menu Management", "OPT_MAINMENU_DETAIL;OPT_MENUITEM_DETAIL",menuItemOfMainMenu);
            checkMainMenu = resourceRepository.save(checkMainMenu);

            Resource manageRole = builtResource("角色管理", "Role Management", "OPT_ROLE_ADD;OPT_ROLE_EDIT;OPT_ROLE_DELETE;OPT_ROLE_DETAIL",menuItemOfRole);
            manageRole = resourceRepository.save(manageRole);
            Resource checkRoleDetail = builtResource("角色查看", "Role Read", "OPT_ROLE_DETAIL",menuItemOfRole);
            checkRoleDetail = resourceRepository.save(checkRoleDetail);

            //Role
            Role role = new Role();
            role.setName("超级管理员");
            role.setDescription("拥有菜单管理，角色管理，用户管理权限");
            role.setMenuItems(newArrayList(menuItemOfAdmin, menuItemOfMainMenu, menuItemOfRole));
            role.setResources(newArrayList(manageAdmin, checkMainMenu, manageMenuItem, checkAdminDetail, manageRole, checkRoleDetail));
            role = roleRepository.save(role);

            user.setRoles(newArrayList(role));
            userRepository.save(user);
            logger.info("完成初始化系统基础数据...");
        }
    }

    private MenuItem buildMenuItem(String name, String nameEn, String symbol, Integer code, String link, MainMenu mainMenu) {
        MenuItem menuItemOfRole = new MenuItem();
        menuItemOfRole.setName(name);
        menuItemOfRole.setSymbol(symbol);
        menuItemOfRole.setCode(code);
        menuItemOfRole.setLink(link);
        menuItemOfRole.setMainMenu(mainMenu);
        return menuItemOfRole;
    }

    private Resource builtResource(String name, String nameEn, String symbol, MenuItem menuItemOfAdmin) {
        Resource resource = new Resource();
        resource.setName(name);
        resource.setSymbol(symbol);
        resource.setMenuItem(menuItemOfAdmin);
        return resource;
    }
}
