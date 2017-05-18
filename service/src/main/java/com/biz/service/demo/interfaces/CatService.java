package com.biz.service.demo.interfaces;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.vo.demo.CatReqVO;

import java.util.List;

/**
 * @author defei
 * @date 2017年04月07日
 */
public interface CatService {

	CatPO save(CatReqVO vo);

	CatPO getByName(String name);

	List<CatPO> listByStatus(CommonStatusEnum status);

	List<CatPO> listBySaleStatus(SaleStatusEnum saleStatus);

	PageVO<CatPO> searchCat(CatSearchVO reqVo);

	CatPO get(Long id);

	void remove(Long id);

	void updateSaleStatus(Long id, SaleStatusEnum saleStatus);

}
