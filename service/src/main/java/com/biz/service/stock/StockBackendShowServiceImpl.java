package com.biz.service.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.dao.mysql.repository.stock.StockRepository;
import com.biz.gbck.dao.mysql.specification.stock.StockSpecification;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.service.product.frontend.ProductService;
import com.biz.service.stock.StockBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzz on 2017/5/17.
 */
@Service
public class StockBackendShowServiceImpl implements StockBackendService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<StockShowVo> find() {
        return stockRepository.findList();
    }

//    @Override
//    public Page<Stock> findList(StockShowVo stockShowVo) {
//        List<StockShowVo> list = stockRepository.findList();
//        return stockRepository.findAll(new StockSpecification(stockShowVo), new PageRequest(stockShowVo.getPage() - 1, stockShowVo.getPageSize()));
//    }
}
