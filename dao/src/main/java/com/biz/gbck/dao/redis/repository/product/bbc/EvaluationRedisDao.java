package com.biz.gbck.dao.redis.repository.product.bbc;


import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.EvaluationRo;
import com.biz.gbck.vo.evaluation.frontend.VendorIdPageRequestVo;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author yangzichun
 * @date 2017/2/9
 */
@Repository
public class EvaluationRedisDao extends CrudRedisDao<EvaluationRo, Long> implements Serializable {
    private static final long serialVersionUID = -5950832172677027189L;

    /**
     * 根据店铺 id 分页查询全部评论
     *
     * @param reqVo 店铺 id 分页查询对象 vo
     * @return 评价 ro 集合
     */
    public List<EvaluationRo> findByVendorId(VendorIdPageRequestVo reqVo) {
        if (reqVo == null || reqVo.getVendorId() == null) {
            return newArrayList();
        }
        Set<byte[]> idBytes = super.zrevrange(getFieldSortedSetKey("vendorId", reqVo.getVendorId()), Math.max(0, reqVo.getPageIndex() - 1) * reqVo
                .getPageSize(), Math.max(1, reqVo.getPageIndex()) * reqVo.getPageSize() - 1);
        if (CollectionUtils.isNotEmpty(idBytes)) {
            return findByIds(idBytes);
        } else {
            return newArrayList();
        }
    }

    public List<EvaluationRo> findByEvaluationProductCode(VendorIdPageRequestVo reqVo) {
        if (reqVo == null || reqVo.getProductCode() == null) {
            return newArrayList();
        }
        Set<byte[]> idBytes = super.zrevrange(getFieldSortedSetKey("productCode", reqVo.getProductCode()), Math.max(0, reqVo.getPageIndex() - 1) * reqVo
                .getPageSize(), Math.max(1, reqVo.getPageIndex()) * reqVo.getPageSize() - 1);
        if (CollectionUtils.isNotEmpty(idBytes)) {
            return findByIds(idBytes);
        } else {
            return newArrayList();
        }

    }

}
