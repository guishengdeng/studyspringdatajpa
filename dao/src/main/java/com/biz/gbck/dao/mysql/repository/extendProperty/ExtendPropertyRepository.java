package com.biz.gbck.dao.mysql.repository.extendProperty;

import com.biz.gbck.dao.mysql.po.product.ExtendProperty;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 商品扩展属性值 Repository
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
@Repository
public interface ExtendPropertyRepository extends CommonJpaRepository<ExtendProperty, Long>, ExtendPropertyDao {


    List<ExtendProperty> findByIdIn(List<Long> extendPropertyIds);

    List<ExtendProperty> findByProductExtendIdAndDeleteFlagOrderByIdx(Long productExtendId, boolean deleteFlag);

    ExtendProperty findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    @Query("SELECT MAX(ep.idx) FROM ExtendProperty ep where ep.productExtend.id = ?1")
    Integer findMaxIdx(Long productExtendId);


}