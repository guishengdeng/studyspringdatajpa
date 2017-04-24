package com.biz.gbck.dao.mysql.repository.demo;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CatRepository extends CommonJpaRepository<CatPO, Long>, CatDao, JpaSpecificationExecutor<CatPO> {

	CatPO findByName(String name);

	List<CatPO> findByStatus(CommonStatusEnum status);

	List<CatPO> findBySaleStatus(SaleStatusEnum saleStatus);

	@Transactional
	@Modifying
	@Query("UPDATE CatPO cat SET cat.saleStatus = :saleStatus WHERE cat.id = :id")
	void updateSaleStatus(@Param("id") Long id, @Param("saleStatus") SaleStatusEnum saleStatus);

	@Transactional
	@Modifying
	@Query("UPDATE CatPO cat SET cat.status = :status WHERE cat.id = :id")
	Integer updateStatus(@Param("id") Long id, @Param("status") CommonStatusEnum status);
}
