package com.biz.service;

import com.biz.gbck.vo.search.IncrProductIdxReqVo;
import com.biz.gbck.vo.search.ProductIdxVO;
import com.biz.gbck.vo.search.TotalProductIdxReqVo;
import java.util.List;

/**
 * 商品索引Service
 *
 * Created by david-liu on 2017/05/03 10:37.
 */
public interface ProductIdxService {

    List<ProductIdxVO> getProductIndices(TotalProductIdxReqVo reqVo);

    List<ProductIdxVO> getProductIndices(IncrProductIdxReqVo reqVo);

}
