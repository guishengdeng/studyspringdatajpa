package com.biz.gbck.dao.mysql.repository.vendor.vendorBrand;

import com.biz.gbck.dao.mysql.po.vendor.BrandVendorRelation;
import com.biz.gbck.dao.mysql.po.vendor.RegisterVendorBrand;
import com.biz.gbck.vo.vendor.BrandStreetVo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author yanweijin
 * @date 2017/3/14
 */
public interface BrandVendorRelationRepository extends CommonJpaRepository<BrandVendorRelation, Long>, JpaSpecificationExecutor<RegisterVendorBrand> {


    @Query("select new com.biz.soa.vo.vendor.BrandStreetVo(r.brand.name, r.brand.logo, r.vendor.id) from BrandVendorRelation r where r.categoryId = ?1 order by rand()")
    List<BrandStreetVo> findRandomStreetByCategoryId(Long categoryId, Pageable pageable);

    @Query("select r.brand.id from BrandVendorRelation r where r.vendor.id=?1")
    List<Long> findBrandsByVendorId(Long vendorId);


    @Modifying
    @Query("delete from BrandVendorRelation bvr where bvr.vendor.id = ?1")
    void deleteByVendorId(Long vendorId);
}