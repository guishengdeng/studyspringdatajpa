package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
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
 * 商户详细资料Repository
 * Created by defei on 3/11/16.
 */
@Repository
public interface ShopDetailRepository extends CommonJpaRepository<ShopDetailPo, Long>, JpaSpecificationExecutor<ShopDetailPo> {

    List<ShopDetailPo> findByShopIdAndAuditStatusOrderByCreateTimeDesc(Long shopId,
                                                                       Integer auditStatus);

    List<ShopDetailPo> findByShopIdAndAuditStatusInOrderByCreateTimeDesc(Long shopId,
                                                                         Collection<Integer> auditStatus);

    List<ShopDetailPo> findByAuditStatusInOrderByCreateTimeDesc(Collection<Integer> auditStatus);

    List<ShopDetailPo> findByShopIdOrderByIdDesc(Long shopId);

    @Modifying
    @Query("UPDATE ShopDetailPo SET auditStatus = :statusUpdateTo WHERE shop.id = :shopId AND auditStatus in :statusWillBeUpdate")
    Integer updateAuditStatus(@Param("shopId") Long shopId,
                              @Param("statusUpdateTo") Integer statusUpdateTo,
                              @Param("statusWillBeUpdate") List<Integer> statusWillBeUpdate);

    @Modifying
    @Transactional
    @Query("UPDATE ShopDetailPo SET auditStatus = :auditStatus  WHERE shop.id = :shopId")
    void updateAuditStatusByShopId(@Param("shopId") Long shopId,
                                   @Param("auditStatus") Integer auditStatus);
}
