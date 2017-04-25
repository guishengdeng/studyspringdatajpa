package com.biz.manage.controller;

import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.service.UpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.biz.gbck.vo.upgrade.AddUpgradeVo;

import java.util.List;

@Controller
@RequestMapping("/upgrade")
@Secured("ROLE_UPGRADE")
public class UpgradeController {

	@Autowired
	private UpgradeService upgradeService;

	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_LIST')")
	public ModelAndView list() {
		ModelAndView view =  new ModelAndView("manage/upgrade/list");
		List<UpgradeRo> ios= upgradeService.findAll("ios");
		List<UpgradeRo> android = upgradeService.findAll("android");
		view.addObject("ios", ios);
		view.addObject("android", android);
		return view;
	}

	@RequestMapping("/add")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_ADD')")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView("manage/upgrade/add");
		view.addObject("cmd", "add");
		return view;
	}


	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_DELETE')")
	public ModelAndView delete(@RequestParam("id") String id) {
		upgradeService.delete(id);
		return new ModelAndView("redirect:/upgrade/list.do");
	}
	
	@RequestMapping("/save_add")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_SAVEADD')")
	public ModelAndView save_add(AddUpgradeVo upgrade) {
		upgradeService.save(upgrade);
		return new ModelAndView("redirect:/upgrade/list.do");
	}

}
