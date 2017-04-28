package com.biz.gbck.dao.mysql.repository.bbc.evaluation;

import com.biz.gbck.dao.mysql.po.product.bbc.Evaluation;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import java.util.Collection;
import java.util.List;

/**
 * 评价repository
 *
 * @author yangzichun
 * @date 2017/2/8
 */
//@Repository
//public interface EvaluationRepository extends CommonJpaRepository<Evaluation, Long>, JpaSpecificationExecutor<Evaluation> {
public interface EvaluationRepository {
    //根据商品id查询所有该id对应的评价
    List<Evaluation> findByProductOrderByIdAsc(Product product);

    //根据商品名称集合查询所有商品名称集合的评价
    List<Evaluation> findByVendorInOrderByIdAsc(Collection collection);

    //根据店铺名称集合查询所有店铺名称集合的评价
    List<Evaluation> findByProductInOrderByIdAsc(Collection collection);
}
