package com.biz.soa.feign.hystrix.cat;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.soa.feign.client.cat.CatFeignClient;
import com.biz.vo.demo.CatReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
	public CatPO save(@RequestBody CatReqVO vo) {

		return null;
	}

	@Override
	public CatPO getByName(@RequestParam("name") String name) {

		return null;
	}

	@Override
	public List<CatPO> listByStatus(@RequestParam("status") CommonStatusEnum status) {

		return null;
	}

	@Override
	public List<CatPO> listBySaleStatus(@RequestParam("saleStatus") SaleStatusEnum saleStatus) {

		return null;
	}

	@Override
	public PageVO<CatPO> searchCat(@RequestBody CatSearchVO reqVo) {

		return null;
	}

	@Override
	public void updateSaleStatus(@RequestParam("id") Long id, @RequestParam("saleStatus") SaleStatusEnum saleStatus) {

	}

	@Override
	public CatPO get(Long id) {

		return null;
	}

	@Override
	public void remove(Long id) {

	}
}
