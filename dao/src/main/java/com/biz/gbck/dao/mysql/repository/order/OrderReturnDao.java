package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.vo.order.req.OrderReturnSearchReqVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public interface OrderReturnDao {

    Page<OrderReturn> search(OrderReturnSearchReqVo reqVo, Pageable page);
}
