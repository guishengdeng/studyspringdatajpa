package com.biz.service.stock;

import com.biz.gbck.vo.product.ProductShowVo;
import com.biz.gbck.vo.stock.StockShowVo;
import org.springframework.data.domain.Page;


import java.util.List;

/**
 * Created by lzz on 2017/5/17.
 */
public interface StockBackendService {

    /**
     * 库存查询
     * @return
     */
    Page<StockShowVo> findList(ProductShowVo reqVo);
}
