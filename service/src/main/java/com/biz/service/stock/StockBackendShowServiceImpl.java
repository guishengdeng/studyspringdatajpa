package com.biz.service.stock;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.stock.CompanyStock;
import com.biz.gbck.dao.mysql.repository.stock.StockRepository;
import com.biz.gbck.vo.product.SearchVo;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ProductBackendService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by lzz on 2017/5/17.
 */
@Service
public class StockBackendShowServiceImpl extends AbstractBaseService implements StockBackendService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductBackendService productBackendService;


    public Page<StockShowVo> search(SearchVo reqVo) {

        //通过reqVo查询满足条件的商品
        List<Product> products = productBackendService.findAll(reqVo);
        Map<Long, Product> productIdToProductMap = new HashMap<>();
        for (Product product : products) {
            productIdToProductMap.put(product.getId(), product);
        }
        Set<Long> productIds = productIdToProductMap.keySet();
        Page<CompanyStock> stockPage = stockRepository.findByCompanyIdAndProductIdIn(reqVo.getCompanyId(), productIds,
                new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize(), new Sort(Sort.Direction.ASC, "productId")));
        List<StockShowVo> stockShowVos = new ArrayList<>();
        for (CompanyStock stock : stockPage) {
            StockShowVo stockShowVo = new StockShowVo();
            if (stock.getQuantity() != null) {
                stockShowVo.setQuantity(stock.getQuantity());
            }
            if (stock.getProductId() != null) {
                stockShowVo.setProductId(stock.getProductId());
            }
            if (StringUtils.isNotBlank(productIdToProductMap.get(stock.getProductId()).getName())) {
                stockShowVo.setName(productIdToProductMap.get(stock.getProductId()).getName());
            }
            if (StringUtils.isNotBlank(productIdToProductMap.get(stock.getProductId()).getProductCode())) {
                stockShowVo.setProductCode(productIdToProductMap.get(stock.getProductId()).getProductCode());
            }
            if (StringUtils.isNotBlank(productIdToProductMap.get(stock.getProductId()).getName())) {
                stockShowVo.setCategoryName(productIdToProductMap.get(stock.getProductId()).getCategory().getName());
            }
            if (StringUtils.isNotBlank(productIdToProductMap.get(stock.getProductId()).getBrand().getName())) {
                stockShowVo.setBrandName(productIdToProductMap.get(stock.getProductId()).getBrand().getName());
            }
            if (StringUtils.isNotBlank(productIdToProductMap.get(stock.getProductId()).getStandard())) {
                stockShowVo.setStandard(productIdToProductMap.get(stock.getProductId()).getStandard());
            }
            stockShowVo.setPage(reqVo.getPage());
            stockShowVo.setPageSize(reqVo.getPageSize());
            stockShowVos.add(stockShowVo);
        }
        Page<StockShowVo> resultPage = new PageImpl<>(stockShowVos, new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize()), stockShowVos.size());
        return resultPage;
    }
}




























