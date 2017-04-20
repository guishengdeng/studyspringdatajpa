package com.biz.gbck.dao.mysql.repository.bbc.vendor;

import com.biz.gbck.dao.mysql.po.vendor.bbc.VendorAddress;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author mounan
 * @Description: 店铺地址dao
 * @time:2017年1月12日 下午12:07:34
 */
@Repository
public interface VendorAddressRepository extends CommonJpaRepository<VendorAddress, Long>, JpaSpecificationExecutor<VendorAddress> {

    /**
     * @param id 店铺id
     * @Description: 查询店铺的地址
     * @author mounan
     * @time:2017年1月12日 下午12:08:00
     */

    @Query("from VendorAddress va   where va.vendor.id = ?1 and va.deleteFlag=false")
    List<VendorAddress> findVendorAddressesByVendor(Long id);


    @Override
    Page<VendorAddress> findAll(Specification<VendorAddress> spec, Pageable pageable);

    /**
     * @param vendorId 店铺id
     * @Description: 查询默认店铺收货地址
     * @author mounan
     * @time:2017年1月12日 下午12:08:23
     */
    @Query("from VendorAddress va where va.deleteFlag=false and va.isReceive = true and va.vendor.id = ?1")
    VendorAddress findReceiveVendorAddressByVendorId(Long vendorId);

    /**
     * @param vendorId 店铺id
     * @Description: 查询店铺默认发货地址
     * @author mounan
     * @time:2017年1月12日 下午12:09:02
     */
    @Query("from VendorAddress va where  va.deleteFlag=false and va.isConsign = true and va.vendor.id = ?1")
    VendorAddress findConsignVendorAddressByVendorId(Long vendorId);

    @Override
    long count(Specification<VendorAddress> spec);
}
