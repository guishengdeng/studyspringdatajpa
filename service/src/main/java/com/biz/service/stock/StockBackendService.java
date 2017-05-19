package com.biz.service.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.vo.stock.StockShowVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lzz on 2017/5/17.
 */
public interface StockBackendService {

    /**
     * 在页面做分页展示
     */
    Page<Stock> findList(StockShowVo stockShowVo);
    List<StockShowVo> find();
}
