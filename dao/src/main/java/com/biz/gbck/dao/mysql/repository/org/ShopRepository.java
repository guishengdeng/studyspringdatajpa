package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by defei on 3/11/16.
 */
@Repository
public interface ShopRepository extends CommonJpaRepository<ShopPo, Long>, ShopDao, JpaSpecificationExecutor<ShopPo>{

    List<ShopPo> findByMobileOrderByIdDesc(String mobilePhone);

    ShopPo findByName(String name);

    @Modifying
    @Query("update ShopPo set detailAuditStatus = :detailAuditStatus WHERE id = :id")
    void updateDetailAuditStatus(@Param("id") Long shopId,
                                 @Param("detailAuditStatus") Integer value);

    @Modifying
    @Query("update ShopPo set qualificationAuditStatus = :qualificationAuditStatus WHERE id = :id")
    void updateQualificationAuditStatus(@Param("id") Long shopId,
                                        @Param("qualificationAuditStatus") Integer value);

    @Modifying
    @Query("Update ShopPo Set status=:status where id=:id") void updateShopStatusById(
            @Param("id") Long id, @Param("status") CommonStatusEnum status);

    @Modifying
    @Query("UPDATE ShopPo SET deliveryName = :deliveryName WHERE id = :shopId")
    void updateDeliveryName(@Param("shopId") Long shopId,
                            @Param("deliveryName") String deliveryName);


//    List<ShopPo> findByDepotId(String depotId);

//    List<ShopPo> findByAssartDepotId(String depotId);

    List<ShopPo> findByIdIn(Set<Long> shopIds);

    @Query("SELECT id FROM ShopPo WHERE businessLicenceId = :businessLicenceId AND id != :shopId")
    List<Long> findShopIdBybusinessLicenceId(@Param("businessLicenceId") String businessLicenceId,
                                             @Param("shopId") Long shopId);

    @Query("From ShopPo Where createTime between :startTime and :endTime order by createTime")
    List<ShopPo> findByStartTimeAndEndTime(@Param("startTime") Timestamp startTime,
                                           @Param("endTime") Timestamp endTime);
    @Query("From ShopPo where inviterCode=?1")
    List<ShopPo> findshopByInviterCode(String inviterCode);

    List<ShopPo> findByShopTypeId(Long shopTypeId);

    @Query("FROM ShopPo s WHERE s.shopType.id = ?1 AND s.createTime >= ?2 AND s.createTime < ?3")
    List<ShopPo> findByShopTypeIdAndStartTimeAndEndTime(Long shopTypeId, Timestamp startTime,
                                                        Timestamp endTime);

    @Query("FROM ShopPo s WHERE s.mobile = :mobile AND s.id != :shopId")
    List<ShopPo> findShopPoByMobileAndHaveNotShopId(@Param("mobile") String mobile,
                                                    @Param("shopId") Long shopId);


    List<ShopPo> findById(@Param("id") List<Long> ids);


    @Query("From ShopPo s WHERE s.detailAuditStatus = :auditStatus AND s.qualificationAuditStatus = :auditStatus order by s.createTime desc")
    List<ShopPo> findByAuditStatus(@Param("auditStatus") Integer auditStatus);

}
