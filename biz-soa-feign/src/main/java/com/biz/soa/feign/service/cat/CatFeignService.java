package com.biz.soa.feign.service.cat;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.service.demo.interfaces.CatService;
import com.biz.soa.feign.client.cat.CatFeignClient;
import com.biz.soa.feign.service.AbstractFeignService;
import com.biz.vo.demo.CatReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: defei
 * @date 5/15/17
 */
@Service
public class CatFeignService extends AbstractFeignService implements CatService {

	@Autowired
	private CatFeignClient catFeignClient;

	@Override
	public CatPO save(CatReqVO vo) {

		return getResultData(catFeignClient.save(vo));
	}

	@Override
	public CatPO getByName(String name) {

		return getResultData(catFeignClient.getByName(name));
	}

	@Override
	public List<CatPO> listByStatus(CommonStatusEnum status) {

		return getResultData(catFeignClient.listByStatus(status));
	}

	@Override
	public List<CatPO> listBySaleStatus(SaleStatusEnum saleStatus) {

		return getResultData(catFeignClient.listBySaleStatus(saleStatus));
	}

	@Override
	public PageVO<CatPO> searchCat(CatSearchVO reqVo) {

		return getResultData(catFeignClient.searchCat(reqVo));
	}

	@Override
	public void updateSaleStatus(Long id, SaleStatusEnum saleStatus) {

		getResultData(catFeignClient.updateSaleStatus(id, saleStatus));
	}

	@Override
	public CatPO get(Long id) {

		return getResultData(catFeignClient.get(id));
	}

	@Override
	public void remove(Long id) {

		getResultData(catFeignClient.remove(id));
	}
}
