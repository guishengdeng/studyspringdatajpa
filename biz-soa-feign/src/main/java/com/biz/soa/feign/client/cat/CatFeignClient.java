package com.biz.soa.feign.client.cat;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.gbck.vo.spring.PageVO;
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
public interface CatFeignClient {

	@RequestMapping(value = "soa/catService/save", method = RequestMethod.POST)
	MicroServiceResult<CatPO> save(@RequestBody CatReqVO vo);

	@RequestMapping(value = "soa/catService/getByName", method = RequestMethod.POST)
	MicroServiceResult<CatPO> getByName(@RequestParam("name") String name);

	@RequestMapping(value = "soa/catService/listByStatus", method = RequestMethod.POST)
	MicroServiceResult<List<CatPO>> listByStatus(@RequestParam("status") CommonStatusEnum status);

	@RequestMapping(value = "soa/catService/listBySaleStatus", method = RequestMethod.POST)
	MicroServiceResult<List<CatPO>> listBySaleStatus(@RequestParam("saleStatus") SaleStatusEnum saleStatus);

	@RequestMapping(value = "soa/catService/searchCat", method = RequestMethod.POST)
	MicroServiceResult<PageVO<CatPO>> searchCat(@RequestBody CatSearchVO reqVo);

	@RequestMapping(value = "soa/catService/updateSaleStatus", method = RequestMethod.POST)
	MicroServiceResult updateSaleStatus(@RequestParam("id") Long id, @RequestParam("saleStatus") SaleStatusEnum saleStatus);

	@RequestMapping(value = "soa/catService/get", method = RequestMethod.POST)
	MicroServiceResult<CatPO> get(@RequestParam("id") Long id);

	@RequestMapping(value = "soa/catService/remove", method = RequestMethod.POST)
	MicroServiceResult remove(@RequestParam("id") Long id);
}
