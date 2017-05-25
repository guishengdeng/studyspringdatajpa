package com.biz.soa.feign.hystrix.cat;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.soa.feign.client.cat.CatFeignClient;
import com.biz.vo.demo.CatReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by defei on 2017/05/12 12:16.
 */
@Component
public class CatFeignClientHystrix implements CatFeignClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void setup() {

		logger.info("Init " + getClass());
	}

	@Override
	public MicroServiceResult<CatPO> save(@RequestBody CatReqVO vo) {

		return MicroServiceResult.buildSuccess();
	}

	@Override
	public MicroServiceResult<CatPO> getByName(@RequestParam("name") String name) {

		return MicroServiceResult.buildSuccess();
	}

	@Override
	public MicroServiceResult<List<CatPO>> listByStatus(@RequestParam("status") CommonStatusEnum status) {

		return MicroServiceResult.buildSuccess();
	}

	@Override
	public MicroServiceResult<List<CatPO>> listBySaleStatus(@RequestParam("saleStatus") SaleStatusEnum saleStatus) {

		return MicroServiceResult.buildSuccess();
	}

	@Override
	public MicroServiceResult<PageVO<CatPO>> searchCat(@RequestBody CatSearchVO reqVo) {

		return MicroServiceResult.buildSuccess();
	}

	@Override
	public MicroServiceResult updateSaleStatus(@RequestParam("id") Long id, @RequestParam("saleStatus") SaleStatusEnum saleStatus) {

		return MicroServiceResult.buildSuccess();
	}

	@Override
	public MicroServiceResult<CatPO> get(@RequestParam("id") Long id) {

		return MicroServiceResult.buildSuccess();
	}

	@Override
	public MicroServiceResult remove(@RequestParam("id") Long id) {

		return MicroServiceResult.buildSuccess();
	}
}
