package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserPo, Long>, UserDao {


    @Query("SELECT id FROM UserPo u WHERE u.shop.shopType.id = :shopTypeId")
    List<Long> findUserIdsByShopType(@Param("shopTypeId") Long shopTypeId);


    @Query("SELECT u.id FROM UserPo u INNER JOIN u.shop.saleAreas s WHERE s.id = :saleAreaId")
    List<Long> findUserIdsBySaleAreaId(@Param("saleAreaId") Integer saleAreaId);


    UserPo findByMobile(String mobilePhone);

    @Modifying
    @Query("UPDATE UserPo SET lastLoginDeviceToken = null WHERE id = :userId")
    void cleanLoginDevice(@Param("userId") Long userId);


    @Modifying @Query("update UserPo u set u.password = :password,u.originalPassword = :originalPassword  where u.mobile = :mobile")
    void updateUserPassword(@Param("mobile") String mobile, @Param("password") String password,@Param("originalPassword") String originalPassword);

}
