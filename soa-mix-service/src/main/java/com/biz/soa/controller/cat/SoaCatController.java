package com.biz.soa.controller.cat;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.service.demo.interfaces.CatService;
import com.biz.soa.base.SoaBaseController;
import com.biz.vo.demo.CatReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Soa Cat Controller
 * <p/>
 * Created by defei on 2017/05/18 12:09.
 */
@RestController
@RequestMapping(value = "soa/catService")
public class SoaCatController extends SoaBaseController {

	@Autowired
	@Qualifier("catService")
	private CatService catService;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	CatPO save(@RequestBody CatReqVO reqVO) {

		return catService.save(reqVO);
	}

	@RequestMapping(value = "getByName", method = RequestMethod.POST)
	CatPO getByName(@RequestParam("name") String name) {

		return catService.getByName(name);
	}

	@RequestMapping(value = "listByStatus", method = RequestMethod.POST)
	List<CatPO> listByStatus(@RequestParam("status") CommonStatusEnum status) {

		return catService.listByStatus(status);
	}

	@RequestMapping(value = "listBySaleStatus", method = RequestMethod.POST)
	List<CatPO> listBySaleStatus(@RequestParam("saleStatus") SaleStatusEnum saleStatus) {

		return catService.listBySaleStatus(saleStatus);
	}

	@RequestMapping(value = "searchCat", method = RequestMethod.POST)
	Page<CatPO> searchCat(@RequestBody CatSearchVO reqVo) {

		return catService.searchCat(reqVo);
	}

	@RequestMapping(value = "updateSaleStatus", method = RequestMethod.POST)
	void updateSaleStatus(@RequestParam("id") Long id, @RequestParam("saleStatus") SaleStatusEnum saleStatus) {

		catService.updateSaleStatus(id, saleStatus);
	}

}
