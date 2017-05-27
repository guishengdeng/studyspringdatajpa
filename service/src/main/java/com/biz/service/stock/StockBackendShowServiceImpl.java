package com.biz.service.stock;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.stock.CompanyStock;
import com.biz.gbck.dao.mysql.repository.stock.StockRepository;
import com.biz.gbck.vo.product.SearchVo;
import com.biz.gbck.vo.stock.StockShowVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ProductBackendService;
import com.biz.support.jpa.po.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


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
        Map<Long, Product> productIdsToProduct = products.stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
        Set<Long> productIds = productIdsToProduct.keySet();
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
            if (StringUtils.isNotBlank(productIdsToProduct.get(stock.getProductId()).getName())) {
                stockShowVo.setName(productIdsToProduct.get(stock.getProductId()).getName());
            }
            if (StringUtils.isNotBlank(productIdsToProduct.get(stock.getProductId()).getProductCode())) {
                stockShowVo.setProductCode(productIdsToProduct.get(stock.getProductId()).getProductCode());
            }
            if (StringUtils.isNotBlank(productIdsToProduct.get(stock.getProductId()).getName())) {
                stockShowVo.setCategoryName(productIdsToProduct.get(stock.getProductId()).getCategory().getName());
            }
            if (StringUtils.isNotBlank(productIdsToProduct.get(stock.getProductId()).getBrand().getName())) {
                stockShowVo.setBrandName(productIdsToProduct.get(stock.getProductId()).getBrand().getName());
            }
            if (StringUtils.isNotBlank(productIdsToProduct.get(stock.getProductId()).getStandard())) {
                stockShowVo.setStandard(productIdsToProduct.get(stock.getProductId()).getStandard());
            }
            stockShowVo.setPage(reqVo.getPage());
            stockShowVo.setPageSize(reqVo.getPageSize());
            stockShowVos.add(stockShowVo);
        }
        return new PageImpl<>(stockShowVos, new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize()), stockShowVos.size());
    }
}




























