package com.biz.service.product.backend;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.mysql.specification.stock.ProductSpecification;
import com.biz.gbck.vo.product.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzz on 2017/5/25.
 */
@Service
public class ProductBackendServiceImpl implements ProductBackendService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll(SearchVo reqVo) {
        return  productRepository.findAll(new ProductSpecification(reqVo));
    }
}
