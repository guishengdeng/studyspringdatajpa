package com.biz.manage.controller;

import com.biz.gbck.dao.redis.ro.upgrade.UpgradeRo;
import com.biz.service.upgrade.UpgradeService;
import com.biz.soa.feign.client.global.GlobalFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.biz.gbck.vo.upgrade.AddUpgradeVo;

import java.util.List;

@Controller
@RequestMapping("/upgrade")
@Secured("ROLE_UPGRADE")
public class UpgradeController {

	@Autowired
	private GlobalFeignClient globalFeignClient;

	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_LIST')")
	public ModelAndView list() {
		ModelAndView view =  new ModelAndView("manage/upgrade/list");
		List<UpgradeRo> ios= globalFeignClient.findUpgradeByOs("ios");
		List<UpgradeRo> android = globalFeignClient.findUpgradeByOs("androId");
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
		globalFeignClient.deleteUpgradeById(id);
		return new ModelAndView("redirect:/upgrade/list.do");
	}
	
	@RequestMapping("/save_add")
	@PreAuthorize("hasAuthority('OPT_UPGRADE_SAVEADD')")
	public ModelAndView save_add(AddUpgradeVo upgrade) {
		globalFeignClient.saveUpgrade(upgrade);
		return new ModelAndView("redirect:/upgrade/list.do");
	}

	/**
	 * 判断给的版本号码是否存在
	 */
	@RequestMapping("/verify")
	@ResponseBody
	public JSONResult verifyVersion(@RequestParam("version") String version, @RequestParam("os") String os){
		boolean verify = globalFeignClient.verifyVersion(version,os);
		ModelAndView mm=new ModelAndView();
		mm.addObject("verify",verify);
		return new JSONResult(mm);
	}


}
