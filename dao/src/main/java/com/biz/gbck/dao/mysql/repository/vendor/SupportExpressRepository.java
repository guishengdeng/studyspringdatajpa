package com.biz.gbck.dao.mysql.repository.vendor;

import com.biz.gbck.dao.mysql.po.vendor.bbc.SupportExpress;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author mounan
 * @Description: 快递公司dao
 * @time:2017年1月12日 下午12:07:16
 */
@Repository
public interface SupportExpressRepository extends CommonJpaRepository<SupportExpress, Long>, JpaSpecificationExecutor<SupportExpress> {

    List<SupportExpress> findAllByDeleteFlag(Boolean deleteFlag);

    SupportExpress findSupportExpressByNameAndDeleteFlag(String name, boolean b);

}
