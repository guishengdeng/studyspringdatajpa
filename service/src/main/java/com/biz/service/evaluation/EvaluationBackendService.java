package com.biz.service.evaluation;

import com.biz.core.page.PageResult;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.evaluation.backend.EvaluationAuditRequestVo;
import com.biz.gbck.vo.evaluation.backend.EvaluationDetailVo;
import com.biz.gbck.vo.evaluation.backend.EvaluationQueryRequestVo;
import com.biz.gbck.vo.evaluation.backend.EvaluationResponseVo;

/**
 * 后台评价 service
 *
 * @author yangzichun
 * @date 2017/2/8
 */
public interface EvaluationBackendService {
    /**
     * 根据分页请求分页查询评价 vo
     *
     * @param reqVo 分页请求 vo
     * @return 评价分页结果
     */
    PageResult<EvaluationResponseVo> findEvaluations(EvaluationQueryRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据评价 id 查询一个评价vo
     *
     * @param id 评价 id
     * @return 评价 vo
     */
    EvaluationDetailVo findEvaluation(Long id) throws DepotNextDoorException;

    /**
     * 审核一条评价
     *
     * @param reqVo 评价 id
     */
    void auditEvaluation(EvaluationAuditRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 统计店铺评价
     */
    void statVendorScore();
}