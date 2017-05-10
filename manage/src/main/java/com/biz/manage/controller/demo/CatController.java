package com.biz.manage.controller.demo;
import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.manage.controller.BaseController;
import com.biz.service.demo.interfaces.CatService;
import com.biz.vo.demo.CatReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
	public ModelAndView list(@ModelAttribute("reqVo") CatSearchVO reqVo) {

		Page<CatPO> catPage = catService.searchCat(reqVo);
		return new ModelAndView("demo/cats/searchResult", "catPage", catPage);
	}

	@GetMapping("listByStatus")
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

	@PostMapping("save")
	@PreAuthorize("hasAuthority('OPT_CAT_CREATE')")
	public ModelAndView save(@Valid CatReqVO catReqVO, BindingResult result) {

		error(result);
		catService.save(catReqVO);
		return new ModelAndView("redirect:/demo/cats.do");
	}

	@PostMapping("delete")
	@PreAuthorize("hasAuthority('OPT_CAT_DELETE')")
	@ResponseBody
	public Boolean delete(@RequestParam("id") Long id) {

		try {
			catService.remove(id);
			return true;
		} catch (Exception e) {
			logger.info("Kill cat[{}] failed.", id, e);
			return false;
		}

	}
}
