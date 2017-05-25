package com.biz.service.stock;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.dao.mysql.repository.admin.AdminRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.mysql.repository.stock.StockRepository;
import com.biz.gbck.dao.mysql.specification.stock.ProductSpecification;
import com.biz.gbck.vo.product.SearchVo;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ProductBackendService;
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

        //判断一下companyID为空的时候........
        Page<StockShowVo> resultPage = null;
        if (reqVo.getCompanyId() != null) {
            List<StockShowVo> stockShowVos = new ArrayList<>();
            //通过reqVo查询满足条件的商品
            List<Product> products = productBackendService.findAll(reqVo);
            Map<Long, Product> productIdToProductMap = new HashMap<>();
            for (Product product : products) {
                productIdToProductMap.put(product.getId(), product);
            }
            Set<Long> productIds = productIdToProductMap.keySet();
            Page<Stock> stockPage = stockRepository.findByCompanyIdAndProductIdIn(reqVo.getCompanyId(), productIds,
                    new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize(), new Sort(Sort.Direction.ASC, "productId")));
            for (Stock sto : stockPage) {
                StockShowVo stockShowVo = new StockShowVo();
                stockShowVo.setQuantity(sto.getQuantity());
                stockShowVo.setProductId(sto.getProductId());
                stockShowVo.setName(productIdToProductMap.get(sto.getProductId()).getName());
                stockShowVo.setProductCode(productIdToProductMap.get(sto.getProductId()).getProductCode());
                stockShowVo.setCategoryName(productIdToProductMap.get(sto.getProductId()).getCategory().getName());
                stockShowVo.setBrandName(productIdToProductMap.get(sto.getProductId()).getBrand().getName());
                stockShowVo.setStandard(productIdToProductMap.get(sto.getProductId()).getStandard());
                stockShowVo.setPage(reqVo.getPage());
                stockShowVo.setPageSize(reqVo.getPageSize());
                stockShowVos.add(stockShowVo);
            }

           resultPage = new PageImpl<>(stockShowVos, new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize()), stockShowVos.size());
        }
        return resultPage;
    }
}


























