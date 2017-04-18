package com.biz.service.search;

import com.biz.gbck.vo.search.IncrIndexVo;
import com.biz.gbck.vo.search.SearchResult;
import com.biz.gbck.vo.search.SearchVendorConditionVo;
import com.biz.gbck.vo.search.VendorSearchResultVo;
import java.io.Serializable;

/**
 * 商家搜索接口
 *
 * @author david-liu
 * @date 2017年01月07日
 * @reviewer
 * @see
 */
public interface IVendorSearchService extends Serializable {

    /**
     * 搜索商户
     *
     * @param vo 商户搜索 Vo
     * @return 商户搜索结果
     */
    SearchResult<VendorSearchResultVo> searchVendor(SearchVendorConditionVo vo);

    /**
     * 更新商户全量索引
     */
    void updateTotalVendorIndexDocuments(IncrIndexVo incrIndexVo);

    /**
     * 通过索引 Vo 更新商户增量索引
     *
     * @param vo 索引 Vo
     */
    void updateIncrVendorIndexDocument(IncrIndexVo vo);
}
