package com.biz.gbck.dao.mysql.repository.aparttag;

import com.biz.gbck.dao.mysql.po.product.meta.ApartTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
public interface ApartTagRepository extends JpaRepository<ApartTag, Long> {
    List<ApartTag> findByDeleteFlag(Boolean status);
}
