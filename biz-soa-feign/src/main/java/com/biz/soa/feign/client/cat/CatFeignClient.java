package com.biz.soa.feign.client.cat;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.service.demo.interfaces.CatService;
import com.biz.soa.feign.hystrix.cat.CatFeignClientHystrix;
import com.biz.vo.demo.CatReqVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: defei
 * @date 5/15/17
 */
@FeignClient(name = "soa-mix-service", fallback = CatFeignClientHystrix.class)
public interface CatFeignClient extends CatService {

	@Override
	@RequestMapping(value = "soa/catServiceService/save", method = RequestMethod.POST)
	CatPO save(@RequestBody CatReqVO vo);

	@Override
	@RequestMapping(value = "soa/catService/getByName", method = RequestMethod.POST)
	CatPO getByName(@RequestParam("name") String name);

	@Override
	@RequestMapping(value = "soa/catService/listByStatus", method = RequestMethod.POST)
	List<CatPO> listByStatus(@RequestParam("status") CommonStatusEnum status);

	@Override
	@RequestMapping(value = "soa/catService/listBySaleStatus", method = RequestMethod.POST)
	List<CatPO> listBySaleStatus(@RequestParam("saleStatus") SaleStatusEnum saleStatus);

	@Override
	@RequestMapping(value = "soa/catService/searchCat", method = RequestMethod.POST)
	PageVO<CatPO> searchCat(@RequestBody CatSearchVO reqVo);

	@Override
	@RequestMapping(value = "soa/catService/updateSaleStatus", method = RequestMethod.POST)
	void updateSaleStatus(@RequestParam("id") Long id, @RequestParam("saleStatus") SaleStatusEnum saleStatus);

	@Override
	@RequestMapping(value = "soa/catService/get", method = RequestMethod.POST)
	CatPO get(@RequestParam("id") Long id);

	@Override
	@RequestMapping(value = "soa/catService/remove", method = RequestMethod.POST)
	void remove(@RequestParam("id") Long id);
}
