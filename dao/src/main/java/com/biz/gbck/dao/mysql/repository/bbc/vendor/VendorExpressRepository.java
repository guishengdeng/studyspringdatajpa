package com.biz.gbck.dao.mysql.repository.bbc.vendor;

import com.biz.gbck.dao.mysql.po.vendor.bbc.VendorExpress;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author mounan
 * @Description: 运费策略快递公司中间表dao
 * @time:2017年1月12日 下午12:09:57
 */
@Repository
public interface VendorExpressRepository extends CommonJpaRepository<VendorExpress, Long>, JpaSpecificationExecutor<VendorExpress> {


    /**
     * @param vendorId 店铺id
     * @param supportExpressId 快递id
     * @Description: 根据快递id和店铺id查询对应的中间表
     * @author mounan
     * @time:2017年1月12日 下午12:10:39
     */
    VendorExpress findVendorExpressByVendorIdAndSupportExpressIdAndDeleteFlag(Long vendorId, Long supportExpressId, Boolean flag);

    /**
     * @param vendorId 店铺id
     * @param status 状态
     * @Description: 查询列表
     * @author mounan
     * @time:2017年1月12日 下午12:11:43
     */
    List<VendorExpress> findVendorExpressByVendorIdAndDeleteFlag(Long vendorId, Boolean status);

    List<VendorExpress> findVendorExpressByVendorIdAndDeleteFlagAndSupportExpressIdIn(Long vendorId, Boolean status, List<Long> ids);

    /**
     * @Description: 根据店铺id集合 获取运费策略
     * @author mounan
     * @time:2017年2月24日 上午11:26:19
     */
    List<VendorExpress> findVendorExpressByDeleteFlagAndVendorIdIn(Boolean status, List<Long> ids);

    @Query("from VendorExpress ven where ven.deleteFlag = ?1 and ven.vendor.id = ?2 and ven.freightStrategy != null")
    List<VendorExpress> findVendorExpress(Boolean status, Long vendorId);
}
