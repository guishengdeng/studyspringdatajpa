package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * 商户资质Repository
 * Created by defei on 3/11/16.
 */
@Repository
public interface ShopQualificationRepository
    extends CommonJpaRepository<ShopQualificationPo, Long>, JpaSpecificationExecutor<ShopQualificationPo> {

    List<ShopQualificationPo> findByShopIdAndAuditStatusOrderByIdDesc(Long shopId,
                                                                      Integer auditStatus);

    List<ShopQualificationPo> findByShopIdAndAuditStatusInOrderByIdDesc(Long shopId,
                                                                        List<Integer> auditStatus);

    List<ShopQualificationPo> findByShopIdOrderByIdDesc(Long shopId);

    List<ShopQualificationPo> findByAuditStatusInOrderByIdDesc(
            Collection<Integer> qualificationAuditStatus);

    @Modifying
    @Query("UPDATE ShopQualificationPo SET auditStatus = :statusUpdateTo WHERE shop.id = :shopId AND auditStatus in :statusWillBeUpdate")
    Integer updateAuditStatus(@Param("shopId") Long shopId,
                              @Param("statusUpdateTo") Integer statusUpdateTo,
                              @Param("statusWillBeUpdate") List<Integer> statusWillBeUpdate);

    @Modifying
    @Transactional
    @Query("UPDATE ShopQualificationPo SET auditStatus = :auditStatus  WHERE shop.id = :shopId")
    void updateAuditStatusByShopId(@Param("shopId") Long shopId,
                                   @Param("auditStatus") Integer auditStatus);
}
