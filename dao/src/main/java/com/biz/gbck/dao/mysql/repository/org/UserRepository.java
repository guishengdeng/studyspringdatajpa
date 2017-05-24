package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserPo, Long>, UserDao {


    UserPo findByMobile(String mobilePhone);

    UserPo findByAccount(String account);


    @Modifying
    @Query("update UserPo u set u.password = :password,u.originalPassword = :originalPassword  where u.mobile = :mobile")
    void updateUserPassword(@Param("mobile") String mobile, @Param("password") String password,
                            @Param("originalPassword") String originalPassword);

    List<UserPo> findByShopId(Long shopId);


    @Modifying
    @Query("UPDATE UserPo SET status = 0 WHERE id = :id")
    void disableUser(@Param("id") Long userId);

    @Query("SELECT id FROM UserPo") List<Long> findAllUserIds();

    @Query("SELECT id FROM UserPo u WHERE u.shop.province.id = :provinceId")
    List<Long> findUserIdsByProvince(@Param("provinceId") Integer provinceId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.city.id = :cityId")
    List<Long> findUserIdsByCity(@Param("cityId") Integer cityId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.district.id = :districtId")
    List<Long> findUserIdsByDistrict(@Param("districtId") Integer districtId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.shopType.id = :shopTypeId")
    List<Long> findUserIdsByShopType(@Param("shopTypeId") Long shopTypeId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.id = :shopId")
    List<Long> findUserIdsByShopId(@Param("shopId") Long shopId);

    @Query("FROM UserPo u WHERE u.shop.id = :shopId")
    List<UserPo> findUserByShopId(@Param("shopId") Long shopId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.province.id = :provinceId AND u.shop.shopType.id = :shopTypeId")
    List<Long> findUserIdsByProvinceAndShopType(@Param("provinceId") Integer provinceId,
                                                @Param("shopTypeId") Integer shopTypeId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.city.id = :cityId AND u.shop.shopType.id = :shopTypeId")
    List<Long> findUserIdsByCityAndShopType(@Param("cityId") Integer cityId,
                                            @Param("shopTypeId") Integer shopTypeId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.district.id = :districtId AND u.shop.shopType.id = :shopTypeId")
    List<Long> findUserIdsByDistrictAndShopType(@Param("districtId") Integer districtId,
                                                @Param("shopTypeId") Integer shopTypeId);

    @Modifying
    @Query("UPDATE UserPo SET mobile = :mobile WHERE id = :id")
    void updateUserMobile(@Param("id") Long userId, @Param("mobile") String mobile);

    @Modifying
    @Query("UPDATE UserPo SET avatar = :avatar WHERE id = :id")
    void updateUserAvatar(@Param("id") Long userId, @Param("avatar") String avatar);

    @Modifying
    @Query("UPDATE UserPo SET lastLoginDeviceToken = :lastLoginDeviceToken, lastLoginTime = :lastLoginTime, lastLoginIP = :lastLoginIP WHERE id = :userId")
    void updateUserLatestLoginInfo(@Param("userId") Long userId,
                                   @Param("lastLoginDeviceToken") String lastLoginDeviceToken,
                                   @Param("lastLoginTime") Timestamp lastLoginTime, @Param("lastLoginIP") String lastLoginIP);

    @Modifying @Query("UPDATE UserPo SET lastLoginDeviceToken = null WHERE id = :userId")
    void cleanLoginDevice(@Param("userId") Long userId);

    @Modifying@Query("Update UserPo Set status=:status where id=:id")
    void updateUserStatusById(@Param("id")Long id, @Param("status")Integer status);

    @Query("SELECT u.id FROM UserPo u INNER JOIN u.shop.saleAreas s WHERE s.id = :saleAreaId") List<Long> findUserIdsBySaleAreaId(@Param("saleAreaId") Integer saleAreaId);

    @Query("SELECT id FROM UserPo u WHERE u.shop.id = :shopId and u.isAdmin = :isAdmin ORDER BY id DESC")
    List<Long> findUserIdByShopIdAndAdminStatus(@Param("shopId") Long shopId, @Param("isAdmin") Boolean isAdmin);

    List<UserPo> findByOldIdIsNull();

    @Query("SELECT u.id FROM UserPo u WHERE u.shop.detailAuditStatus = :auditStatus AND u.shop.qualificationAuditStatus = :auditStatus")
    List<Long> findAllUserIds(@Param("auditStatus") Integer auditStatus);

    @Query("FROM UserPo u WHERE u.shop.detailAuditStatus = :auditStatus AND u.shop.qualificationAuditStatus = :auditStatus")
    List<UserPo> findAllUserByAuditStatus(@Param("auditStatus") Integer auditStatus);

    @Query("FROM UserPo u WHERE u.shop.id = :shopId and u.isAdmin = :isAdmin ORDER BY id DESC")
    List<UserPo> findUsersByShopIdAndAdminStatus(@Param("shopId") Long shopId, @Param("isAdmin") Boolean isAdmin);

    @Query("FROM UserPo u WHERE u.shop.detailAuditStatus = :auditStatus AND u.shop.qualificationAuditStatus = :auditStatus order by u.createTime desc")
    List<UserPo> findAllUserByAuditStatusTwo(@Param("auditStatus") Integer auditStatus);
}
