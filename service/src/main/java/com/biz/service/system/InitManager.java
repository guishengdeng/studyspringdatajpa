package com.biz.service.system;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.dao.mysql.po.security.*;
import com.biz.gbck.dao.mysql.repository.admin.*;
import com.biz.gbck.dao.mysql.repository.demo.CatRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import org.codelogger.utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.util.List;

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

	@Autowired
	private CatRepository catRepository;

	@PostConstruct
	@Transactional
	public void init() {

		if (userRepository.count() == 0) {
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

			MainMenu demoMainMenu = new MainMenu();
			demoMainMenu.setName("示例演示");
			demoMainMenu.setCode(0);
			demoMainMenu = mainMenuRepository.save(demoMainMenu);

			//Menu Item
			MenuItem menuItemOfAdmin = buildMenuItem("用户管理", "Admin Management", "ROLE_USER;OPT_USER_LIST", 1, "/manage/users.do", mainMenu);
			menuItemOfAdmin = menuItemRepository.save(menuItemOfAdmin);
			MenuItem menuItemOfMainMenu = buildMenuItem("菜单管理", "Main Menu", "ROLE_MAINMENU;OPT_MAINMENU_LIST;ROLE_MENUITEM;ROLE_RESOURCE", 2,
			  "/manage/mainMenus.do", mainMenu);
			menuItemOfMainMenu = menuItemRepository.save(menuItemOfMainMenu);
			MenuItem menuItemOfRole = buildMenuItem("角色管理", "Role Management", "ROLE_ROLE;OPT_ROLE_LIST", 3, "/manage/roles.do", mainMenu);
			menuItemOfRole = menuItemRepository.save(menuItemOfRole);

			MenuItem menuItemOfDemo = buildMenuItem("猫管理", "Cat Management", "ROLE_CAT;OPT_CAT_LIST", 1, "/demo/cats.do", demoMainMenu);
			menuItemOfDemo = menuItemRepository.save(menuItemOfDemo);


			//Resource
			Resource manageAdmin = builtResource("用户管理", "Admin Management",
			  "OPT_USER_ADD;OPT_USER_EDIT;OPT_USER_DELETE;OPT_USER_RESET;OPT_USER_DETAIL", menuItemOfAdmin);
			manageAdmin = resourceRepository.save(manageAdmin);
			Resource checkAdminDetail = builtResource("查看用户", "Admin Read", "OPT_USER_DETAIL", menuItemOfAdmin);
			checkAdminDetail = resourceRepository.save(checkAdminDetail);
			Resource manageMenuItem = builtResource("菜单管理", "Menu Management",
			  "OPT_MAINMENU_ADD;OPT_MAINMENU_EDIT;OPT_MAINMENU_DELETE;OPT_MAINMENU_DETAIL;OPT_MENUITEM_ADD;OPT_MENUITEM_EDIT;OPT_MENUITEM_DELETE;OPT_MENUITEM_DETAIL;OPT_RESOURCE_ADD;OPT_RESOURCE_EDIT;OPT_RESOURCE_DELETE",
			  menuItemOfMainMenu);
			manageMenuItem = resourceRepository.save(manageMenuItem);
			Resource checkMainMenu = builtResource("菜单查看", "Main Menu Management", "OPT_MAINMENU_DETAIL;OPT_MENUITEM_DETAIL", menuItemOfMainMenu);
			checkMainMenu = resourceRepository.save(checkMainMenu);
			Resource manageRole = builtResource("角色管理", "Role Management", "OPT_ROLE_ADD;OPT_ROLE_EDIT;OPT_ROLE_DELETE;OPT_ROLE_DETAIL",
			  menuItemOfRole);
			manageRole = resourceRepository.save(manageRole);
			Resource checkRoleDetail = builtResource("角色查看", "Role Read", "OPT_ROLE_DETAIL", menuItemOfRole);
			checkRoleDetail = resourceRepository.save(checkRoleDetail);

			Resource manageDemoMenu = builtResource("示例管理", "Demo Management", "OPT_CAT_CREATE;OPT_CAT_DELETE", menuItemOfDemo);
			manageDemoMenu = resourceRepository.save(manageDemoMenu);


			//Role
			Role role = new Role();
			role.setName("超级管理员");
			role.setDescription("拥有菜单管理，角色管理，用户管理权限");
			role.setMenuItems(newArrayList(menuItemOfAdmin, menuItemOfMainMenu, menuItemOfRole, menuItemOfDemo));
			role.setResources(newArrayList(manageAdmin, checkMainMenu, manageMenuItem, checkAdminDetail, manageRole, checkRoleDetail, manageDemoMenu));
			role = roleRepository.save(role);

			user.setRoles(newArrayList(role));
			userRepository.save(user);

			logger.info("完成初始化系统基础数据...");
		}

		if (catRepository.count() < 100) {
			logger.info("开始初始化猫数据...");
			Long catId = 1L;
			List<CatPO> cats = newArrayList();
			cats.add(buildCat(catId++, "阿比西尼亚猫", "仪表堂堂、尊贵、庄严、天生一副帝王之相，加上它红黄相间、深浅不一、变化莫测、华丽动人的被毛，使不少爱猫者为之倾倒。阿比西尼亚猫是短毛猫中的贵族，也是世界上最流行的短毛猫之一，尤受北美爱猫人士的欢迎。", SaleStatusEnum.FOR_SALE, CommonStatusEnum.ENABLE));
			cats.add(buildCat(catId++, "埃及猫", "早在公元1400年前，尼罗河畔古埃及寺庙的壁画上，就画有一种带斑点的猫的形象。在古埃及，猫是神的化身，人们对猫非常崇拜，死后要厚葬。", SaleStatusEnum.LOCK, CommonStatusEnum.ENABLE));
			cats.add(buildCat(catId++, "奥西猫", "欧西猫”的拼写是“OCELOT”(美洲的斑点野猫)和“猫”的结合体。确实，该品种带斑点的被毛使之形似小型的野猫。", SaleStatusEnum.SOLD, CommonStatusEnum.ENABLE));
			cats.add(buildCat(catId++, "巴厘猫", "最早出现在人们认为是纯暹罗猫血统的猫中。当时养猫者几乎不感兴趣，但是巴厘猫的吸引力逐步扩大，所以品种便开始发展。因为长毛是显性基因，因此任何两个巴厘猫交配必然生出中长毛型的巴厘仔猫，但也采用异种型杂交，以保持暹罗猫短毛的外形。", SaleStatusEnum.FOR_SALE, CommonStatusEnum.DISABLE));
			cats.add(buildCat(catId++, "伯曼猫", "伯曼猫相传是源出于缅甸。", SaleStatusEnum.SOLD, CommonStatusEnum.DISABLE));
			for (int i = 0; i < MathUtils.randomInt(211, 985); i++) {
				cats.add(buildCat(catId++, "随机薛定谔的猫" + i, "随机薛定谔的猫，一种存在于计算机程序中的特殊的猫，该猫处于“死－活叠加态”——既死了又活着！", SaleStatusEnum.values()[MathUtils.randomInt(0,SaleStatusEnum.values().length - 1)], MathUtils.randomInt(1,100) % 3 == 0 ? CommonStatusEnum.ENABLE : CommonStatusEnum.DISABLE));
			}
			catRepository.save(cats);
			logger.info("完成初始化猫数据...");
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

	private CatPO buildCat(Long id, String name, String description, SaleStatusEnum saleStatus, CommonStatusEnum commonStatus){

		CatPO catPO = new CatPO();
		catPO.setId(id);
		catPO.setName(name);
		catPO.setHomepage("https://www.baidu.com/s?wd=" + name);
		catPO.setDescription(description);
		catPO.setSaleStatus(saleStatus);
		catPO.setStatus(commonStatus);
		return catPO;
	}
}
