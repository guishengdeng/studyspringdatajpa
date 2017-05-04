package com.biz.gbck.dao.mysql.repository.saleTag;

import com.biz.gbck.dao.mysql.po.product.meta.SaleTag;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 销售标签 Repository
 *
 * @author lzz
 * @date 2017年5月2日
 * @reviewer
 * @see
 */
@Repository
public interface SaleTagRepository extends JpaRepository<SaleTag, Long> , SaleTagDao,JpaSpecificationExecutor{

    List<SaleTag> findByStatus(CommonStatusEnum status);

    @Transactional
    @Modifying
    @Query("UPDATE SaleTag saleTag SET saleTag.status= :status WHERE saleTag.id= :id")
    Integer updateStatus(@Param("id") Long id, @Param("status") CommonStatusEnum status);

}
