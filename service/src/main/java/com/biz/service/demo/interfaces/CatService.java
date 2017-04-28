package com.biz.service.demo.interfaces;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.service.RepositorySupportService;
import com.biz.vo.demo.CatReqVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author defei
 * @date 2017年04月07日
 */
public interface CatService extends RepositorySupportService<CatPO> {

	CatPO save(CatReqVO vo);

	CatPO getByName(String name);

	List<CatPO> listByStatus(CommonStatusEnum status);

	List<CatPO> listBySaleStatus(SaleStatusEnum saleStatus);

	Page<CatPO> searchCat(CatSearchVO reqVo);

	void updateSaleStatus(Long id, SaleStatusEnum saleStatus);

}
