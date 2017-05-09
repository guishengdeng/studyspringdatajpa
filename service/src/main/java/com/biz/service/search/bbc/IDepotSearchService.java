package com.biz.service.search.bbc;

import com.biz.gbck.vo.search.bbc.DepotSearchResultVo;
import com.biz.gbck.vo.search.bbc.IncrIndexVo;
import com.biz.gbck.vo.search.bbc.SearchDepotConditionVo;
import com.biz.gbck.vo.search.bbc.SearchResult;
import java.io.Serializable;

/**
 * 门店搜索接口
 *
 * @author david-liu
 * @date 2017年01月07日
 * @reviewer
 * @see
 */
public interface IDepotSearchService extends Serializable {

    /**
     * 根据搜索条件搜索门店
     *
     * @param vo 搜索条件 Vo
     * @return 门店搜索结果集合
     */
    SearchResult<DepotSearchResultVo> searchDepot(SearchDepotConditionVo vo);

    /**
     * 更新门店全量索引
     */
    void updateTotalDepotIndexDocuments();

    /**
     * 通过索引 Vo 更新门店增量索引
     *
     * @param vo 索引 Vo
     */
    void updateIncrDepotIndexDocument(IncrIndexVo vo);
}
