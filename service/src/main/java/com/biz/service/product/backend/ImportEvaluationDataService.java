package com.biz.service.product.backend;

/**
 * 导入评价数据的服务
 *
 * @author zhangcheng
 * @date 2017/3/24
 * @reviewer
 * @see
 */
public interface ImportEvaluationDataService {

    /**
     * 将老官网评价数据导入到临时表中
     */
    void importTemporaryEvaluation();

    /**
     * 将临时表中的评价数据导入到正式评价表中
     */
    void importEvaluation();

    /**
     * 将正式评价表中的数据同步到redis
     */
    void syncEvaluationToRedis();
}
