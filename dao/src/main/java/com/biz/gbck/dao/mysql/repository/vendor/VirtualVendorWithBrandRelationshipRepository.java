package com.biz.gbck.dao.mysql.repository.vendor;

import com.biz.gbck.dao.mysql.po.vendor.VirtualVendorWithBrandRelationShip;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/3/9
 * @reviewer
 * @see
 */
@Repository
public interface VirtualVendorWithBrandRelationshipRepository extends CommonJpaRepository<VirtualVendorWithBrandRelationShip, Long> {
}
