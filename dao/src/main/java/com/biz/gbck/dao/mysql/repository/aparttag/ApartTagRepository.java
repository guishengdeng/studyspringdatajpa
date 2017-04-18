package com.biz.gbck.dao.mysql.repository.aparttag;

import com.biz.gbck.dao.mysql.repository.category.CategoryDao;
import com.biz.gbck.dao.mysql.po.product.ApartTag;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
@Repository
public interface ApartTagRepository extends CommonJpaRepository<ApartTag, Long>, CategoryDao {
    List<ApartTag> findByDeleteFlag(Boolean status);
}
