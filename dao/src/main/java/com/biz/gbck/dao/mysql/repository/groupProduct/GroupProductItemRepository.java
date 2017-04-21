package com.biz.gbck.dao.mysql.repository.groupProduct;

import com.biz.gbck.dao.mysql.po.product.master.GroupProduct;
import com.biz.gbck.dao.mysql.po.product.master.GroupProductItem;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/3/20
 * @reviewer
 * @see
 */
@Repository
public interface GroupProductItemRepository extends CommonJpaRepository<GroupProductItem, Long>, GroupProductItemDao {

    List<GroupProductItem> findByParent(GroupProduct groupProduct);
}
