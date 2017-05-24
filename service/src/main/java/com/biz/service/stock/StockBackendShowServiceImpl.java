package com.biz.service.stock;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.dao.mysql.repository.product.ProductDao;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.mysql.repository.stock.StockRepository;
import com.biz.gbck.dao.mysql.specification.stock.ProductSpecification;
import com.biz.gbck.vo.product.ProductShowVo;
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
public class StockBackendShowServiceImpl extends AbstractBaseService implements StockBackendService, ProductBackendService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private ProductDao productDao;

    public Page<StockShowVo> findList(ProductShowVo reqVo) {

//        //库存数量
//        Integer quantity = 0;
//        Page<Product> page = productRepository.findAll(new ProductSpecification(reqVo),
//                new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize(), new Sort(Sort.Direction.ASC, "productCode")));
//        List<Product> list = page.getContent();
//        List<StockShowVo> listStock = new ArrayList<StockShowVo>();
//        /**
//         * 遍历list向stockShowVo注入元素
//         */
//        for (int i = 0; i < list.size(); i++) {
//            StockShowVo stockShowVo = new StockShowVo();
//            Product product = (Product) list.get(i);
//            if (product.getId() != null) {
//                stockShowVo.setProductId(product.getId());
//            }
//            if (StringUtils.isNotBlank(product.getProductCode())) {
//                stockShowVo.setProductCode(product.getProductCode());
//            }
//            if (StringUtils.isNotBlank(product.getName())) {
//                stockShowVo.setName(product.getName());
//            }
//            if (StringUtils.isNotBlank(product.getBrand().getName())) {
//                stockShowVo.setBrandName(product.getBrand().getName());
//            }
//            if (StringUtils.isNotBlank(product.getCategory().getName())) {
//                stockShowVo.setCategoryName(product.getCategory().getName());
//            }
//            if (StringUtils.isNotBlank(product.getStandard())) {
//                stockShowVo.setStandard(product.getStandard());
//            }
//            Stock stock = stockRepository.findByProductId(product.getId());
//            if (stock != null && stock.getQuantity() != null) {
//                stockShowVo.setQuantity(stock.getQuantity());
//            } else {
//                stockShowVo.setQuantity(quantity);
//            }
//            stockShowVo.setPage(reqVo.getPage());
//            stockShowVo.setPageSize(reqVo.getPageSize());
//            listStock.add(stockShowVo);
//        }
//        Page<StockShowVo> pageStock = new PageImpl<StockShowVo>(listStock, new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize()), listStock.size());
//        return pageStock;
        //获取userId,从当前用户获取Id
         Long companyId = 2L;
         List<Stock> stockList =stockRepository.findByCompanyId(companyId);
        List<StockShowVo> listStock = new ArrayList<StockShowVo>();
         for (Stock stock : stockList){
             StockShowVo stockShowVo = new StockShowVo();
             if (stock.getQuantity() !=null){
                stockShowVo.setQuantity(stock.getQuantity());
             }
             if (stock.getProductId() != null){
                 stockShowVo.setProductId(stock.getProductId());
             }
             Product product = productRepository.findOne(stock.getProductId());
             if (StringUtils.isNotBlank(product.getProductCode())){
                 stockShowVo.setProductCode(product.getProductCode());
             }
             if (StringUtils.isNotBlank(product.getName())){
                 stockShowVo.setName(product.getName());
             }
             if (StringUtils.isNotBlank(product.getBrand().getName())){
                 stockShowVo.setBrandName(product.getBrand().getName());
             }
             if (StringUtils.isNotBlank(product.getStandard())){
                 stockShowVo.setStandard(product.getStandard());
             }
             stockShowVo.setPage(reqVo.getPage());
             stockShowVo.setPageSize(reqVo.getPageSize());
             listStock.add(stockShowVo);
         }
        Page<StockShowVo> pageStock = new PageImpl<StockShowVo>(listStock, new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize()), listStock.size());
//         List<Product> productList = productRepository.findAll();
//         Map<Long, String> productMap = new HashMap<Long, String>();
//         for (Product product : productList){
//             productMap.put(product.getId(), product.getName());
//         }
//         Set<Long> productIds = new HashSet<Long>();
//         productIds = productMap.keySet();
//             Page<Stock> page = stockRepository.findByCompanyIdAndProductIdIn(userId,productIds,
//                     new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize(), new Sort(Sort.Direction.ASC, "productCode")));
        return pageStock;
    }
}


























