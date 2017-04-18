package com.biz.manage.controller;

import com.biz.gbck.common.ro.upgrade.UpgradeRo;
import com.biz.gbck.common.vo.upgrade.AddUpgradeVo;
import com.biz.service.UpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/upgrade")
@Secured("ROLE_UPGRADE")
public class UpgradeController {

	@Autowired
	private UpgradeService clientService;
		
	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_LIST')")
	public ModelAndView list() {
		ModelAndView view =  new ModelAndView("upgrade/list");
		List<UpgradeRo> ios= clientService.findAll("ios");
		List<UpgradeRo> androId = clientService.findAll("androId");
		view.addObject("ios", ios);
		view.addObject("androId", androId);
		return view;
	}

	@RequestMapping("/add")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_ADD')")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView("upgrade/add");
		view.addObject("cmd", "add");
		return view;
	}


	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_DELETE')")
	public ModelAndView delete(@RequestParam("id") String id) {
		clientService.delete(id);
		return new ModelAndView("redirect:/upgrade/list.do");
	}
	
	@RequestMapping("/save_add")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_SAVEADD')")
	public ModelAndView save_add(AddUpgradeVo upgrade) {
		clientService.save(upgrade);
		return new ModelAndView("redirect:/upgrade/list.do");
	}

}
