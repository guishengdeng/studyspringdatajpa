package com.biz.gbck.dao.mysql.repository.evaluation;

import com.biz.gbck.dao.mysql.po.product.bbc.TemporaryEvaluation;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/3/24
 * @reivewer
 * @see
 */
@Repository
public interface TemporaryEvaluationRepository extends CommonJpaRepository<TemporaryEvaluation, Long>, TemporaryEvaluationDao {
}
