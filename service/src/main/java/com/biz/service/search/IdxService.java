package com.biz.service.search;

import com.biz.gbck.vo.search.IdxPageRequestVo;
import com.biz.gbck.vo.search.IdxPageResultVo;
import java.io.Serializable;
import java.util.List;

/**
 * 索引Service
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
public interface IdxService<T, I> extends Serializable {

    T getSearchIdx(I identityVo);

    IdxPageResultVo<T> getSearchIndices(IdxPageRequestVo vo);

    List<T> getSearchIndices(List<I> identityVos);
}
