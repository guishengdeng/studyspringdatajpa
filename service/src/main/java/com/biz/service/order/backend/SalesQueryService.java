package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.order.req.SalesSearchVo;
import org.springframework.data.domain.Page;

/**
 * Created by lzz on 2017/5/23.
 */
public interface SalesQueryService {

    Page<Order> findList(SalesSearchVo salesSearchVo);
}
