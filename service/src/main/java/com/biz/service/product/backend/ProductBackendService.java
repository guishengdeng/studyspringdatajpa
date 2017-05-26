package com.biz.service.product.backend;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.vo.product.SearchVo;

import java.util.List;

/**
 * 按照条件获取product中的数据
 * Created lzz on 2017/5/19.
 */
public interface ProductBackendService {

    List<Product> findAll(SearchVo reqVo);
}
