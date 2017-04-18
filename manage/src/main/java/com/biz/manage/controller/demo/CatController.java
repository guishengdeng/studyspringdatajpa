package com.biz.manage.controller.demo;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.manage.controller.BaseController;
import com.biz.service.demo.interfaces.CatService;
import com.biz.vo.demo.CatReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author defei
 * @date 2017年04月12日
 * @reviewer
 */
@Controller
@RequestMapping("demo/cats")
@Secured("ROLE_CAT")
public class CatController extends BaseController {

	@Autowired
	private CatService catService;

	@GetMapping
	@PreAuthorize("hasAuthority('OPT_CAT_LIST')")
	public ModelAndView list(@RequestParam(value = "status", required = false, defaultValue = "ENABLE") CommonStatusEnum status) {

		List<CatPO> cats = catService.listByStatus(status);
		return new ModelAndView("demo/cats/list", "cats", cats).addObject("enabled", status.isEnable());
	}

	@GetMapping("listBySaleStatus")
	@PreAuthorize("hasAuthority('OPT_CAT_LIST')")
	public ModelAndView listBySaleStatus(@RequestParam(value = "saleStatus", required = false) SaleStatusEnum saleStatus) {

		List<CatPO> cats = catService.listBySaleStatus(saleStatus);
		return new ModelAndView("demo/cats/list", "cats", cats);
	}

	@RequestMapping("{id}")
	@PreAuthorize("hasAuthority('OPT_CAT_LIST')")
	public ModelAndView edit(@PathVariable Long id) {

		return new ModelAndView("demo/cats/new", "cat", catService.get(id));
	}

	@GetMapping("new")
	@PreAuthorize("hasAuthority('OPT_CAT_CREATE')")
	public ModelAndView add() {

		return new ModelAndView("demo/cats/new");
	}

	@PostMapping
	@PreAuthorize("hasAuthority('OPT_CAT_CREATE')")
	public ModelAndView save(CatReqVO catReqVO, BindingResult result, HttpServletRequest request) {

		error(result);
		catService.save(catReqVO);
		return new ModelAndView("redirect:/demo/cats.do");
	}

	@GetMapping("delete")
	@PreAuthorize("hasAuthority('OPT_CAT_DELETE')")
	public ModelAndView delete(@RequestParam("id") Long id) {

		catService.remove(id);
		return new ModelAndView("redirect:/demo/cats.do");
	}
}
